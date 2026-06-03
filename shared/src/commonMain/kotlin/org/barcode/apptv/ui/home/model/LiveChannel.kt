package org.barcode.apptv.ui.home.model

import androidx.compose.ui.graphics.Color

data class LiveChannel(
    val name: String,
    val tag: String,
    val gradientColors: List<Color>
)
