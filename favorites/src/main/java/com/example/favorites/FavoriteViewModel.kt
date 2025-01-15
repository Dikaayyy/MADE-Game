package com.example.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.GameUseCase

class FavoriteViewModel (gameUseCase: GameUseCase) : ViewModel() {
    val game = gameUseCase.getFavoriteGames().asLiveData()
}