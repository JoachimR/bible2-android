package net.bible2.data.remote

import net.bible2.data.remote.dto.TwdDto
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface TwdApi {

    @GET("twd11/?format=json")
    suspend fun getTwds(): List<TwdDto>

    @Streaming
    @GET
    suspend fun downloadTheWordFileContent(@Url url: String): Response<ResponseBody>
}
