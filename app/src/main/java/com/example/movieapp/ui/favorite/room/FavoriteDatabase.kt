package com.example.movieapp.ui.favorite.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteModel::class], version = 1)
abstract class FavoriteDatabase :RoomDatabase() {

    abstract fun movieDao(): FavoriteDao
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