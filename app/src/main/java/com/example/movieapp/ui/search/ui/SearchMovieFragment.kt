package com.example.movieapp.ui.search.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.TextKeyListener
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.data.viewmodels.MovieListViewModel
import com.example.movieapp.databinding.FragmentSearchMovieBinding
import com.example.movieapp.ui.OnNavigate
import com.example.movieapp.ui.home.adapters.CustomAdapter

class SearchMovieFragment() : Fragment() ,CustomAdapter.OnItemClick{
    private lateinit var allDataViewModel:MovieListViewModel
    private lateinit var searchViewModel:SearchMovieViewModel
    private lateinit var binding: FragmentSearchMovieBinding

    private val broadcastReceiver=object : BroadcastReceiver(){
        override fun onReceive(p0: Context?, p1: Intent?) {
           // binding.queryEt.addTextChangedListener(textWatcher)

            if (binding.queryEt.text.isEmpty()){ // means no search operation
               findNavController().navigateUp()  // back to previous page
            }else{ // means there is search operation
                binding.queryEt.text.clear()
                findNavController().navigateUp()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSearchMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        allDataViewModel=ViewModelProvider(this)[MovieListViewModel::class.java]
        searchViewModel=ViewModelProvider(this)[SearchMovieViewModel::class.java]

        val lm= LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvResults.layoutManager=lm
        val searchResultAdapter = CustomAdapter(this)
        binding.rvResults.adapter=searchResultAdapter


//Search
        searchViewModel.listSearchMovies.observe(viewLifecycleOwner){
            searchResultAdapter.clear()
            searchResultAdapter.appendMoreMovies(it.results)
        }

        binding.searchBtn.setOnClickListener {

            if (binding.queryEt.text.isEmpty()){
               binding.queryEt.setError("Field cannot be blank")
            }
            searchViewModel.getSearchResult(binding.queryEt.text.toString())
        }

        // broadcast receiver
        context ?.registerReceiver(broadcastReceiver, IntentFilter("back_action"))
    }

    override fun onDestroy() {
        super.onDestroy()
        context?.unregisterReceiver(broadcastReceiver)
    }

    override fun onItemClick(id: Int) {
        val bundle= Bundle()
        bundle.putInt("id" ,id)
        findNavController().navigate(R.id.action_searchMovieFragment_to_detailsFragment5,bundle)
    }

    override fun onItemLongClick(id: Int) {
        TODO("Not yet implemented")
    }

//    private val textWatcher = object : TextWatcher {
//        override fun afterTextChanged(s: Editable?) {
//            if (s != null) {
//                if (s.isEmpty()){
//// load data
//                }else{
//                    s.clear()
//                    //load data
//                }
//            }
//        }
//        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//        }
//        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//
//        }
    }



