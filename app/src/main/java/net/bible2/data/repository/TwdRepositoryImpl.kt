package net.bible2.data.repository

import javax.inject.Inject
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import net.bible2.data.remote.TwdApi
import net.bible2.data.remote.dto.TwdDto
import net.bible2.domain.model.TheWordFileContent
import net.bible2.domain.repository.TwdRepository
import okhttp3.ResponseBody
import retrofit2.Response

class TwdRepositoryImpl @Inject constructor(
    private val api: TwdApi
) : TwdRepository {

    override suspend fun getTwds(): List<TwdDto> {
        return api.getTwds()
    }

    override suspend fun getTheWordFileContent(url: String): TheWordFileContent? {
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
}
