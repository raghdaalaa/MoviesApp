package com.example.movieapp.data.api


import android.util.Log
import com.example.movieapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration
import java.util.concurrent.TimeUnit


class MovieClient {
    //singleton design pattern  --> used when we want to make one instance from the class
    companion object {

        private var INSTANCE: Retrofit? = null
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun getInstance(): Retrofit {
            //okHttp interceptor
            if (BuildConfig.DEBUG) {      //return boolean

                if (INSTANCE == null) {
                    INSTANCE = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(getClient())
                        .build()
                }

            }
            return INSTANCE!!
        }

         fun getClient(): OkHttpClient {
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor())
                .connectTimeout(10,TimeUnit.SECONDS)                     // the time of start channel between app and server
                .readTimeout(30, TimeUnit.SECONDS)      // the time of read data
                .build()

            return client
        }

         fun loggingInterceptor(): Interceptor {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            return interceptor
        }
    }
}
