package org.barcode.apptv.player

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVPlayer
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun VideoPlayer(
    url: String,
    modifier: Modifier,
    urlIos: String,
) {
    val effectiveUrl = urlIos.ifBlank { url }

    if (effectiveUrl.endsWith(".ts", ignoreCase = true)) {
        Box(
            modifier         = modifier.background(Color.Black),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text("⚠️", fontSize = 36.sp)
                Text("Stream no compatible con iOS", color = Color.White, fontSize = 15.sp)
                Text("Configura una URL HLS (.m3u8) para este canal", color = Color(0xFF888888), fontSize = 12.sp)
            }
        }
        return
    }

    val playerViewController = remember(effectiveUrl) { AVPlayerViewController() }

    LaunchedEffect(effectiveUrl) {
        val nsUrl = NSURL.URLWithString(effectiveUrl) ?: return@LaunchedEffect
        playerViewController.player              = AVPlayer(uRL = nsUrl)
        playerViewController.showsPlaybackControls = true
    }

    DisposableEffect(effectiveUrl) {
        onDispose {
            playerViewController.player = null
        }
    }

    UIKitView(
        factory  = { playerViewController.view },
        modifier = modifier,
    )
}