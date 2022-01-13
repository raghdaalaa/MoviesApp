package com.example.moviesapp.data.api


import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class MovieClient{
    companion object{
        private var INSTANCE:Retrofit?=null
        private const val BASE_URL="https://developers.themoviedb.org/3/"

        fun getInstance():Retrofit{
            if (INSTANCE ==null){
                INSTANCE=Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE !!
        }
    }

//    private val BASE_URL = "https://developers.themoviedb.org/3/"
//     val API_KEY="2f5c7f8a5b120242529ccef0a81ab720"
//    private var retrofit: Retrofit? = null

//    init {
//        retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(getClient())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .build()
//    }
//
//    companion object {
//        private var networkingHelper: MovieClient? = null
//        fun getInstance(): MovieClient {
//            if (networkingHelper == null) {
//                networkingHelper = MovieClient()
//            }
//            return networkingHelper!!
//        }
//    }
//
//    private fun getClient(): OkHttpClient {
//        val client = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor())
//            .build()
//
//        return client
//    }
//
//    private fun loggingInterceptor(): Interceptor {
//        val interceptor = HttpLoggingInterceptor()
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//        return interceptor
//    }


//    fun getMovieApi(): MovieInterface {
//        return retrofit!!.create(MovieInterface::class.java)
//    }
}