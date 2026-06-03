package org.barcode.apptv.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.barcode.apptv.theme.Gold
import org.barcode.apptv.theme.GoldDark

@Composable
fun StarRating(rating: Float, size: Dp = 12.dp) {
    val fullStars = rating.toInt()
    val hasHalf   = (rating - fullStars) >= 0.5f
    Row {
        repeat(5) { i ->
            val tint = when {
                i < fullStars           -> Gold
                i == fullStars && hasHalf -> GoldDark
                else                    -> Color.White.copy(alpha = 0.3f)
            }
            Icon(
                if (i < fullStars || (i == fullStars && hasHalf)) Icons.Default.Star
                else Icons.Outlined.StarOutline,
                contentDescription = null,
                tint     = tint,
                modifier = Modifier.size(size)
            )
        }
    }
}