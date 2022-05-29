package net.bible2.domain.model

import net.bible2.Bible
import net.bible2.Year

data class Twd(
    val bible: Bible,
    val year: Year,
    val language: String,
    val name: String,
    val url: String
)
