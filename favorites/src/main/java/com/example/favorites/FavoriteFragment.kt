//package com.example.favorites
//
//import android.content.Context
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import com.example.favorites.databinding.FragmentFavoriteBinding
//import com.example.made.di.FavoriteModuleDependencies
//import dagger.hilt.android.EntryPointAccessors
//import javax.inject.Inject
//
//class FavoriteFragment : Fragment() {
//
//    @Inject
//    lateinit var factory: ViewModelFactory
//    private val favoriteViewModel: FavoriteViewModel by viewModels { factory }
//
//    private var _binding: FragmentFavoriteBinding? = null
//    private val binding get() = _binding!!
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        val favoriteModuleDependencies = EntryPointAccessors.fromApplication(
//            context,
//            FavoriteModuleDependencies::class.java
//        )
//        DaggerFavoriteComponent.builder()
//            .context(context)
//            .appDependencies(favoriteModuleDependencies)
//            .build()
//            .inject(this)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
//        return binding.root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        favoriteViewModel.game.observe(viewLifecycleOwner) { games ->
//            // Update UI with favorite games
//        }
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//}