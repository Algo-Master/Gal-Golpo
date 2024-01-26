package com.example.galgolpo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.galgolpo.ui.theme.GalGolpoTheme

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

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalGolpoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(name = "Rinki")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}