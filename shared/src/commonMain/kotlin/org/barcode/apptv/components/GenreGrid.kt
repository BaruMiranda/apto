package org.barcode.apptv.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun GenreGrid(genres: List<String>) {
    // Usamos LazyRow con grupos de 2 filas para mantener scroll horizontal como en el diseño
    // Si prefieres grid estático, también funciona
    LazyRow(
        contentPadding        = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(genres) { genre ->
            GenreChip(label = genre)
        }
    }
}