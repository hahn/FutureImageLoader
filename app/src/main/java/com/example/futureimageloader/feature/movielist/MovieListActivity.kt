package com.example.futureimageloader.feature.movielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.futureimageloader.R
import com.example.futureimageloader.adapter.TmdbAdapter
import com.example.futureimageloader.databinding.ActivityMovieListBinding
import com.example.futureimageloader.feature.detail.MovieDetailActivity
import com.example.futureimageloader.model.Movie
import com.example.futureimageloader.model.MovieResponse
import com.example.futureimageloader.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadMovieList()
    }

    private fun loadMovieList() {
        showProgressbar(true)
        val call = ApiClient.getClient.fetchPopularMovies()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                showProgressbar(false)
                if (response.isSuccessful) {
                    val movieList = response.body()?.results
                    if (!movieList.isNullOrEmpty()) {
                        val movieAdapter = TmdbAdapter(movieList) { movie ->
                            val intent = Intent(this@MovieListActivity, MovieDetailActivity::class.java)
                            intent.putExtra("id", movie.id)
                            startActivity(intent)
                        }
                        with(binding.rvMovie) {
                            adapter = movieAdapter
                            layoutManager = LinearLayoutManager(this@MovieListActivity)
                            movieAdapter.notifyDataSetChanged()
                        }
                    }
                } else {
                    showToast("Failed")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                showToast("FaILURE: ${t.message}")
                showProgressbar(false)
                t.printStackTrace()
            }
        })
    }

    private fun showProgressbar(isShow: Boolean) {
        when(isShow) {
            true -> binding.progressbar.visibility = View.VISIBLE
            else -> binding.progressbar.visibility = View.GONE
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}