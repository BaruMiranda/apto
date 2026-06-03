package org.barcode.apptv.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
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
import org.barcode.apptv.theme.DarkBackground
import org.barcode.apptv.theme.DarkSurface
import org.barcode.apptv.theme.Gold

@Composable
fun HomeHeader(userName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 14.dp),
        verticalAlignment    = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Logo
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Gold),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.PlayCircle, contentDescription = null,
                    tint = Color.Black, modifier = Modifier.size(20.dp))
            }
            Text(
                "FILMOSPHERE",
                color      = Gold,
                fontSize   = 18.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }

        // Acciones derecha
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Icon(Icons.Default.Search, contentDescription = "Buscar",
                tint = Color.White, modifier = Modifier.size(24.dp))

            Column(horizontalAlignment = Alignment.End) {
                // Avatar con indicador activo
                Box {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(DarkSurface)
                            .border(1.5.dp, Gold, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Person, contentDescription = null,
                            tint = Gold, modifier = Modifier.size(20.dp))
                    }
                    // Punto verde "Active"
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF4CAF50))
                            .border(1.5.dp, DarkBackground, CircleShape)
                            .align(Alignment.TopEnd)
                    )
                }
                Text("Active", color = Color(0xFF4CAF50), fontSize = 9.sp)
            }
        }
    }

    // Saludo
    Text(
        "Hola, $userName",
        color      = Gold,
        fontSize   = 15.sp,
        fontWeight = FontWeight.Medium,
        modifier   = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        textAlign  = androidx.compose.ui.text.style.TextAlign.End
    )
}