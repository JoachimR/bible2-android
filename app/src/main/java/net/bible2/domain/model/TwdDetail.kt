package net.bible2.domain.model

import java.util.*

data class TwdDetail(
    val bible: String,
    val bibleName: String,
    val bibleLanguageCode: String,
    val date: Date,
    val title: String,
    val parol1: String,
    val parol2: String
)
