package com.jetbrains.handson.mpp.tyrocodechallenge.movieList

import android.app.SearchManager
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.jetbrains.handson.mpp.tyrocodechallenge.MainActivity
import com.jetbrains.handson.mpp.tyrocodechallenge.R
import com.jetbrains.handson.mpp.tyrocodechallenge.databinding.FragmentMovieListBinding

class MovieListFragment : Fragment() {

    private val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMovieListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.listMovie.adapter = MovieListAdapter(MovieListAdapter.OnClickListener{
            viewModel.displayMovieDetails(it)
        })

        viewModel.navigateToSelectedMovie.observe(viewLifecycleOwner, Observer {
            if ( null != it ) {
                this.findNavController()
                    .navigate(MovieListFragmentDirections.actionMovieListFragmentToDetailsFragment(it))
                viewModel.displayMovieDetailsComplete()
            }
        })

        setHasOptionsMenu(true)
        return binding.root

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.search_menu, menu)
        //val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val manager = requireActivity().getSystemService("search") as SearchManager
        val search = menu?.findItem(R.id.search_movie)
        val searchview = search?.actionView as SearchView
        searchview.setSearchableInfo(manager.getSearchableInfo(requireActivity().componentName))
        searchview.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchview.clearFocus()
                searchview.setQuery("",false)
                search.collapseActionView()
                query?.let {viewModel.searchMovies(query)}
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }



}