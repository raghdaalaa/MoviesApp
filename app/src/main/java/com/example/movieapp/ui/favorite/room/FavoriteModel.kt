package com.example.movieapp.ui.favorite.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies_table")
data class FavoriteModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo val movieId: Int,
    @ColumnInfo val title: String,
    @ColumnInfo val posterPath: String
)