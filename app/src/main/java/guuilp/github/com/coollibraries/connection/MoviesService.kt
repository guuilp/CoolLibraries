package guuilp.github.com.coollibraries.connection

import guuilp.github.com.coollibraries.model.Movies
import guuilp.github.com.coollibraries.utils.URLUtils
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit


/**
 * Created by guilh on 2/16/2018.
 */
interface MoviesService {

    @GET("movie/popular")
    fun getPopularMovies(): Observable<Movies>

    /**
     * Companion object for the factory
     */
    companion object Factory {
        fun create(): MoviesService {

            val logging = HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY)

            val httpClient = OkHttpClient.Builder()
                    .readTimeout(90, TimeUnit.SECONDS)
                    .writeTimeout(90, TimeUnit.SECONDS)
                    .connectTimeout(90, TimeUnit.SECONDS)
                    .addInterceptor { chain ->
                        val original = chain.request()

                        val originalHttpUrl = original.url()

                        val url = originalHttpUrl.newBuilder()
                                .addQueryParameter("api_key", "a0d80a5dc769f33ab12e8b06eaa06562")
                                .build()

                        val requestBuilder = original.newBuilder()
                                .url(url)

                        val request = requestBuilder.build()

                        chain.proceed(request)
                    }
                    .addInterceptor(logging)


            val retrofit = Retrofit.Builder()
                    .baseUrl(URLUtils.BASE_URL)
                    .addCallAdapterFactory(retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build()

            return retrofit.create(MoviesService::class.java)
        }
    }
}
