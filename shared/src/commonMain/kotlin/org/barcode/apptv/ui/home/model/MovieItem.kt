package org.barcode.apptv.ui.home.model

import androidx.compose.ui.graphics.Color

data class MovieItem(
    val title: String,
    val year: String,
    val rating: Float,
    val genre: String,
    val gradientColors: List<Color>
)
