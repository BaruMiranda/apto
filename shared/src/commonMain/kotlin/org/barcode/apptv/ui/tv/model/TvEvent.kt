package org.barcode.apptv.ui.tv.model

sealed class TvEvent {
    data class SelectChannel(val channel: TvChannelLive) : TvEvent()
    data class SearchQueryChanged(val query: String) : TvEvent()
    object DismissError : TvEvent()
}