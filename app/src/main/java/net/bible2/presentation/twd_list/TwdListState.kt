package net.bible2.presentation.twd_list

import net.bible2.domain.model.Twd

data class TwdListState(
    val isLoading: Boolean = false,
    val twds: List<Twd> = emptyList(),
    val error: String = ""
)
