package com.example.galgolpo.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.galgolpo.DestinationPage
import com.example.galgolpo.R
import com.example.galgolpo.navigateTo

enum class BottomNavigationItem(val icon: Int, val navDestination: DestinationPage) {
    CHATLIST(R.drawable.chat, DestinationPage.ChatList), STATUSLIST(
        R.drawable.loading,
        DestinationPage.StatusList
    ),
    PROFILE(R.drawable.user, DestinationPage.Profile)
}

@Composable
fun BottomNavigationBar(
    selectedItem: BottomNavigationItem, navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 5.dp)
            .background(Color.White)
    ) {
        for (item in BottomNavigationItem.values()) {
            Image(painter = painterResource(id = item.icon),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .padding(4.dp)
                    .weight(1f)
                    .clickable {
                        navigateTo(navController = navController, item.navDestination.route)
                    }
            )
        }
    }
}