package net.bible2.data.repository

import javax.inject.Inject
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.bible2.Bible
import net.bible2.DaysInAYear
import net.bible2.Year
import net.bible2.data.remote.TwdApi
import net.bible2.data.remote.dto.toTwd
import net.bible2.data.room.Entities
import net.bible2.data.room.toTheWord
import net.bible2.data.room.toTheWordEntity
import net.bible2.data.room.toTwd
import net.bible2.data.room.toTwdEntity
import net.bible2.domain.model.TheWord
import net.bible2.domain.model.TheWordFileContent
import net.bible2.domain.model.Twd
import net.bible2.domain.repository.TwdRepository
import okhttp3.ResponseBody
import retrofit2.Response

class TwdRepositoryImpl @Inject constructor(
    private val twdEntityDao: Entities.TwdEntity.AsDao,
    private val theWordEntityDao: Entities.TheWordEntity.AsDao,
    private val api: TwdApi
) : TwdRepository {

    override suspend fun getTwds(): List<Twd> {
        return withContext(Dispatchers.IO) {
            val twdDtos = api.getTwds()
            val twds = twdDtos.map { it.toTwd() }
            storeTwdsInDatabase(twds)
            twds
        }
    }

    private fun storeTwdsInDatabase(twds: List<Twd>) {
        val entities = twds.map { it.toTwdEntity() }
        twdEntityDao.insertAll(*entities.toTypedArray())
    }

    override suspend fun getTheWordFileContent(bible: Bible, year: Year): TheWordFileContent {
        return withContext(Dispatchers.IO) {
            TheWordFileContent(
                bible = bible,
                year = year,
                items = loadAllItemsForTheYear(bible, year)
            )
        }
    }

    private suspend fun loadAllItemsForTheYear(bible: Bible, year: Year): List<TheWord> {
        val storedItems = theWordEntityDao.findAll(bible, year)
        if (storedItems.size == DaysInAYear) {
            return storedItems.map { it.toTheWord() }
        }
        return tryGetAllItemsForTheYear(bible, year)
    }

    private suspend fun tryGetAllItemsForTheYear(bible: Bible, year: Year): List<TheWord> {
        val twd = twdEntityDao.find(bible = bible, year = year)?.toTwd() ?: return emptyList()
        val content = download(twd.url) ?: return emptyList()
        storeTheWordsInDatabase(content.items, twd.bible, twd.year)
        return content.items
    }

    private suspend fun download(url: String): TheWordFileContent? {
        val response: Response<ResponseBody> = api.downloadTheWordFileContent(url)
        if (!response.isSuccessful) {
            return null
        }

        return suspendCoroutine<String?> { continuation ->
            thread {
                val xmlContent = response.body()?.string()
                continuation.resume(xmlContent)
            }
        }?.let {
            TheWordFileContentParser.parse(it)
        }
    }

    private fun storeTheWordsInDatabase(
        items: List<TheWord>,
        bible: Bible,
        year: Year
    ) {
        val entities = items.map {
            it.toTheWordEntity(bible = bible, year = year)
        }.toTypedArray()
        theWordEntityDao.insertAll(*entities)
    }
}
