package guuilp.github.com.coollibraries.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query

/**
 * Created by guilh on 2/16/2018.
 */
@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie") fun getMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE) fun insertMovie(movie: Movie)
}