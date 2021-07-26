package ru.nobird.hse2021.sample.githubuserlist.presentation.injection

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

object NetworkModule {
    private const val BASE_URL = "https://api.github.com"

    fun provideRetrofit(
        json: Json
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
            .client(OkHttpClient.Builder().build())
            .build()

    fun provideJson(): Json =
        Json {
            coerceInputValues = true
            ignoreUnknownKeys = true
            serializersModule = SerializersModule {}
        }
}