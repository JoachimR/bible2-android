package net.bible2.domain.model

data class Parol(
    val intro: String?,
    val text: String,
    val ref: String
)

data class TheWord(
    val day: Int,
    val title: String?,
    val parols: List<Parol>
)
