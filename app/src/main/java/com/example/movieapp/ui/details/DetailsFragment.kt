package com.example.movieapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import coil.load
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.moviesapp.data.base.BASE_IMAGE_URL
import com.example.moviesapp.data.viewmodels.DetailsViewModel
import com.flaviofaria.kenburnsview.RandomTransitionGenerator

class DetailsFragment : Fragment() {
    private lateinit var moviesDetailsViewModel: DetailsViewModel
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var trailerId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_details, container, false)
        return view

        val posterPath=arguments?.get("posterPath").toString()
        val id=arguments?.getInt("id")!!

        moviesDetailsViewModel.getMoviesDetails(id)
        binding.moviesBackGroundImage.load(BASE_IMAGE_URL +posterPath)
        val backGroundImage=binding.moviesBackGroundImage
        val interpolator= LinearInterpolator()
        val generator = RandomTransitionGenerator(10000,interpolator)
        backGroundImage.setTransitionGenerator(generator)
        backGroundImage.load(BASE_IMAGE_URL +posterPath)


        moviesDetailsViewModel.getMovieCountry(id)


        binding.playButton.setOnClickListener {
            if (::trailerId.isInitialized){
                val bundle= bundleOf("url" to trailerId)
                it.findNavController().navigate(R.id.action_detailsFragment5_to_videoPlayerFragment,bundle,null,null)
            }
        }


//        binding.favoriteIcon.setOnClickListener {
//
//        }

        moviesDetailsViewModel.trailerId.observe(viewLifecycleOwner,{
            trailerId=it?.key?:""
        })

        moviesDetailsViewModel.movieDetails.observe(viewLifecycleOwner,{
            binding.movieName.text=it?.title
            binding.overviewText.text=it?.overview
        })

        moviesDetailsViewModel.movieCountry.observe(viewLifecycleOwner,{
            binding.movieCountry.text=it?.name
        })
    }

    override fun onDetach() {
        super.onDetach()
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
}