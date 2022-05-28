package net.bible2.data.remote

import android.content.Context
import net.bible2.R
import net.bible2.util.loadRawFile
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class UseLocalTwdJsonInterceptor(val context: Context) : Interceptor {

    private val contentType = "application/json".toMediaTypeOrNull()

    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url.toUri().toString()
        if (!uri.endsWith("twd11/?format=json")) {
            return chain.proceed(chain.request())
        }

        val localJson = loadRawFile(context, R.raw.twd11)!!

        return chain.proceed(chain.request())
            .newBuilder()
            .code(200)
            .body(localJson.toByteArray().toResponseBody(contentType))
            .addHeader("content-type", "application/json")
            .build()
    }
}
