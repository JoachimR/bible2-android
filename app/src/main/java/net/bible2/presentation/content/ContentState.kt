package net.bible2.presentation.content

import net.bible2.domain.model.TheWordFileContent

data class ContentState(
    val isLoading: Boolean = false,
    val theWordFileContent: TheWordFileContent? = null,
    val error: String = ""
)
