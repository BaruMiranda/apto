package org.barcode.apptv.ui.home

import org.barcode.apptv.ui.home.model.LiveChannel
import org.barcode.apptv.ui.home.model.MovieItem

data class HomeUiState(
    val userName: String = "Baru M.",
    val heroBannerTitle: String = "THE MIDNIGHT ECHO",
    val trendingMovies: List<MovieItem> = emptyList(),
    val recentlyAdded: List<MovieItem> = emptyList(),
    val liveChannels: List<LiveChannel> = emptyList(),
    val genres: List<String> = emptyList(),
)