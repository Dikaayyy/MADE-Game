package com.example.favorites

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.favorites.databinding.ActivityFavoriteBinding
import com.example.made.di.FavoriteModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity: ComponentActivity() {

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }

    private var _binding: ActivityFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        initView()
    }

    private fun inject() {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
    }

    private fun initView() {
        _binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Contoh inisialisasi tampilan, tambahkan sesuai kebutuhan
        binding.someTextView.text = "Favorite Activity"
    }
}