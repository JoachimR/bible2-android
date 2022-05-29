package net.bible2.domain.repository

import net.bible2.Bible
import net.bible2.Year
import net.bible2.domain.model.TheWordFileContent
import net.bible2.domain.model.Twd

interface TwdRepository {

    suspend fun getTwds(): List<Twd>

    suspend fun getTheWordFileContent(bible: Bible, year: Year): TheWordFileContent?
}
