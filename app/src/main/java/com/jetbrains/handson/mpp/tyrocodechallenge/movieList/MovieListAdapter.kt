package com.jetbrains.handson.mpp.tyrocodechallenge.movieList


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jetbrains.handson.mpp.tyrocodechallenge.databinding.MovieListItemBinding
import com.jetbrains.handson.mpp.tyrocodechallenge.netWork.Movie

class MovieListAdapter(val onClickListener: OnClickListener): ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(DiffCallback) {


    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.imdbID == newItem.imdbID
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieListAdapter.MovieViewHolder {
        return MovieViewHolder(MovieListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: MovieListAdapter.MovieViewHolder, position: Int) {
        val movie = getItem(position)
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(movie)
        }
    }

    class MovieViewHolder(private var binding: MovieListItemBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
        }
    }

    class OnClickListener(val clickListener: (movie:Movie) -> Unit) {
        fun onClick(movie:Movie) = clickListener(movie)
    }
}
