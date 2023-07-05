package com.example.androidchallengecatapi.repository

import com.example.androidchallengecatapi.data.model.Cat
import com.example.androidchallengecatapi.utils.AppsConstants.API_KEY
import com.example.androidchallengecatapi.utils.AppsConstants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface WebService {
    @GET("images/search?limit=100")
    suspend fun getCatById(): List<Cat>

    @GET("images/search?limit=100&breed_ids=")
    suspend fun searchByBreed(): List<Cat>
}

object RetrofitClient {


    val webService: WebService by lazy {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val requestBuilder = originalRequest.newBuilder()
                    .header("x-api-key", API_KEY)
                    .method(originalRequest.method(), originalRequest.body())
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }

}
