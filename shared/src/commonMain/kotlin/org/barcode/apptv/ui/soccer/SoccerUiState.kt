package org.barcode.apptv.ui.soccer

import androidx.compose.ui.graphics.Color
import org.barcode.apptv.ui.tv.model.TvChannelLive

data class SoccerUiState(
    val mainChannels: List<TvChannelLive> = emptyList(),
    val leagues: List<SoccerLeague> = emptyList(),
)

data class SoccerLeague(
    val name: String,
    val shortName: String,
    val color: Color,
)