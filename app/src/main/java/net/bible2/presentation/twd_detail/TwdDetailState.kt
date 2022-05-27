package net.bible2.presentation.twd_detail

import net.bible2.domain.model.TheWordFileContent

data class TwdDetailState(
    val isLoading: Boolean = false,
    val theWordFileContent: TheWordFileContent? = null,
    val error: String = ""
)
