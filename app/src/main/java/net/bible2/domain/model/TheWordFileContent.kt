package net.bible2.domain.model

data class TheWordFileContent (
    val key: String,
    val year: Int,
    val items: List<TheWord>
)
