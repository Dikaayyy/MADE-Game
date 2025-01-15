package com.example.made.ui.detail

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.core.data.Resource
import com.example.core.domain.model.Game
import com.example.made.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val gameId = intent.extras?.getInt("gameId") ?: return

        // Observe the LiveData from ViewModel
        detailViewModel.getGameDetail(gameId).observe(this) { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let { game ->
                        populateGameDetails(game)
                    } ?: showError("Game details not found")
                }
                is Resource.Error -> {
                    showError(resource.message ?: "An unknown error occurred")
                }
                is Resource.Loading -> {
                    // Handle loading state if needed
                }
            }
        }
    }

    private fun populateGameDetails(game: Game) {
        binding.apply {
            tvName.text = game.name
            tvReleased.text = game.released
            tvRating.text = game.rating.toString()
            tvDescription.text = game.description
            Glide.with(this@DetailActivity)
                .load(game.backgroundImage)
                .into(ivBackgroundImage)
        }
    }

    private fun showError(message: String) {
        // Show error message to the user
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}