package com.rascarlo.astroeye.detail

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.rascarlo.astroeye.databinding.FragmentDetailBinding
import timber.log.Timber

/**
 * fragment to hold [DetailViewModel]
 */
class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // application
        val application: Application = requireNotNull(activity).application
        // binding
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val recentImage = DetailFragmentArgs.fromBundle(requireArguments()).recentImage
        val viewModelFactory = DetailViewModelFactory(recentImage, application)
        binding.viewModel =
            ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
        binding.executePendingBindings()
        Timber.d(recentImage.toString())
        // Inflate the layout for this fragment
        return binding.root
    }
}