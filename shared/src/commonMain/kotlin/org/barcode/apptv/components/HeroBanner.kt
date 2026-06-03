package org.barcode.apptv.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import org.barcode.apptv.theme.Gold

@Composable
fun HeroBanner(
    title: String,
    onPlay: () -> Unit,
    onAdd:  () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .height(220.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(
                Brush.horizontalGradient(
                    listOf(Color(0xFF1A1F35), Color(0xFF2A2030), Color(0xFF0D0D1A))
                )
            )
    ) {
        // Gradiente izquierdo sobre la imagen para legibilidad
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        0f to Color(0xCC1A1F35),
                        0.55f to Color(0x661A1F35),
                        1f to Color.Transparent
                    )
                )
        )

        // Contenido texto + botones
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                title,
                color      = Color.White,
                fontSize   = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                lineHeight = 30.sp,
                letterSpacing = 0.5.sp
            )

            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                // Botón Reproducir
                Button(
                    onClick = onPlay,
                    colors  = ButtonDefaults.buttonColors(containerColor = Gold),
                    shape   = RoundedCornerShape(50),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
                ) {
                    Icon(Icons.Default.PlayArrow, contentDescription = null,
                        tint = Color.Black, modifier = Modifier.size(18.dp))
                    Spacer(Modifier.width(6.dp))
                    Text("REPRODUCIR", color = Color.Black,
                        fontWeight = FontWeight.Bold, fontSize = 13.sp)
                }

                // Botón Agregar
                OutlinedIconButton(
                    onClick = onAdd,
                    modifier = Modifier.size(38.dp),
                    border   = BorderStroke(1.5.dp, Color.White.copy(alpha = 0.6f)),
                    shape    = CircleShape,
                    colors   = IconButtonDefaults.outlinedIconButtonColors(containerColor = Color.Transparent)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Agregar",
                        tint = Color.White, modifier = Modifier.size(20.dp))
                }
            }
        }

        // Indicadores de paginación
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            repeat(5) { i ->
                Box(
                    modifier = Modifier
                        .size(if (i == 0) 20.dp else 6.dp, 6.dp)
                        .clip(RoundedCornerShape(50))
                        .background(if (i == 0) Gold else Color.White.copy(alpha = 0.4f))
                )
            }
        }
    }
}
