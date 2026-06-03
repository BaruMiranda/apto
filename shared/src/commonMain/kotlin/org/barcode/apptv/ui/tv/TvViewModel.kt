package org.barcode.apptv.ui.tv

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.barcode.apptv.ui.tv.model.TvChannelLive
import org.barcode.apptv.ui.tv.model.TvEvent
import kotlin.reflect.KClass

class TvViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TvUiState())
    val uiState: StateFlow<TvUiState> = _uiState.asStateFlow()

    init {
        loadChannels()
    }

    fun onEvent(event: TvEvent) {
        when (event) {
            is TvEvent.SelectChannel      -> selectChannel(event.channel)
            is TvEvent.SearchQueryChanged -> updateSearch(event.query)
            is TvEvent.DismissError       -> _uiState.update { it.copy(errorMessage = null) }
        }
    }

    private fun loadChannels() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val list = listOf(
                TvChannelLive(1,  "DSports", "Señal local", "📡",
                    url            = "http://play.servinetwork.com.pe:80/TELEDESTINO/Tele2025/952.ts",
                    gradientColors = listOf(Color(0xFF1A2744), Color(0xFF0D1520)),
                    urlIos         = "http://play.servinetwork.com.pe:80/TELEDESTINO/Tele2025/952.m3u8"),
                TvChannelLive(2,  "Canal 2",  "Por configurar", "📺", "",
                    listOf(Color(0xFF252B3C), Color(0xFF1E2435))),
                TvChannelLive(3,  "Canal 3",  "Por configurar", "🎬", "",
                    listOf(Color(0xFF2A1500), Color(0xFF1A0D00))),
                TvChannelLive(4,  "Canal 4",  "Por configurar", "🎙️", "",
                    listOf(Color(0xFF003A1A), Color(0xFF001A0D))),
                TvChannelLive(5,  "Canal 5",  "Por configurar", "🌐", "",
                    listOf(Color(0xFF002A5C), Color(0xFF001A3D))),
                TvChannelLive(6,  "Canal 6",  "Por configurar", "🎵", "",
                    listOf(Color(0xFF2A1A00), Color(0xFF1A0D00))),
                TvChannelLive(7,  "Canal 7",  "Por configurar", "⚽", "",
                    listOf(Color(0xFF1A3A00), Color(0xFF0D1A00))),
                TvChannelLive(8,  "Canal 8",  "Por configurar", "📰", "",
                    listOf(Color(0xFF3D0000), Color(0xFF1A0000))),
                TvChannelLive(9,  "Canal 9",  "Por configurar", "🎭", "",
                    listOf(Color(0xFF2A0A0A), Color(0xFF1A0505))),
                TvChannelLive(10, "Canal 10", "Por configurar", "🌟", "",
                    listOf(Color(0xFF1A1A2A), Color(0xFF0D0D1A))),
            )
            _uiState.update {
                it.copy(
                    channels         = list,
                    featured         = list.take(4),
                    filteredChannels = list,
                    isLoading        = false,
                )
            }
        }
    }

    private fun selectChannel(channel: TvChannelLive) {
        _uiState.update {
            it.copy(
                selectedChannel = channel,
                errorMessage    = if (channel.url.isBlank())
                    "URL no configurada para ${channel.name}" else null,
            )
        }
    }

    private fun updateSearch(query: String) {
        val base     = _uiState.value.channels
        val filtered = if (query.isBlank()) base
        else base.filter {
            it.name.contains(query, ignoreCase = true) ||
                    it.genre.contains(query, ignoreCase = true)
        }
        _uiState.update { it.copy(searchQuery = query, filteredChannels = filtered) }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: KClass<T>, extras: CreationExtras): T {
                return TvViewModel() as T
            }
        }
    }
}