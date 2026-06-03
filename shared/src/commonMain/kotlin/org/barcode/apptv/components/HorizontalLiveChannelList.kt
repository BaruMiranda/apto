package org.barcode.apptv.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.barcode.apptv.ui.home.model.LiveChannel

@Composable
fun HorizontalLiveChannelList(channels: List<LiveChannel>) {
    LazyRow(
        contentPadding        = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(channels) { channel ->
            LiveChannelCard(channel = channel)
        }
    }
}

@Composable
fun LiveChannelCard(channel: LiveChannel) {
    // Punto parpadeante animado
    val infiniteTransition = rememberInfiniteTransition(label = "live_blink")
    val alpha by infiniteTransition.animateFloat(
        initialValue   = 1f,
        targetValue    = 0.2f,
        animationSpec  = infiniteRepeatable(
            animation  = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "blink"
    )

    Box(
        modifier = Modifier
            .width(160.dp)
            .height(95.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Brush.horizontalGradient(channel.gradientColors))
            .clickable {}
    ) {
        // Gradiente oscuro inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(listOf(Color.Transparent, Color(0xAA000000)))
                )
        )

        // Badge LIVE
        Row(
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFCC0000))
                .padding(horizontal = 5.dp, vertical = 2.dp)
                .align(Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(5.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = alpha))
            )
            Text("LIVE", color = Color.White, fontSize = 8.sp, fontWeight = FontWeight.Bold)
        }

        // Nombre del canal (centro)
        Text(
            channel.tag,
            color      = Color.White,
            fontSize   = 13.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 0.5.sp,
            modifier   = Modifier.align(Alignment.Center)
        )

        // Nombre inferior
        Text(
            channel.name,
            color    = Color.White.copy(alpha = 0.85f),
            fontSize = 11.sp,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 8.dp, vertical = 5.dp)
        )
    }
}