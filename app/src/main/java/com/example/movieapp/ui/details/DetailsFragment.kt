package com.example.movieapp.ui.details

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    private var favorite = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moviesDetailsViewModel = ViewModelProvider(this)[DetailsViewModel::class.java]
        favoriteViewModel = ViewModelProvider(this)[FavoriteViewModel::class.java]
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val id = arguments?.getInt("id")!!

        moviesDetailsViewModel.getMoviesDetails(id)
        moviesDetailsViewModel.getTrailerMovie(id)

//        context?.let { favoriteViewModel.insertMovie(it) }
//        context?.let { favoriteViewModel.deleteMovie(it, id) }

        favoriteViewModel.isFav(id)



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

        favoriteViewModel.fav.observe(viewLifecycleOwner) {
            favorite=it
            if (it) {
                binding.favIv.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                binding.favIv.setImageResource(R.drawable.ic_baseline_favorite_border_24)
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


        moviesDetailsViewModel.trailerId.observe(viewLifecycleOwner, {
            Log.d(TAG, "onViewCreated: " + it.toString())
            trailerKey = it?.key ?: ""    //Elvis -->if key ==null return empty string if not return key
        })

        moviesDetailsViewModel.movieDetails.observe(viewLifecycleOwner, {
            binding.movieOverViewTextView.text = it?.overview
            binding.currentPointTextView.text = it?.voteAverage.toString()
            binding.titleTv.text = it?.title
            binding.movieReleaseDate.text = it?.releaseDate
            binding.statusTv.text = it?.status

            binding.favIv.setOnClickListener {v->
                if (!favorite){
                    binding.favIv.setImageResource(R.drawable.ic_baseline_favorite_24)
                    context?.let { it1 -> it?.let { it2 -> favoriteViewModel.insertMovie(it1, it2) } }
                }else{
                    binding.favIv.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                    context?.let { it1 -> favoriteViewModel.deleteMovie(it1,id) }
                }
                favorite=!favorite
            }

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