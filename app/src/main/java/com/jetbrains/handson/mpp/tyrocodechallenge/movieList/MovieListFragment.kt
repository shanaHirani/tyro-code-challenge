package com.jetbrains.handson.mpp.tyrocodechallenge.movieList

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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

  /*
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_rent_menu -> MarsApiFilter.SHOW_RENT
                R.id.show_buy_menu -> MarsApiFilter.SHOW_BUY
                else -> MarsApiFilter.SHOW_ALL
            }
        )
        return true
    }*/

}