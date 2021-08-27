package work.vuong.github_profile_app.common.network

import android.widget.ImageView
import coil.load
import work.vuong.github_profile_app.R
import work.vuong.view_components.common.imageloader.ImageLoader

class CoilImageLoader(val url: String): ImageLoader {

    override fun load(imageView: ImageView) {
        imageView.load(url) {
            crossfade(true)
            this.placeholder(R.drawable.image_placeholder)
        }
    }

}