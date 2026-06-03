package org.barcode.apptv.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import org.barcode.apptv.ui.home.model.MovieItem

@Composable
fun HorizontalMovieList(movies: List<MovieItem>) {
    LazyRow(
        contentPadding       = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(movies) { movie ->
            MovieCard(movie = movie)
        }
    }
}