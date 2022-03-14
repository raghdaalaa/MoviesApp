package com.example.movieapp.ui.details

import android.os.Build
import android.os.Bundle
import android.util.Log
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
import coil.transform.RoundedCornersTransformation
import com.example.movieapp.R
import com.example.movieapp.data.base.BASE_IMAGE_URL
import com.example.movieapp.data.viewmodels.DetailsViewModel
import com.example.movieapp.databinding.FragmentDetailsBinding
import com.example.movieapp.ui.OnNavigate
import com.example.movieapp.ui.favorite.ui.FavoriteViewModel
import com.flaviofaria.kenburnsview.RandomTransitionGenerator

private const val TAG = "DetailsFragment"

class DetailsFragment : Fragment() {
    private lateinit var moviesDetailsViewModel: DetailsViewModel
    private lateinit var favoriteViewModel: FavoriteViewModel


    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var trailerKey: String
    private val favorite = false


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

        val id = arguments?.getInt("id")!!

        moviesDetailsViewModel.getMoviesDetails(id)
//        moviesDetailsViewModel.getMovieCountry(id)
        moviesDetailsViewModel.getTrailerMovie(id)



        binding.playButton.setOnClickListener {
            if (::trailerKey.isInitialized) {
                val bundle = Bundle()
                bundle.putString("TrailerKey", trailerKey)
                // onNavigte.onNavigationSelected(R.id.action_detailsFragment5_to_playerVideoFragment,bundle)
                it.findNavController().navigate(
                    R.id.action_detailsFragment5_to_playerVideoFragment,
                    bundle,
                    null,
                    null
                )
            }
        }

        //Favorite

        binding.favIv.setOnClickListener {
            if (favorite){
                binding.favIv.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                context?.applicationContext?.let {
                        it1 -> favoriteViewModel.insertMovie(it1) }
            }else{
                binding.favIv.setImageResource(R.drawable.ic_baseline_favorite_24)
                context?.let { it1 -> favoriteViewModel.deleteMovie(it1?.applicationContext,id) }
            }
        }

//        binding.favIv.setOnClickListener {
//            moviesDetailsViewModel.setFavorite(id, poster, title, favorite)
//            if (favorite) {
//                binding.favIv.setImageResource(R.drawable.ic_baseline_favorite_border_24)
//            } else {
//                binding.favIv.setImageResource(R.drawable.ic_baseline_favorite_24)
//            }
//            val bundle = Bundle()
//            bundle.putInt("MovieId", id)
//            it.findNavController()
//                .navigate(R.id.action_detailsFragment5_to_favoriteFragment, bundle)
//        }


        moviesDetailsViewModel.favorite.observe(viewLifecycleOwner) {
            if (it) {
                binding.favIv.setImageResource(R.drawable.ic_baseline_favorite_border_24)

            } else {
                binding.favIv.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
        }


        moviesDetailsViewModel.trailerId.observe(viewLifecycleOwner, {
            Log.d(TAG, "onViewCreated: " + it.toString())
            trailerKey = it?.key ?: ""
        })

        moviesDetailsViewModel.movieDetails.observe(viewLifecycleOwner, {
            binding.movieOverViewTextView.text = it?.overview
            binding.currentPointTextView.text = it?.voteAverage.toString()
            binding.titleTv.text = it?.title
            binding.movieReleaseDate.text = it?.releaseDate
            binding.statusTv.text = it?.status

            val backGroundImage = binding.moviesBackGroundImage
            val interpolator = LinearInterpolator()
            val generator = RandomTransitionGenerator(10000, interpolator)
            backGroundImage.setTransitionGenerator(generator)
            backGroundImage.load(BASE_IMAGE_URL + it?.backdropPath)

            binding.posterIv.load(BASE_IMAGE_URL + it?.posterPath) {
                crossfade(true)
                crossfade(500)
                transformations(RoundedCornersTransformation(20f))
            }

        })

    }
//
//override fun onDestroyView() {
//    super.onDestroyView()
//    _binding = null
//}
}