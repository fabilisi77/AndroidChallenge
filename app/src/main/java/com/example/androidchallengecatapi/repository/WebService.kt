package com.example.androidchallengecatapi.repository

import com.example.androidchallengecatapi.data.model.CatResponse
import com.example.androidchallengecatapi.utils.AppsConstants.API_KEY
import com.example.androidchallengecatapi.utils.AppsConstants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface WebService {
    @GET("images/search?limit=20")
    suspend fun getRandomCats(): List<CatResponse>

    @GET("images/search?limit=20")
    suspend fun searchCatsByBreed(@Query("breed_ids") breedIds: String): List<CatResponse>
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
