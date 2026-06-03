package org.barcode.apptv.ui.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.barcode.apptv.player.LockLandscapeOrientation
import org.barcode.apptv.player.VideoPlayer
import org.barcode.apptv.ui.tv.model.TvChannelLive

@Composable
fun TvPlayerScreen(
    channel: TvChannelLive,
    onBack: () -> Unit,
) {
    LockLandscapeOrientation()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        VideoPlayer(
            url      = channel.url,
            urlIos   = channel.urlIos,
            modifier = Modifier.fillMaxSize(),
        )

        IconButton(
            onClick  = onBack,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.TopStart)
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.Black.copy(alpha = 0.5f)),
        ) {
            Icon(
                imageVector        = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint               = Color.White,
                modifier           = Modifier.size(22.dp),
            )
        }

        Text(
            text       = "${channel.emoji}  ${channel.name}",
            color      = Color.White,
            fontSize   = 14.sp,
            fontWeight = FontWeight.SemiBold,
            modifier   = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 16.dp),
        )
    }
}