package com.example.made.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.domain.model.Game
import com.example.core.ui.GameAdapter
import com.example.made.R
import com.example.made.databinding.FragmentHomeBinding
import com.example.made.ui.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameAdapter = GameAdapter { game -> navigateToDetail(game) }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = gameAdapter
        }

        homeViewModel.games.observe(viewLifecycleOwner) { resource ->
            resource.data?.let { games ->
                gameAdapter.updateGames(games)
            }
        }

        binding.buttonFavorite.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_favoriteActivity)
        }
    }

    private fun navigateToDetail(game: Game) {
        val intent = Intent(requireContext(), DetailActivity::class.java)
        intent.putExtra("gameId", game.id)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}