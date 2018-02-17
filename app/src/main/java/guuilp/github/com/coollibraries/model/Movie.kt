package guuilp.github.com.coollibraries.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Movie")
data class Movie(

		@PrimaryKey
		@NonNull
		@field:SerializedName("id")
		@ColumnInfo(name = "id")
		var id: Int? = null,

		@field:SerializedName("overview")
		@ColumnInfo(name = "overview")
		var overview: String? = null,

		@field:SerializedName("original_language")
		@ColumnInfo(name = "originalLanguage")
		var originalLanguage: String? = null,

		@field:SerializedName("original_title")
		@ColumnInfo(name = "originalTitle")
		var originalTitle: String? = null,

		@field:SerializedName("video")
		@ColumnInfo(name = "video")
		var video: Boolean? = null,

		@field:SerializedName("title")
		@ColumnInfo(name = "title")
		var title: String? = null,

		@field:SerializedName("genre_ids")
		@Ignore
		var genreIds: List<Int?>? = null,

		@field:SerializedName("poster_path")
		@ColumnInfo(name = "posterPath")
		var posterPath: String? = null,

		@field:SerializedName("backdrop_path")
		@ColumnInfo(name = "backdropPath")
		var backdropPath: String? = null,

		@field:SerializedName("release_date")
		@ColumnInfo(name = "releaseDate")
		var releaseDate: String? = null,

		@field:SerializedName("vote_average")
		@ColumnInfo(name = "voteAverage")
		var voteAverage: Double? = null,

		@field:SerializedName("popularity")
		@ColumnInfo(name = "popularity")
		var popularity: Double? = null,

		@field:SerializedName("adult")
		@ColumnInfo(name = "adult")
		var adult: Boolean? = null,

		@field:SerializedName("vote_count")
		@ColumnInfo(name = "voteCount")
		var voteCount: Int? = null
)