package org.barcode.apptv.ui.soccer

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import org.barcode.apptv.components.AppHeader
import org.barcode.apptv.components.SectionTitle
import org.barcode.apptv.theme.DarkBackground
import org.barcode.apptv.theme.DarkSurface
import org.barcode.apptv.theme.Gold
import org.barcode.apptv.ui.tv.model.TvChannelLive

@Composable
fun SoccerScreen(
    viewModel: SoccerViewModel = viewModel(factory = SoccerViewModel.Factory),
    onPlayChannel: (TvChannelLive) -> Unit = {},
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState(pageCount = { state.mainChannels.size })

    LaunchedEffect(state.mainChannels.size) {
        if (state.mainChannels.size < 2) return@LaunchedEffect
        while (true) {
            delay(4500)
            val next = (pagerState.currentPage + 1) % state.mainChannels.size
            pagerState.animateScrollToPage(next, animationSpec = tween(600))
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkBackground)
    ) {
        AppHeader(section = "Fútbol")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp)
        ) {
            // ── Carrusel ──
            HorizontalPager(
                state    = pagerState,
                modifier = Modifier.fillMaxWidth(),
            ) { page ->
                val channel = state.mainChannels[page]
                ChannelCarouselCard(
                    channel = channel,
                    onClick = { onPlayChannel(channel) },
                )
            }

            // Indicadores de página
            Row(
                modifier              = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment     = Alignment.CenterVertically,
            ) {
                state.mainChannels.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 4.dp)
                            .size(
                                width  = if (pagerState.currentPage == index) 22.dp else 6.dp,
                                height = 6.dp,
                            )
                            .clip(RoundedCornerShape(50))
                            .background(
                                if (pagerState.currentPage == index) Gold
                                else Gold.copy(alpha = 0.25f)
                            )
                    )
                }
            }

            // ── Ligas & Copas ──
            SectionTitle("LIGAS & COPAS")
            Spacer(Modifier.height(12.dp))

            state.leagues.chunked(3).forEach { row ->
                Row(
                    modifier              = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    row.forEach { league ->
                        LeagueCard(league = league, modifier = Modifier.weight(1f))
                    }
                    repeat(3 - row.size) { Spacer(Modifier.weight(1f)) }
                }
                Spacer(Modifier.height(10.dp))
            }

            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun ChannelCarouselCard(
    channel: TvChannelLive,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 7f)
            .background(Brush.verticalGradient(channel.gradientColors))
            .clickable { onClick() }
    ) {
        // Gradiente oscuro inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(Color.Transparent, Color.Black.copy(alpha = 0.75f))
                    )
                )
        )

        // Badge EN VIVO (Material Icon inline — sin emoji)
        Row(
            modifier              = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(Color(0xFFCC0000))
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp),
        ) {
            Box(Modifier.size(5.dp).clip(CircleShape).background(Color.White))
            Text("EN VIVO", color = Color.White, fontSize = 9.sp, fontWeight = FontWeight.Bold)
        }

        // Centro: icono + nombre (sin emoji, todo Compose-rendered)
        Column(
            modifier            = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Icon(
                imageVector        = Icons.Default.LiveTv,
                contentDescription = null,
                tint               = Gold.copy(alpha = 0.85f),
                modifier           = Modifier.size(52.dp),
            )
            Text(
                channel.name,
                color         = Color.White,
                fontSize      = 22.sp,
                fontWeight    = FontWeight.ExtraBold,
                letterSpacing = 0.5.sp,
            )
            Text(
                channel.genre,
                color    = Gold.copy(alpha = 0.7f),
                fontSize = 11.sp,
            )
        }

        // Botón play
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(14.dp)
                .size(46.dp)
                .clip(CircleShape)
                .background(Gold)
                .clickable { onClick() },
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                Icons.Default.PlayArrow,
                contentDescription = "Reproducir",
                tint     = Color.Black,
                modifier = Modifier.size(28.dp),
            )
        }
    }
}

@Composable
private fun LeagueCard(
    league: SoccerLeague,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(DarkSurface)
            .border(1.dp, Gold.copy(alpha = 0.15f), RoundedCornerShape(12.dp))
            .padding(vertical = 14.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp),
    ) {
        // Badge de color con sigla — sin emoji, 100% Compose
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(league.color.copy(alpha = 0.2f))
                .border(1.5.dp, league.color.copy(alpha = 0.7f), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                league.shortName,
                color      = league.color,
                fontSize   = if (league.shortName.length > 3) 6.sp else 8.sp,
                fontWeight = FontWeight.Bold,
                textAlign  = TextAlign.Center,
            )
        }
        Text(
            league.name,
            color      = Color.White,
            fontSize   = 10.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign  = TextAlign.Center,
            maxLines   = 2,
            overflow   = TextOverflow.Ellipsis,
        )
    }
}