package net.bible2.domain.model

import net.bible2.DayOfYear

data class Parol(
    val intro: String?,
    val text: String,
    val ref: String
)

data class TheWord(
    val dayOfYear: DayOfYear,
    val title: String?,
    val parol0: Parol,
    val parol1: Parol
)
