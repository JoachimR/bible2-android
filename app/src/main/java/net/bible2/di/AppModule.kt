package net.bible2.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import net.bible2.common.Constants
import net.bible2.data.remote.TwdApi
import net.bible2.data.repository.TwdRepositoryImpl
import net.bible2.domain.repository.TwdRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTwdApi(): TwdApi {
        return Retrofit.Builder()
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