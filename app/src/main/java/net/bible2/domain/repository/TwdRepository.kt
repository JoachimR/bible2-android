package net.bible2.domain.repository

import net.bible2.data.remote.dto.TwdDto
import net.bible2.domain.model.TheWordFileContent

interface TwdRepository {

    suspend fun getTwds(): List<TwdDto>

    suspend fun getTheWordFileContent(url: String): TheWordFileContent?

}