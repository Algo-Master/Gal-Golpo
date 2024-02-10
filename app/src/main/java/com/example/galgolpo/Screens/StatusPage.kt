package com.example.galgolpo.Screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.galgolpo.GGViewModel

@Composable
fun StatusPage(navController : NavController, vm : GGViewModel) {
    
    BottomNavigationBar(selectedItem = BottomNavigationItem.PROFILE, navController = navController)
}