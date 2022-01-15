package com.example.movieapp.ui.details

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.LinearInterpolator
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import coil.load
import com.example.movieapp.R
import com.example.movieapp.data.base.BASE_IMAGE_URL
import com.example.movieapp.data.viewmodels.DetailsViewModel
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.flaviofaria.kenburnsview.RandomTransitionGenerator

class DetailsFragment : Fragment() {
    private lateinit var moviesDetailsViewModel: DetailsViewModel
    //private lateinit var binding: FragmentDetailsBinding
    private  var _binding: FragmentDetailsBinding?=null
    private val binding get() = _binding!!
    private lateinit var trailerId: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesDetailsViewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val posterPath=arguments?.get("posterPath").toString()
        val id=arguments?.getInt("id")!!

        moviesDetailsViewModel.getMoviesDetails(id)
        moviesDetailsViewModel.getMovieCountry(id)


        binding.moviesBackGroundImage.load(BASE_IMAGE_URL +posterPath)
        val backGroundImage=binding.moviesBackGroundImage
        val interpolator= LinearInterpolator()
        val generator = RandomTransitionGenerator(10000,interpolator)
        backGroundImage.setTransitionGenerator(generator)
        backGroundImage.load(BASE_IMAGE_URL +posterPath)


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
            binding.movieOverViewTextView.text=it?.overview
            binding.currentPointTextView.text=it?.voteAverage.toString()
            binding.movieCountry.text=it?.title  //test
        })

//        moviesDetailsViewModel.movieCountry.observe(viewLifecycleOwner,{
//            binding.movieCountry.text=it?.name
//        })
    }

//    override fun onDetach() {
//        super.onDetach()
//        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//    }
override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
}