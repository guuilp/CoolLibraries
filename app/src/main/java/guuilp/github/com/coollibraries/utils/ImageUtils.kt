package guuilp.github.com.coollibraries.utils

import android.net.Uri

/**
 * Created by guilh on 2/16/2018.
 */
class ImageUtils{
    companion object {
        fun buildCoverUrl(size: String, posterPath: String): String {
            var posterPath = posterPath

            posterPath = posterPath.substring(1)

            val builtUri = Uri.parse(URLUtils.COVER_IMAGE_URL).buildUpon()
                    .appendPath(size)
                    .appendPath(posterPath)
                    .build()

            return builtUri.toString()
        }
    }
}