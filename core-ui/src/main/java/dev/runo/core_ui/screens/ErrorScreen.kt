package dev.runo.core_ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

// Make it more beautiful.
@Composable
fun ErrorScreen(
    reason: String
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val emojis = arrayOf("::>_<::", "(；′⌒`)", "ಥ_ಥ", "（＞人＜；）", "(；′⌒`)")
        Text(
            "${emojis.random()}\n$reason",
            textAlign = TextAlign.Center
        )
    }
}
