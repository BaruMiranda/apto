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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import org.barcode.apptv.theme.*
import org.barcode.apptv.theme.Gold
import org.barcode.apptv.ui.tv.TvUiState
import org.barcode.apptv.ui.tv.model.TvChannelLive

@Composable
fun TvChannelListPanel(
    modifier: Modifier,
    state: TvUiState,
    onSearchChange: (String) -> Unit,
    onSelect: (TvChannelLive) -> Unit,
    onPlay: (TvChannelLive) -> Unit = {},
) {
    Column(
        modifier = modifier
            .background(DarkSurface)
            .border(BorderStroke(1.dp, Gold.copy(alpha = 0.15f)))
    ) {
        Column(
            modifier            = Modifier.fillMaxWidth().padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            SectionTitle("TODOS LOS CANALES")
            OutlinedTextField(
                value         = state.searchQuery,
                onValueChange = onSearchChange,
                placeholder   = { Text("Buscar canal…", fontSize = 12.sp, color = Gold.copy(alpha = 0.4f)) },
                leadingIcon   = {
                    Icon(Icons.Default.Search, contentDescription = null,
                        tint = Gold.copy(alpha = 0.6f), modifier = Modifier.size(18.dp))
                },
                trailingIcon  = if (state.searchQuery.isNotBlank()) {{
                    IconButton(onClick = { onSearchChange("") }) {
                        Icon(Icons.Default.Close, contentDescription = null,
                            tint = Gold.copy(alpha = 0.5f), modifier = Modifier.size(16.dp))
                    }
                }} else null,
                singleLine    = true,
                modifier      = Modifier.fillMaxWidth().height(46.dp),
                shape         = RoundedCornerShape(8.dp),
                colors        = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor      = Gold,
                    unfocusedBorderColor    = Gold.copy(alpha = 0.25f),
                    focusedTextColor        = Color.White,
                    unfocusedTextColor      = Color.White,
                    cursorColor             = Gold,
                    focusedContainerColor   = DarkBackground,
                    unfocusedContainerColor = DarkBackground,
                ),
                textStyle = LocalTextStyle.current.copy(fontSize = 13.sp),
            )
        }

        HorizontalDivider(color = Gold.copy(alpha = 0.1f), thickness = Dp.Hairline)

        if (state.filteredChannels.isEmpty()) {
            Box(modifier = Modifier.fillMaxWidth().padding(32.dp), contentAlignment = Alignment.Center) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("📭", fontSize = 28.sp)
                    Text("Sin resultados", color = Gold.copy(alpha = 0.5f), fontSize = 13.sp)
                }
            }
        } else {
            LazyColumn(Modifier.fillMaxSize()) {
                items(state.filteredChannels, key = { it.id }) { ch ->
                    TvChannelRow(
                        channel    = ch,
                        isSelected = ch.id == state.selectedChannel?.id,
                        onClick    = { onSelect(ch) },
                        onPlay     = { onPlay(ch) },
                    )
                    HorizontalDivider(color = Gold.copy(alpha = 0.07f), thickness = Dp.Hairline)
                }
            }
        }
    }
}

@Composable
private fun TvChannelRow(
    channel: TvChannelLive,
    isSelected: Boolean,
    onClick: () -> Unit,
    onPlay: () -> Unit = {},
) {
    val bgColor by animateColorAsState(
        if (isSelected) Gold.copy(alpha = 0.08f) else Color.Transparent, label = "rowBg"
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .drawBehind {
                if (isSelected) drawRect(Gold, size = androidx.compose.ui.geometry.Size(3.dp.toPx(), size.height))
            }
            .background(bgColor)
            .clickable { onClick() }
            .padding(horizontal = 14.dp, vertical = 9.dp),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Text(
            channel.id.toString().padStart(2, '0'),
            color = Gold.copy(alpha = 0.5f), fontSize = 10.sp, fontWeight = FontWeight.Bold,
            modifier = Modifier.width(20.dp), textAlign = TextAlign.End,
        )
        Box(
            modifier = Modifier
                .size(38.dp).clip(RoundedCornerShape(8.dp))
                .background(Brush.verticalGradient(channel.gradientColors)),
            contentAlignment = Alignment.Center
        ) {
            Text(channel.emoji, fontSize = 16.sp)
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                channel.name,
                color = if (isSelected) Gold else Color.White,
                fontSize = 13.sp, fontWeight = FontWeight.SemiBold,
                maxLines = 1, overflow = TextOverflow.Ellipsis,
            )
            Text(channel.genre, color = Gold.copy(alpha = 0.5f), fontSize = 10.sp)
        }
        Box(
            modifier = Modifier
                .size(28.dp).clip(CircleShape)
                .border(1.dp, if (isSelected) Gold else Gold.copy(alpha = 0.3f), CircleShape)
                .background(if (isSelected) Gold.copy(alpha = 0.15f) else Color.Transparent)
                .clickable { onPlay() },
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = "Reproducir",
                tint = if (isSelected) Gold else Gold.copy(alpha = 0.5f), modifier = Modifier.size(14.dp))
        }
    }
}