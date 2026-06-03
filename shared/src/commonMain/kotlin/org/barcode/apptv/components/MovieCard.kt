package org.barcode.apptv.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.barcode.apptv.ui.home.model.MovieItem

@Composable
fun MovieCard(movie: MovieItem) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .height(160.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Brush.verticalGradient(movie.gradientColors))
            .clickable {}
    ) {
        // Gradiente inferior para el texto
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color(0xCC000000))
                    )
                )
        )

        // Estrellas + año (top)
        Row(
            modifier = Modifier
                .padding(6.dp)
                .align(Alignment.TopStart),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(3.dp)
        ) {
            StarRating(rating = movie.rating, size = 9.dp)
            Spacer(Modifier.width(2.dp))
            Text(movie.year, color = Color.White.copy(alpha = 0.8f), fontSize = 9.sp)
        }

        // Título en el centro/medio (grande, para que se vea como en el diseño)
        if (movie.genre == "HORROR" || movie.title == "TERROR") {
            Text(
                movie.title,
                color      = Color.Red,
                fontSize   = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier   = Modifier
                    .align(Alignment.Center)
                    .padding(horizontal = 8.dp)
            )
        }

        // Nombre inferior
        Text(
            movie.title,
            color      = Color.White,
            fontSize   = 11.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines   = 1,
            overflow   = TextOverflow.Ellipsis,
            modifier   = Modifier
                .align(Alignment.BottomStart)
                .padding(horizontal = 8.dp, vertical = 6.dp)
        )
    }
}