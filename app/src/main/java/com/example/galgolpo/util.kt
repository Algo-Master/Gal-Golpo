package com.example.galgolpo

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.google.android.play.integrity.internal.f

fun navigateTo(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(route)
        launchSingleTop = true
    }
}

@Composable
fun CommonProgressBar() {
    Row(
        modifier = Modifier
            .alpha(0.5f)
            .background(Color.LightGray)
            .clickable(enabled = false) {}
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun CheckSignedIn(vm: GGViewModel, navController: NavController) {
    val alreadySignIn = remember {
        mutableStateOf(false)
    }
    val signIn = vm.signIn.value
    if(signIn && !alreadySignIn.value){
        alreadySignIn.value = true
        Log.i("Gal Golpo", "About to navigate")
        navController.navigate(DestinationPage.ChatList.route){
            popUpTo(0)
        }
//        navigateTo(navController, DestinationPage.ChatList.route)
    }
}