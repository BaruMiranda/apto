package org.barcode.apptv.ui.soccer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.barcode.apptv.components.AppHeader
import org.barcode.apptv.theme.DarkBackground
import org.barcode.apptv.theme.Gold

@Composable
fun SoccerScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        AppHeader(section = "Fútbol")

        Box(
            modifier         = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text("⚽", fontSize = 48.sp)
            Text(
                "Próximamente",
                color      = Gold.copy(alpha = 0.5f),
                fontSize   = 14.sp,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}