package com.example.galgolpo

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.galgolpo.Data.Event
import com.example.galgolpo.Data.USER_NODE
import com.example.galgolpo.Data.UserData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

const val TAG = "Gal Golpo"

@HiltViewModel
class GGViewModel @Inject constructor(
    val auth: FirebaseAuth,
    var db: FirebaseFirestore
) : ViewModel() {

    var inProgress = mutableStateOf(false)
    val eventMutableState = mutableStateOf<Event<String>?>(null)
    var signIn = mutableStateOf(false)
    val userData = mutableStateOf<UserData?>(null)

    init {
        val currentUser = auth.currentUser
        signIn.value = currentUser != null
        currentUser?.uid?.let {
            getUserData(it)
        }
    }

    fun signUp(name: String, number: String, email: String, password: String) {
        inProgress.value = true
        if (name.isEmpty() or number.isEmpty() or email.isEmpty() or password.isEmpty()) {
            handleException(customMessage = "Please Fill all fields")
            return
        }
        db.collection(USER_NODE).whereEqualTo("number", number).get().addOnSuccessListener {
            if (it.isEmpty()) {
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        signIn.value = true
                        Log.d(TAG, "Signup is successfully completed")
                        createOrUpdateProfile(name, number)
                    } else {
                        handleException(it.exception, customMessage = "SignUp failed")
                    }
                }
            }
            else {
                handleException(customMessage = "Number already exists")
                inProgress.value = false
            }
        }
            .addOnFailureListener{
                handleException(exception = it, customMessage = "DataBase Collection failed")
                return@addOnFailureListener
            }
    }

    fun loginIn(email: String, password: String){

        if(email.isEmpty() or password.isEmpty()) {
            handleException(customMessage = "Please fill all the Fields")
            return
        }
        else {
            inProgress.value = true
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        signIn.value = true
                        inProgress.value = false
                        auth.currentUser?.uid?.let {
                            getUserData(it)
                        }
                    }
                    else {
                        handleException(exception = it.exception, customMessage = "Login failed")
                    }
                }
        }
    }

    private fun createOrUpdateProfile(
        name: String? = null,
        number: String? = null,
        imageurl: String? = null
    ) {
        var uid = auth.currentUser?.uid
        val userData = UserData(
            userId = uid,
            name = name ?: userData.value?.name,
            number = number ?: userData.value?.number,
            imageUrl = imageurl ?: userData.value?.imageUrl
        )
        Log.i(TAG, "I am here")

        uid?.let {
            inProgress.value = true
            db.collection(USER_NODE).document(uid).get().addOnSuccessListener {

                if (it.exists()) {
                    //update ur data
                } else {
                    db.collection(USER_NODE).document(uid).set(userData)
                    inProgress.value = false
                    getUserData(uid)
                }
            }
                .addOnFailureListener {
                    handleException(it, "Can not retrieve user")
                }
        }
    }

    private fun getUserData(uid: String) {
        inProgress.value = true
        db.collection(USER_NODE).document(uid).addSnapshotListener {
            value , error->
            if(error != null){
                handleException(error, customMessage = "Can not Retrieve User data")
            }
            if(value != null){
                var user = value.toObject<UserData>()
                userData.value = user
                inProgress.value = false
            }
        }
    }

    fun handleException(exception: Exception? = null, customMessage: String = "") {
        Log.e(TAG, "Gal Golpo exception: ", exception)
        exception?.printStackTrace()
        val errormsg = exception?.localizedMessage ?: ""
        val message = if (customMessage.isNullOrEmpty()) errormsg else customMessage

        eventMutableState.value = Event(message)
        inProgress.value = false
    }
}