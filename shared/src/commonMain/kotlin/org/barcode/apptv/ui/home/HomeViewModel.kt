package org.barcode.apptv.ui.home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.barcode.apptv.ui.home.model.LiveChannel
import org.barcode.apptv.ui.home.model.MovieItem

class HomeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val trendingMovies = listOf(
        MovieItem(
            "GALAXY'S EDGE", "2023", 4.5f, "SCI-FI",
            listOf(Color(0xFF1A2744), Color(0xFF2D1B4E))
        ),
        MovieItem("CITY NEON",     "2025", 5.0f, "THRILLER",
            listOf(Color(0xFF0D1F3C), Color(0xFF1A0A2E))),
        MovieItem("TERROR",        "2021", 4.5f, "HORROR",
            listOf(Color(0xFF3D0000), Color(0xFF1A0000))),
        MovieItem("TERROR",        "2022", 4.0f, "HORROR",
            listOf(Color(0xFF2A1A00), Color(0xFF1A0D00))),
        MovieItem("OPPENHEIMER",   "2023", 5.0f, "DRAMA",
            listOf(Color(0xFF2A1500), Color(0xFF1A0800))),
    )

    private val recentlyAdded = listOf(
        MovieItem("THE MOSSAICE", "2020", 4.0f, "CITY NEON",
            listOf(Color(0xFF1A2030), Color(0xFF0D1520))),
        MovieItem("MYSTERY",      "2024", 4.5f, "TERROR",
            listOf(Color(0xFF2A1500), Color(0xFF1A0D00))),
        MovieItem("MY RESEMSION", "2020", 4.0f, "DRAMA",
            listOf(Color(0xFF0D2020), Color(0xFF071515))),
        MovieItem("STORM",        "2021", 4.0f, "ORIGINALES",
            listOf(Color(0xFF1A1A2A), Color(0xFF0D0D1A))),
        MovieItem("ENIGMA",       "2022", 4.5f, "STO",
            listOf(Color(0xFF2A0A0A), Color(0xFF1A0505))),
    )

    private val liveChannels = listOf(
        LiveChannel("Cine Max", "MAX", listOf(Color(0xFF8B0000), Color(0xFF4A0000))),
        LiveChannel("Sports Hub",  "SPORTS HUB", listOf(Color(0xFF003A1A), Color(0xFF001A0D))),
        LiveChannel("News 24h",    "NEWS 24h",   listOf(Color(0xFF002A5C), Color(0xFF001A3D))),
        LiveChannel("Sports Live", "SPO LIVE",   listOf(Color(0xFF1A3A00), Color(0xFF0D1A00))),
    )

    private val genres = listOf(
        "ACCIÓN", "CIENCIA FICCIÓN", "DOCUMENTALES", "DRAMA", "TERROR", "COMEDIA"
    )

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        _uiState.value = HomeUiState(
            userName        = "Baru G.",
            heroBannerTitle = "APP TV",
            trendingMovies  = trendingMovies,
            recentlyAdded   = recentlyAdded,
            liveChannels    = liveChannels,
            genres          = genres,
        )
    }
}