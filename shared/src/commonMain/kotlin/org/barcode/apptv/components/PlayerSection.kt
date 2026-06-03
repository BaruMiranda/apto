package org.barcode.apptv.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import org.barcode.apptv.theme.*
import org.barcode.apptv.theme.Gold
import org.barcode.apptv.ui.tv.TvUiState
import org.barcode.apptv.ui.tv.model.TvChannelLive

@Composable
fun PlayerSection(
    state: TvUiState,
    onPlay: () -> Unit,
    onStop: () -> Unit,
    videoPlayerContent: @Composable (url: String, modifier: Modifier) -> Unit,
    modifier: Modifier = Modifier,
) {
    val channel   = state.selectedChannel
    val isPlaying = state.isPlaying

    Column(
        modifier            = modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        SectionTitle("REPRODUCCIÓN EN VIVO")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f)
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.horizontalGradient(
                        channel?.gradientColors ?: listOf(Color(0xFF1A1F35), Color(0xFF0D0D1A))
                    )
                ),
            contentAlignment = Alignment.Center,
        ) {
            if (isPlaying && channel != null && channel.url.isNotBlank()) {
                videoPlayerContent(channel.url, Modifier.fillMaxSize())
            } else {
                Box(
                    modifier = Modifier.fillMaxSize()
                        .background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xAA000000))))
                )
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Text(
                        text          = channel?.name ?: "SERVI TV",
                        color         = Color.White,
                        fontSize      = 22.sp,
                        fontWeight    = FontWeight.ExtraBold,
                        letterSpacing = 0.5.sp,
                    )
                    Text(
                        text     = when {
                            channel == null         -> "Selecciona un canal"
                            channel.url.isBlank()   -> "URL no configurada"
                            else                    -> "Listo para reproducir"
                        },
                        color    = Gold.copy(alpha = 0.8f),
                        fontSize = 12.sp,
                    )
                    Button(
                        onClick        = onPlay,
                        colors         = ButtonDefaults.buttonColors(containerColor = Gold),
                        shape          = RoundedCornerShape(50),
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp),
                        enabled        = channel != null && channel.url.isNotBlank(),
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.Black, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("REPRODUCIR", color = Color.Black, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                    }
                }

                // Indicadores de paginación como HeroBanner
                Row(
                    modifier              = Modifier.align(Alignment.BottomCenter).padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    state.featured.forEach { ch ->
                        Box(
                            modifier = Modifier
                                .size(if (ch.id == channel?.id) 20.dp else 6.dp, 6.dp)
                                .clip(RoundedCornerShape(50))
                                .background(if (ch.id == channel?.id) Gold else Color.White.copy(alpha = 0.3f))
                        )
                    }
                }
            }
        }

        NowPlayingBar(channel = channel, isPlaying = isPlaying)
    }
}

@Composable
private fun NowPlayingBar(channel: TvChannelLive?, isPlaying: Boolean) {
    var tick by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) { while (true) { kotlinx.coroutines.delay(900); tick = !tick } }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(DarkSurface)
            .border(1.dp, Gold.copy(alpha = 0.25f), RoundedCornerShape(12.dp))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Box(
            modifier = Modifier
                .size(36.dp).clip(CircleShape)
                .background(if (isPlaying) Gold.copy(alpha = 0.2f) else DarkBackground)
                .border(1.dp, Gold.copy(alpha = 0.5f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(channel?.emoji ?: "📺", fontSize = 16.sp)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(channel?.name ?: "Sin reproducción", color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.Bold)
            Text(
                channel?.url?.ifBlank { "URL no configurada" } ?: "Selecciona un canal",
                color = Gold.copy(alpha = 0.5f), fontSize = 10.sp, maxLines = 1, overflow = TextOverflow.Ellipsis,
            )
        }
        if (isPlaying) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(Color(0xFFCC0000))
                    .padding(horizontal = 6.dp, vertical = 3.dp),
                verticalAlignment     = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.dp),
            ) {
                Box(Modifier.size(5.dp).clip(CircleShape).background(Color.White.copy(alpha = if (tick) 1f else 0.2f)))
                Text("LIVE", color = Color.White, fontSize = 8.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
