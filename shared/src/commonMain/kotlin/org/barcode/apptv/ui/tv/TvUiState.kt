package org.barcode.apptv.ui.tv

import org.barcode.apptv.ui.tv.model.TvChannelLive

data class TvUiState(
    val channels: List<TvChannelLive> = emptyList(),
    val featured: List<TvChannelLive> = emptyList(),
    val selectedChannel: TvChannelLive? = null,
    val isPlaying: Boolean = false,
    val searchQuery: String = "",
    val filteredChannels: List<TvChannelLive> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)