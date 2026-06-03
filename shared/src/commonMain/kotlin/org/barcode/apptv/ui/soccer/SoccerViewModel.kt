package org.barcode.apptv.ui.soccer

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.barcode.apptv.ui.tv.model.TvChannelLive
import kotlin.reflect.KClass

class SoccerViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(SoccerUiState())
    val uiState: StateFlow<SoccerUiState> = _uiState.asStateFlow()

    init { load() }

    private fun load() {
        _uiState.value = SoccerUiState(
            mainChannels = listOf(
                TvChannelLive(
                    id             = 101,
                    name           = "DSports HD",
                    genre          = "Fútbol en vivo",
                    emoji          = "⚽",
                    url            = "http://play.servinetwork.com.pe:80/TELEDESTINO/Tele2025/952.ts",
                    gradientColors = listOf(Color(0xFF0A1628), Color(0xFF1A3A6B)),
                    urlIos         = "",
                ),
                TvChannelLive(
                    id             = 102,
                    name           = "DSports 2 HD",
                    genre          = "Fútbol en vivo",
                    emoji          = "🏆",
                    url            = "http://play.servinetwork.com.pe:80/TELEDESTINO/Tele2025/953.ts",
                    gradientColors = listOf(Color(0xFF2A0000), Color(0xFF7A0000)),
                    urlIos         = "",
                ),
                TvChannelLive(
                    id             = 103,
                    name           = "DSports+ HD",
                    genre          = "Fútbol en vivo",
                    emoji          = "🎯",
                    url            = "http://play.servinetwork.com.pe:80/TELEDESTINO/Tele2025/954.ts",
                    gradientColors = listOf(Color(0xFF002200), Color(0xFF005500)),
                    urlIos         = "",
                ),
            ),
            leagues = listOf(
                SoccerLeague("Champions League",  "🏆"),
                SoccerLeague("Copa Libertadores", "🌎"),
                SoccerLeague("Premier League",    "🏴"),
                SoccerLeague("LaLiga",            "🇪🇸"),
                SoccerLeague("Serie A",           "🇮🇹"),
                SoccerLeague("Bundesliga",        "🇩🇪"),
                SoccerLeague("Copa del Mundo",    "🌍"),
                SoccerLeague("Copa América",      "⭐"),
                SoccerLeague("Europa League",     "🔶"),
                SoccerLeague("FA Cup",            "🏅"),
                SoccerLeague("Ligue 1",           "🇫🇷"),
                SoccerLeague("Copa del Rey",      "👑"),
            ),
        )
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T =
                SoccerViewModel() as T
        }
    }
}