package com.example.galgolpo.Screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.galgolpo.GGViewModel

@Composable
fun ProfilePage(navController: NavController, vm : GGViewModel) {

    BottomNavigationBar(selectedItem = BottomNavigationItem.STATUSLIST, navController = navController)
}