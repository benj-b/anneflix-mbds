package com.gmail.eamosse.imdb.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.ui.dashboard.DashboardFragmentDirections
import com.gmail.eamosse.imdb.ui.home.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModels()

    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_search, container, false)

        // Récupérer les références aux vues
        val editTextSearch: EditText = root.findViewById(R.id.editTextSearch)
        val buttonSearch: Button = root.findViewById(R.id.buttonSearch)

        // Ajouter un écouteur de clic au bouton de recherche
        buttonSearch.setOnClickListener {
            val searchTerm = editTextSearch.text.toString()
            performSearch(searchTerm)
        }

        // Initialiser la RecyclerView et l'adaptateur
        recyclerView = root.findViewById(R.id.recyclerViewMovies)
        movieAdapter = MovieAdapter(emptyList()) { movie ->
            navigateToDetailFragment(movie.id)
        }

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = movieAdapter

        return root
    }

    private fun performSearch(searchTerm: String) {
        searchViewModel.getMoviesSearch(searchTerm)

        searchViewModel.movies.observe(viewLifecycleOwner, Observer {
            movieAdapter.movies = it
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun navigateToDetailFragment(movieId: Int) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(movieId)
        findNavController().navigate(action)
    }
}
