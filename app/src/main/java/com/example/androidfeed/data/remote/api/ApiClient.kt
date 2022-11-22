package com.example.androidfeed.data.remote.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://jsonplaceholder.typicode.com/"
class ApiClient {

    companion object{
        private var retrofit:Retrofit?=null
        fun getApiClient():Retrofit{
            val gson=GsonBuilder()
                .setLenient()
                .create()
            var interceptor = HttpLoggingInterceptor()
            interceptor.level =HttpLoggingInterceptor.Level.BASIC
            val okHttpClient =OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .readTimeout(30,TimeUnit.SECONDS)
                .writeTimeout(30,TimeUnit.SECONDS)
                .build()
            if(retrofit == null){
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

            }
            return retrofit!!
        }
    }
}