package com.srk.nytimesmostpopular.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.srk.nytimesmostpopular.R
import com.srk.nytimesmostpopular.adapter.MostPopularAdapter
import com.srk.nytimesmostpopular.data.remote.model.MostPopularResult
import com.srk.nytimesmostpopular.databinding.FragmentHomeBinding
import com.srk.nytimesmostpopular.handler.MostPopularHandler
import com.srk.nytimesmostpopular.utils.EspressoIdlingResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), MostPopularHandler {

    private lateinit var adapter: MostPopularAdapter
    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = homeViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        addObserver()
    }

    private fun setUpRecyclerView() {

        adapter = MostPopularAdapter(this)
        binding.rvMostPopular.adapter = adapter
    }

    private fun addObserver() {

        homeViewModel.mostPopularResult.observe(viewLifecycleOwner, {
            adapter.setItems(it as ArrayList<MostPopularResult>)
            EspressoIdlingResource.decrement()
        })

        homeViewModel.errorMessage.observe(viewLifecycleOwner, {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
                .setAction(getString(R.string.retry), View.OnClickListener {
                    homeViewModel.getMostPopular()
                }).show()
        })
    }

    override fun onItemClick(mostPopularResult: MostPopularResult) {
        findNavController().navigate(HomeFragmentDirections.actionNavHomeToMostPopularDetailFragment(mostPopularResult))
    }
}