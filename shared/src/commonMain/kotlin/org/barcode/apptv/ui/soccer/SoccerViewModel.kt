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
                    emoji          = "DS1",
                    url            = "http://play.servinetwork.com.pe:80/TELEDESTINO/Tele2025/952.ts",
                    gradientColors = listOf(Color(0xFF0A1628), Color(0xFF1A3A6B)),
                    urlIos         = "http://play.servinetwork.com.pe:80/TELEDESTINO/Tele2025/952.m3u8",
                ),
                TvChannelLive(
                    id             = 102,
                    name           = "DSports 2 HD",
                    genre          = "Fútbol en vivo",
                    emoji          = "DS2",
                    url            = "http://play.servinetwork.com.pe:80/TELEDESTINO/Tele2025/953.ts",
                    gradientColors = listOf(Color(0xFF2A0000), Color(0xFF7A0000)),
                    urlIos         = "http://play.servinetwork.com.pe:80/TELEDESTINO/Tele2025/953.m3u8",
                ),
                TvChannelLive(
                    id             = 103,
                    name           = "DSports+ HD",
                    genre          = "Fútbol en vivo",
                    emoji          = "DS+",
                    url            = "http://play.servinetwork.com.pe:80/TELEDESTINO/Tele2025/954.ts",
                    gradientColors = listOf(Color(0xFF002200), Color(0xFF005500)),
                    urlIos         = "http://play.servinetwork.com.pe:80/TELEDESTINO/Tele2025/954.m3u8",
                ),
            ),
            leagues = listOf(
                SoccerLeague("Champions League",  "UCL",  Color(0xFF1565C0)),
                SoccerLeague("Copa Libertadores", "COPA", Color(0xFFB8860B)),
                SoccerLeague("Premier League",    "PL",   Color(0xFF6A0DAD)),
                SoccerLeague("LaLiga",            "LL",   Color(0xFFCC0000)),
                SoccerLeague("Serie A",           "SA",   Color(0xFF0D47A1)),
                SoccerLeague("Bundesliga",        "BL",   Color(0xFFCC0000)),
                SoccerLeague("Copa del Mundo",    "FIFA", Color(0xFF2E7D32)),
                SoccerLeague("Copa América",      "CA",   Color(0xFF00838F)),
                SoccerLeague("Europa League",     "UEL",  Color(0xFFE65100)),
                SoccerLeague("FA Cup",            "FA",   Color(0xFF880E4F)),
                SoccerLeague("Ligue 1",           "L1",   Color(0xFF1A237E)),
                SoccerLeague("Copa del Rey",      "CDR",  Color(0xFF4A148C)),
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