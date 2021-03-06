package com.srk.nytimesmostpopular.ui.mostpopular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.srk.nytimesmostpopular.data.remote.model.MostPopularResult
import com.srk.nytimesmostpopular.databinding.MostPopularDetailFragmentBinding
import com.srk.nytimesmostpopular.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_bar_main.*

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
        val data = arguments?.getParcelable<MostPopularResult>("mostPopular") as MostPopularResult
        viewModel.mostPopular.postValue(data)
        updateToolbarTitle(data)
        return binding.root
    }

    private fun updateToolbarTitle(data: MostPopularResult) {
        (activity as MainActivity).toolbar.setTitle(data.title)
    }

}