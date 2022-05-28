package net.bible2.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import net.bible2.common.Constants
import net.bible2.data.remote.TwdApi
import net.bible2.data.remote.UseLocalTwdJsonInterceptor
import net.bible2.data.repository.TwdRepositoryImpl
import net.bible2.domain.repository.TwdRepository
import net.bible2.util.isDebugMode
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTwdApi(@ApplicationContext context: Context): TwdApi {
        val builder = Retrofit.Builder()
        if (isDebugMode()) {
            builder.client(
                OkHttpClient.Builder()
                    .addInterceptor(UseLocalTwdJsonInterceptor(context))
                    .build()
            )
        }
        return builder
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TwdApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTwdRepository(api: TwdApi): TwdRepository {
        return TwdRepositoryImpl(api)
    }
}
