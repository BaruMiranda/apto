package org.barcode.apptv.player

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * Reproductor de video multiplataforma.
 * - Android → Media3 ExoPlayer
 * - iOS     → AVPlayer
 */
@Composable
expect fun VideoPlayer(
    url: String,
    modifier: Modifier = Modifier,
    urlIos: String = "",
)