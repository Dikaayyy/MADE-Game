package com.example.made.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.GameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val gameUseCase: GameUseCase) : ViewModel() {
    fun getGameDetail(id: Int) = gameUseCase.getDetailGame(id).asLiveData()
}