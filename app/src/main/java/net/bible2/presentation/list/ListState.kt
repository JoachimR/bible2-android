package net.bible2.presentation.list

import net.bible2.domain.model.Twd

data class ListState(
    val isLoading: Boolean = false,
    val twds: List<Twd> = emptyList(),
    val error: String = ""
)
