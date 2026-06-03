package org.barcode.apptv.ui.tv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import org.barcode.apptv.components.TvChannelListPanel
import org.barcode.apptv.components.TvErrorBanner
import org.barcode.apptv.components.TvHeader
import org.barcode.apptv.theme.DarkBackground
import org.barcode.apptv.theme.Gold
import org.barcode.apptv.ui.tv.model.TvChannelLive
import org.barcode.apptv.ui.tv.model.TvEvent

@Composable
fun TvScreen(
    viewModel: TvViewModel = viewModel(factory = TvViewModel.Factory),
    onPlayChannel: (TvChannelLive) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        TvHeader()

        state.errorMessage?.let { msg ->
            TvErrorBanner(
                message   = msg,
                onDismiss = { viewModel.onEvent(TvEvent.DismissError) }
            )
        }

        if (state.isLoading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Gold, strokeWidth = 2.dp)
            }
        } else {
            TvChannelListPanel(
                modifier       = Modifier.fillMaxSize(),
                state          = state,
                onSearchChange = { viewModel.onEvent(TvEvent.SearchQueryChanged(it)) },
                onSelect       = { viewModel.onEvent(TvEvent.SelectChannel(it)) },
                onPlay         = { ch ->
                    if (ch.url.isNotBlank()) onPlayChannel(ch)
                    else viewModel.onEvent(TvEvent.SelectChannel(ch))
                },
            )
        }
    }
}