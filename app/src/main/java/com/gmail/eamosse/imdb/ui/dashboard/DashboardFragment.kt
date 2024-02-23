package com.gmail.eamosse.imdb.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.eamosse.imdb.databinding.FragmentDashboardBinding
import com.gmail.eamosse.imdb.ui.home.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private val viewModel: DashboardViewModel by viewModels()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MovieAdapter(listOf()) { movie ->
            navigateToDetailFragment(movie.id)
        }
        binding.moviesGrid.layoutManager = LinearLayoutManager(context)
        binding.moviesGrid.adapter = adapter

        // Observation et mise à jour du RecyclerView
        viewModel.movies.observe(viewLifecycleOwner) { movies ->
            adapter.movies = movies
            adapter.notifyDataSetChanged()
        }

        // Gestion des erreurs
        viewModel.error.observe(viewLifecycleOwner) { _ ->
            // Gérer l'erreur
        }
    }

    private fun navigateToDetailFragment(movieId: Int) {
        val action = DashboardFragmentDirections.actionDashboardFragementToDetailFragment(movieId)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
