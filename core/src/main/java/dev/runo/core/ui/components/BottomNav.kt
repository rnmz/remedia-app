package dev.runo.core.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.runo.core.R
import dev.runo.core.navigation.HomeRoute
import dev.runo.core.navigation.SearchRoute

@Composable
fun BottomNav(navController: NavController) {
    // Need refactor.
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(R.string.bottom_home, R.string.bottom_search)
    val icons = listOf(R.drawable.home, R.drawable.search)
    val routes = listOf(HomeRoute, SearchRoute)

    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                icon = {
                    Icon(
                        painter = painterResource(icons[index]),
                        contentDescription = stringResource(item)
                    )
                },
                label = { Text(stringResource(item)) },
                onClick = {
                    selectedItem = index
                    navController.navigate(routes[index])
                }
            )
        }
    }
}