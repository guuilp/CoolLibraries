package guuilp.github.com.coollibraries.activity

import android.arch.persistence.room.Room
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import guuilp.github.com.coollibraries.R
import guuilp.github.com.coollibraries.connection.MoviesService
import guuilp.github.com.coollibraries.item.MoviesListItem
import guuilp.github.com.coollibraries.model.Movies
import guuilp.github.com.coollibraries.model.MoviesDatabase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movies_list.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import java.net.UnknownHostException


class MoviesList : AppCompatActivity() {

    private var movieListItem = FastItemAdapter<MoviesListItem>()
    private val disposables = CompositeDisposable()

    private val moviesService by lazy {
        MoviesService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        setSupportActionBar(my_toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setupRecyclerView()

        if(isNetworkAvailable()) getMoviesListRemote()
        else getMoviesListLocal()
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    private fun setupRecyclerView(){
        recycler_view_movies.layoutManager = GridLayoutManager(this, 3)
        movieListItem.setHasStableIds(true)
        movieListItem.withSelectable(false)
        recycler_view_movies.adapter = movieListItem
    }

    private fun getMoviesListRemote(){
        disposables.add(moviesService.getPopularMovies()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .retry(3)
                .subscribe ({
                    result -> moviesListWork(result)
                }, { error ->
                    if(error is UnknownHostException) toast("You are offline. Try again later.")
                }))
    }

    private fun getMoviesListLocal(){
        val db = Room.databaseBuilder(applicationContext, MoviesDatabase::class.java, "Movies.db").build()
        doAsync {
            db.taskDao().getMovies().forEach {
                movieListItem.add(MoviesListItem(this@MoviesList, it))
            }
        }
    }

    private fun moviesListWork(response: Movies){
        val db = Room.databaseBuilder(applicationContext, MoviesDatabase::class.java, "Movies.db").build()

        movieListItem.clear()

        response.results?.forEach {
            doAsync {
                db.taskDao().insertMovie(it!!)
            }
            movieListItem.add(MoviesListItem(this, it))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}
