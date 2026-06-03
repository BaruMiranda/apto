package org.barcode.apptv.ui.soccer

import org.barcode.apptv.ui.tv.model.TvChannelLive

data class SoccerUiState(
    val mainChannels: List<TvChannelLive> = emptyList(),
    val leagues: List<SoccerLeague> = emptyList(),
)

data class SoccerLeague(
    val name: String,
    val emoji: String,
)