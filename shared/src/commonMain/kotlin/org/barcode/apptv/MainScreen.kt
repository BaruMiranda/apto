package org.barcode.apptv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import org.barcode.apptv.components.FloatingBottomBar
import org.barcode.apptv.ui.home.HomeScreen
import org.barcode.apptv.ui.menu.ProfileScreen
import org.barcode.apptv.ui.soccer.SoccerScreen
import org.barcode.apptv.ui.tv.TvPlayerScreen
import org.barcode.apptv.ui.tv.TvScreen
import org.barcode.apptv.ui.tv.model.TvChannelLive

@Composable
fun MainScreen() {

    val AppBackground = Brush.linearGradient(
        colors = listOf(
            Color(0xFF232428),
            Color(0xFF1E2435),
            Color(0xFF171C2A)
        )
    )

    var selectedIndex by remember { mutableStateOf(0) }
    var playingChannel by remember { mutableStateOf<TvChannelLive?>(null) }

    if (playingChannel != null) {
        TvPlayerScreen(
            channel = playingChannel!!,
            onBack  = { playingChannel = null },
        )
        return
    }

    Box(
        modifier = Modifier.fillMaxSize().background(AppBackground)
    ) {
        when (selectedIndex) {
            0 -> HomeScreen()
            1 -> TvScreen(onPlayChannel = { playingChannel = it })
            2 -> SoccerScreen(onPlayChannel = { playingChannel = it })
            3 -> ProfileScreen()
        }

        FloatingBottomBar(
            selectedIndex  = selectedIndex,
            onItemSelected = { selectedIndex = it },
            modifier       = Modifier.align(Alignment.BottomCenter)
        )
    }
}