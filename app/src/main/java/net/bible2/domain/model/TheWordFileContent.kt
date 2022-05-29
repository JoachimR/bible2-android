package net.bible2.domain.model

import net.bible2.Bible
import net.bible2.Year

data class TheWordFileContent(
    val bible: Bible,
    val year: Year,
    val items: List<TheWord>
)
