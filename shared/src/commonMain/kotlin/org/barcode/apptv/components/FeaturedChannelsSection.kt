package org.barcode.apptv.components

import androidx.compose.animation.animateColorAsState
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
import org.barcode.apptv.theme.Gold
import org.barcode.apptv.ui.tv.model.TvChannelLive

@Composable
fun FeaturedChannelsSection(
    channels: List<TvChannelLive>,
    selectedId: Int?,
    onSelect: (TvChannelLive) -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        SectionTitle("CANALES DESTACADOS")
        LazyRow(
            contentPadding        = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(channels) { ch ->
                TvFeaturedCard(
                    channel    = ch,
                    isSelected = ch.id == selectedId,
                    onClick    = { onSelect(ch) }
                )
            }
        }
    }
}

@Composable
private fun TvFeaturedCard(
    channel: TvChannelLive,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val borderColor by animateColorAsState(
        if (isSelected) Gold else Color.Transparent, label = "border"
    )

    Box(
        modifier = Modifier
            .width(150.dp)
            .height(110.dp)
            .clip(RoundedCornerShape(12.dp))
            .border(1.5.dp, borderColor, RoundedCornerShape(12.dp))
            .background(Brush.verticalGradient(channel.gradientColors))
            .clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .align(Alignment.BottomCenter)
                .background(Brush.verticalGradient(listOf(Color.Transparent, Color(0xCC000000))))
        )

        Text(
            text      = "CH ${channel.id.toString().padStart(2, '0')}",
            color     = Gold.copy(alpha = 0.9f),
            fontSize  = 9.sp,
            fontWeight = FontWeight.Bold,
            modifier  = Modifier.padding(8.dp).align(Alignment.TopStart)
        )

        Text(
            text     = channel.emoji,
            fontSize = 26.sp,
            modifier = Modifier.align(Alignment.Center).padding(bottom = 18.dp)
        )

        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 8.dp, vertical = 6.dp)
        ) {
            Text(
                channel.name,
                color      = Color.White,
                fontSize   = 11.sp,
                fontWeight = FontWeight.Bold,
                maxLines   = 1,
                overflow   = TextOverflow.Ellipsis,
            )
            Text(channel.genre, color = Gold.copy(alpha = 0.7f), fontSize = 9.sp)
        }

        // Botón play top-end
        Box(
            modifier = Modifier
                .padding(6.dp)
                .size(22.dp)
                .clip(CircleShape)
                .then(
                    if (isSelected) Modifier.background(Gold)
                    else Modifier.border(1.dp, Gold.copy(alpha = 0.6f), CircleShape)
                )
                .align(Alignment.TopEnd),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null,
                tint = if (isSelected) Color.Black else Gold.copy(alpha = 0.8f),
                modifier = Modifier.size(14.dp))
        }
    }
}
