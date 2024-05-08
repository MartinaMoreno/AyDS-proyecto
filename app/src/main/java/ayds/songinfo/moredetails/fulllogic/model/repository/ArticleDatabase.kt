package ayds.songinfo.moredetails.fulllogic.model.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import ayds.songinfo.moredetails.fulllogic.ArticleDao
import ayds.songinfo.moredetails.fulllogic.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun ArticleDao(): ArticleDao
}