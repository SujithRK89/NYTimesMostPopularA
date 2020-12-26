package com.srk.nytimesmostpopular.ui.mostpopular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.srk.nytimesmostpopular.data.remote.model.MostPopularResult
import com.srk.nytimesmostpopular.databinding.MostPopularDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MostPopularDetailFragment : Fragment() {

    companion object {
        fun newInstance() = MostPopularDetailFragment()
    }

    private lateinit var binding: MostPopularDetailFragmentBinding
    private val viewModel: MostPopularDetailViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = MostPopularDetailFragmentBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        viewModel.mostPopular.postValue(arguments?.getParcelable<MostPopularResult>("mostPopular") as MostPopularResult)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}