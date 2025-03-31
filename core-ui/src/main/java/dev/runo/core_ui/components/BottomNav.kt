package dev.runo.core_ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.runo.core_ui.R
import dev.runo.core_ui.navigation.Routes

@Composable
fun BottomNav(navController: NavController) {
    // Need refactor.
    var selectedItem by remember { mutableIntStateOf(0) }
    val icons = listOf(R.drawable.home, R.drawable.search)
    val routes = listOf(Routes.Home, Routes.Search)

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        routes.forEachIndexed { index, route ->
            NavigationBarItem(
                selected = selectedItem == index,
                icon = {
                    Icon(
                        painter = painterResource(icons[index]),
                        contentDescription = null
                    )
                },
                onClick = {
                    selectedItem = index
                    navController.navigate(routes[index])
                }
            )
        }
    }
}