package com.example.galgolpo.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.galgolpo.GGViewModel

@Composable
fun ChatListPage(navController : NavController, vm : GGViewModel) {
    Text(
        text = "ChatList Page",
        color = Color.Cyan,
    )
    BottomNavigationBar(selectedItem = BottomNavigationItem.CHATLIST, navController = navController)
}