package com.example.futureimageloader.feature.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.futureimageloader.BuildConfig
import com.example.futureimageloader.R
import com.example.futureimageloader.databinding.ActivityMovieDetailBinding
import com.example.futureimageloader.model.Movie
import com.example.futureimageloader.network.ApiClient
import com.example.futureimageloader.util.loadImageFromUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieId = intent.getIntExtra("id", 0)
        loadMovieDetail(movieId)
    }

    private fun loadMovieDetail(id: Int) {
        val call = ApiClient.getClient.fetchMovieDetailById(id)
        call.enqueue(object : Callback<Movie>{
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val movie = response.body()
                    with(binding) {
                        ivBackgrop.loadImageFromUrl("${BuildConfig.BASE_IMAGE_URL}${movie?.backdropPath}")
                        tvTitle.text = movie?.title
                        tvDescription.text = movie?.overview
                        var genres = ""
                        movie?.genres?.forEach {
                            genres += "${it.name}, "
                        }
                        tvGenres.text = "Genre: $genres"
                    }
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {

            }
        })
    }
 }