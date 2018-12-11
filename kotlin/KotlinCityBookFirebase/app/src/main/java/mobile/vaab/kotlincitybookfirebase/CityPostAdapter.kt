package mobile.vaab.kotlincitybookfirebase

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.custom_view.view.*

class CityPostAdapter (private val posts: ArrayList<CityPost>, private val context: Activity) : ArrayAdapter<CityPost>(context,R.layout.custom_view, posts) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutInflater = context.layoutInflater

        val customView = layoutInflater.inflate(R.layout.custom_view,null,true)

        customView.customUserName.text = "Autor: ${posts[position].author}"
        customView.customCommentText.text = posts[position].cityName

        Picasso
            .with(context)
            .load(posts[position].image)
            .into(customView.customImageView)

        return customView
    }
}
