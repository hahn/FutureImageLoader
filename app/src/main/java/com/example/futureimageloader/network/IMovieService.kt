package com.example.futureimageloader.network

import com.example.futureimageloader.model.Movie
import com.example.futureimageloader.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IMovieService {

    @GET("movie/popular")
    fun fetchPopularMovies(): Call<MovieResponse>

    @GET("movie/{id}")
    fun fetchMovieDetailById(@Path("id") id: Int): Call<Movie>
}