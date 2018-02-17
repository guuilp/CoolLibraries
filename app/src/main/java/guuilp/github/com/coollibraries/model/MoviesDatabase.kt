package guuilp.github.com.coollibraries.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

/**
 * Created by guilh on 2/16/2018.
 */
@Database(entities = arrayOf(Movie::class), version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun taskDao(): MovieDao

    companion object {

        private var INSTANCE: MoviesDatabase? = null

        private val lock = Any()

        fun getInstance(context: Context): MoviesDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MoviesDatabase::class.java, "Movies.db")
                            .build()
                }
                return INSTANCE!!
            }
        }
    }

}