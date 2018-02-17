package guuilp.github.com.coollibraries.item

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mikepenz.fastadapter.items.AbstractItem
import guuilp.github.com.coollibraries.R
import guuilp.github.com.coollibraries.model.Movie
import guuilp.github.com.coollibraries.utils.ImageUtils

/**
 * Created by guilh on 2/16/2018.
 */
class MoviesListItem(var context: Context, var movie: Movie?) : AbstractItem<MoviesListItem, MoviesListItem.ViewHolder>() {

    override fun getType(): Int {
        return R.id.movies_list_item_id
    }

    override fun getViewHolder(v: View?): ViewHolder {
        return ViewHolder(v)
    }

    override fun getLayoutRes(): Int {
        return R.layout.movies_list_item
    }

    override fun bindView(holder: ViewHolder?, payloads: MutableList<Any>?) {
        super.bindView(holder, payloads)

        if(movie?.posterPath != null) {
            Glide.with(context)
                    .asBitmap()
                    .load(ImageUtils.buildCoverUrl("w185", movie?.posterPath!!))
                    .into(holder?.movie_cover!!)
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        val movie_cover = itemView?.findViewById(R.id.iv_movie_cover) as ImageView
    }
}