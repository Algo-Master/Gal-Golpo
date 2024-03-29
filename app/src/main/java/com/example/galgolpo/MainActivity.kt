package com.example.galgolpo

import android.app.Activity
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.galgolpo.Screens.ChatListPage
import com.example.galgolpo.Screens.LoginPage
import com.example.galgolpo.Screens.ProfilePage
import com.example.galgolpo.Screens.SignUpPage
import com.example.galgolpo.Screens.StatusPage
import com.example.galgolpo.ui.theme.GalGolpoTheme
import dagger.hilt.android.AndroidEntryPoint

sealed class DestinationPage(var route : String) {
    object Signup : DestinationPage("signup")
    object Login : DestinationPage("login")
    object Profile : DestinationPage("profile")

    object ChatList : DestinationPage("chatList")
    object Conversation : DestinationPage("conversation/{chatId}") {
        fun createRoute(id : String) = "conversation/$id"
    }

    object StatusList : DestinationPage("StatusList")
    object SingleStatus : DestinationPage("singlestatus/{userId}") {
        fun createRoute(userId : String) = "singlestatus/$userId"
    }
}
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            GalGolpoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ChatAppNavigation()
                }
            }
        }
    }
}
@Preview
@Composable
fun ChatAppNavigation() {

    val navController = rememberNavController()
    var vm = hiltViewModel<GGViewModel>()
    NavHost(navController = navController, startDestination = DestinationPage.Signup.route){
        composable(DestinationPage.Signup.route) {
            SignUpPage(navController, vm)
        }
        composable(DestinationPage.Login.route) {
            LoginPage(navController, vm)
        }
        composable(DestinationPage.ChatList.route){
            ChatListPage(navController, vm)
        }
        composable(DestinationPage.StatusList.route){
            StatusPage(navController, vm)
        }
        composable(DestinationPage.Profile.route){
            ProfilePage(navController, vm)
        }
    }
}