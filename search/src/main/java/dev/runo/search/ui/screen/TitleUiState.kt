package dev.runo.search.ui.screen

import android.graphics.Bitmap

data class TitleUiState(
    val id: Int,
    val image: Bitmap,
    val name: String,
    val rating: Float
)