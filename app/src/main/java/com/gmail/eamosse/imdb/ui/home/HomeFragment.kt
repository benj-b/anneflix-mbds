package com.gmail.eamosse.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import com.gmail.eamosse.imdb.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(homeViewModel) {
            token.observe(viewLifecycleOwner) {
                getCategories()
            }

            categories.observe(viewLifecycleOwner) {
                binding.categoryList.adapter = CategoryAdapter(it) { category ->
                    val action = HomeFragmentDirections.actionHomeFragmentToHomeSecondFragment(category.id.toString(), category.name)
                    findNavController(view).navigate(action)
                }
            }

            error.observe(viewLifecycleOwner) {
                //afficher l'erreur
            }
        }
    }
}
