package com.jetbrains.handson.mpp.tyrocodechallenge.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.jetbrains.handson.mpp.tyrocodechallenge.R
import com.jetbrains.handson.mpp.tyrocodechallenge.databinding.FragmentDetailsBinding
import com.jetbrains.handson.mpp.tyrocodechallenge.movieList.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailsBinding.inflate(inflater)
        binding.lifecycleOwner = this
        //val movie = DetailsFragmentArgs.fromBundle(requireArguments()!!).selectedMovie
        //val viewModelFactory = DetailViewModelFactory(movie, application)
        //binding.viewModel = ViewModelProvider(
        //    this, viewModelFactory).get(DetailViewModel::class.java)
        binding.viewModel = viewModel
        return binding.root
    }

}