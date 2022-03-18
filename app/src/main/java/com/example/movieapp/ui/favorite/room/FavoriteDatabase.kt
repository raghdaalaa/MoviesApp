package com.example.movieapp.ui.favorite.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.movieapp.data.api.model.MovieDetails

@Database(entities = [MovieDetails::class], version = 2)
abstract class FavoriteDatabase :RoomDatabase() {

    abstract fun movieDao(): FavoriteDao

companion object{
    @Volatile
    private var INSTANCE: FavoriteDatabase?=null
    fun getInstance(context: Context): FavoriteDatabase {
        kotlin.synchronized(this){
            if (INSTANCE == null){
                INSTANCE=Room.databaseBuilder(context.applicationContext,
                    FavoriteDatabase::class.java,"Movies_Database")
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
        return INSTANCE!!
    }
}
}