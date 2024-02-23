package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.gmail.eamosse.imdb.databinding.FragmentHomeSecondBinding
import com.gmail.eamosse.imdb.ui.dashboard.DashboardFragmentDirections
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeSecondFragment : Fragment() {

    private val viewModel: HomeSecondViewModel by viewModels()
    private lateinit var binding: FragmentHomeSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeSecondBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        //binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryId = arguments?.getString("categoryId") ?: ""
        val categoryName = arguments?.getString("categoryName") ?: ""
        (activity as AppCompatActivity).supportActionBar?.title = "Films par catégories - $categoryName"

        val movieAdapter = MovieAdapter(listOf()) {
            navigateToDetailFragment(it.id)
        }

        with (viewModel) {
            token.observe(viewLifecycleOwner, Observer {
                //récupérer les catégories
                getMoviesByCategory(categoryId)
            })

            movies.observe(viewLifecycleOwner, Observer {
                movieAdapter.movies = it
                binding.movieList.adapter = movieAdapter
            })

            error.observe(viewLifecycleOwner, Observer {
                //afficher l'erreur
            })
        }
    }

    private fun navigateToDetailFragment(movieId: Int) {
        val action = HomeSecondFragmentDirections.actionHomeSecondFragmentToDetailFragment(movieId)
        findNavController().navigate(action)
    }

}