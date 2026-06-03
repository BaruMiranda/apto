package org.barcode.apptv.ui.tv.model

import androidx.compose.ui.graphics.Color

data class
TvChannelLive(
    val id: Int,
    val name: String,
    val genre: String,
    val emoji: String,
    val url: String,
    val gradientColors: List<Color> = listOf(Color(0xFF1A2435), Color(0xFF0D1520)),
    val urlIos: String = "",
)