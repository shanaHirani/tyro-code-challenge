package com.jetbrains.handson.mpp.tyrocodechallenge

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetbrains.handson.mpp.tyrocodechallenge.movieList.ApiStatus
import com.jetbrains.handson.mpp.tyrocodechallenge.movieList.MovieListAdapter
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.Movie

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieListAdapter
    adapter.submitList(data)
}

@BindingAdapter("movieApiStatus")
fun bindStatus(statusImageView: ImageView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        ApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        ApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
        ApiStatus.EMPTY_LISt -> {
            statusImageView.visibility = View.GONE
        }
        ApiStatus.START -> {
            statusImageView.visibility = View.GONE
        }

    }
}

@BindingAdapter("movieApiStatus")
fun bindStatus(statusTextView: TextView, status: ApiStatus?) {
    when (status) {
        ApiStatus.LOADING -> {
            statusTextView.visibility = View.GONE
        }
        ApiStatus.ERROR -> {
            statusTextView.visibility = View.GONE
        }
        ApiStatus.DONE -> {
            statusTextView.visibility = View.GONE
        }
        ApiStatus.EMPTY_LISt -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = statusTextView.resources
                .getString(R.string.no_Movie_Found)
        }
        ApiStatus.START -> {
            statusTextView.visibility = View.VISIBLE
            statusTextView.text = statusTextView.resources
                .getString(R.string.Please_input_your_title_in_search)
        }

    }
}