package net.bible2.presentation.content

import net.bible2.domain.model.TheWordFileContent

sealed class ContentState {
    object BibleMissing : ContentState()
    object Loading : ContentState()
    class Loaded(val content: TheWordFileContent) : ContentState()
    class ErrorLoading(val message: String?) : ContentState()
}
