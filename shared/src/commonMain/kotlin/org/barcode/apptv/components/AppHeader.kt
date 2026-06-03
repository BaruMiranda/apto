package org.barcode.apptv.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.barcode.apptv.theme.DarkSurface
import org.barcode.apptv.theme.Gold

@Composable
fun AppHeader(
    section: String = "",
    showLiveBadge: Boolean = false,
) {
    var tick by remember { mutableStateOf(true) }
    LaunchedEffect(Unit) { while (true) { kotlinx.coroutines.delay(900); tick = !tick } }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkSurface)
    ) {
        // línea dorada inferior
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(listOf(Color.Transparent, Gold, Color.Transparent))
                )
        )

        Row(
            modifier              = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // ── Logo ──
            Row(
                verticalAlignment     = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Gold),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Icons.Default.Tv,
                        contentDescription = null,
                        tint     = Color.Black,
                        modifier = Modifier.size(18.dp),
                    )
                }
                Text(
                    "SERVI TV",
                    color         = Gold,
                    fontSize      = 18.sp,
                    fontWeight    = FontWeight.Bold,
                    letterSpacing = 1.sp,
                )
            }

            // ── Derecha ──
            Row(
                verticalAlignment     = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                // sección actual
                if (section.isNotBlank()) {
                    Text(
                        section.uppercase(),
                        color         = Gold.copy(alpha = 0.6f),
                        fontSize      = 10.sp,
                        fontWeight    = FontWeight.SemiBold,
                        letterSpacing = 0.8.sp,
                    )
                }

                // badge EN VIVO
                if (showLiveBadge) {
                    Row(
                        modifier              = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFCC0000))
                            .padding(horizontal = 7.dp, vertical = 3.dp),
                        verticalAlignment     = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Box(
                            Modifier
                                .size(5.dp)
                                .clip(CircleShape)
                                .background(Color.White.copy(alpha = if (tick) 1f else 0.2f))
                        )
                        Text("EN VIVO", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                }

                // avatar usuario
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Gold.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        tint     = Gold,
                        modifier = Modifier.size(18.dp),
                    )
                }
            }
        }
    }
}