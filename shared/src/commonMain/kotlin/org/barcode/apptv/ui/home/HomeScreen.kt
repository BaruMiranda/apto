package org.barcode.apptv.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.barcode.apptv.components.GenreGrid
import org.barcode.apptv.components.HeroBanner
import org.barcode.apptv.components.HomeHeader
import org.barcode.apptv.components.HorizontalLiveChannelList
import org.barcode.apptv.components.HorizontalMovieList
import org.barcode.apptv.components.SectionTitle

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = remember { HomeViewModel() }
) {
    val uiState by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 100.dp)
    ) {
        HomeHeader(userName = uiState.userName)

        HeroBanner(
            title  = uiState.heroBannerTitle,
            onPlay = {},
            onAdd  = {}
        )

        Spacer(Modifier.height(24.dp))

        SectionTitle("TENDENCIAS GLOBALES")
        Spacer(Modifier.height(12.dp))
        HorizontalMovieList(movies = uiState.trendingMovies)

        Spacer(Modifier.height(24.dp))

        SectionTitle("AGREGADO RECIENTEMENTE")
        Spacer(Modifier.height(12.dp))
        HorizontalMovieList(movies = uiState.recentlyAdded)

        Spacer(Modifier.height(24.dp))

        SectionTitle("CANALES EN VIVO")
        Spacer(Modifier.height(12.dp))
        HorizontalLiveChannelList(channels = uiState.liveChannels)

        Spacer(Modifier.height(24.dp))

        SectionTitle("EXPLORAR GÉNEROS")
        Spacer(Modifier.height(12.dp))
        GenreGrid(genres = uiState.genres)

        Spacer(Modifier.height(16.dp))
    }
}