package com.example.futureimageloader.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.futureimageloader.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  companion object {
    private val urlDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    val view = binding.root
    setContentView(view)

    binding.btLoad.setOnClickListener {
      val url = if (binding.edUrl.text.toString().isEmpty()) {
        urlDefault
      } else {
        binding.edUrl.text.toString()
      }
      loadImage(url)
    }

  }

  private fun loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .into(binding.imageView)
  }
}