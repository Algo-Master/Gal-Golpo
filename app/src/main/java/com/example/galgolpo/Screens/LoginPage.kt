package com.example.galgolpo.Screens

import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.galgolpo.GGViewModel

@Composable
fun LoginPage(navController: NavController, vm : GGViewModel) {
    Text(text = "Hi, This is the login page", modifier = Modifier.background(Color.Cyan))
}