package com.example.futureimageloader.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.futureimageloader.BuildConfig
import com.example.futureimageloader.databinding.ItemMovieBinding
import com.example.futureimageloader.model.Movie
import com.example.futureimageloader.util.loadImageFromUrl

class TmdbAdapter(private val items: List<Movie>, private val listener: (Movie) -> Unit) : RecyclerView.Adapter<TmdbAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    inner class DataViewHolder(private val itemMovieBinding: ItemMovieBinding) : RecyclerView.ViewHolder(itemMovieBinding.root) {
        fun bind(movie: Movie, listener: (Movie) -> Unit) = with(itemMovieBinding) {
            movie.posterPath?.let {
                val imgUrl = "${BuildConfig.BASE_IMAGE_URL}/$it"
                ivMovie.loadImageFromUrl(imgUrl)
            }
            movie.title?.let { tvTitle.text = it }
            movie.overview?.let { tvDescription.text = it }
            root.setOnClickListener {
                listener(movie)
            }
        }
    }
}