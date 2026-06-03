package org.barcode.apptv.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.barcode.apptv.theme.Gold

@Composable
fun TvVideoPlaceholder(modifier: Modifier = Modifier) {
    Box(
        modifier         = modifier.background(Color.Black),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Icon(Icons.Default.Tv, contentDescription = null,
                tint = Gold.copy(alpha = 0.4f), modifier = Modifier.size(40.dp))
            Text("Conecta tu VideoPlayer", color = Gold.copy(alpha = 0.4f), fontSize = 11.sp)
        }
    }
}