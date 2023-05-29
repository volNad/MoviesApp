package com.example.moviesapp.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesapp.databinding.ActivityMoviesBinding
import com.example.moviesapp.view.adapters.CustomAdapter
import com.example.moviesapp.viewmodel.MoviesViewModel

class MoviesActivity : AppCompatActivity(), CustomAdapter.ItemClickListener {
    var binding: ActivityMoviesBinding? = null
    private val mViewModel: MoviesViewModel = MoviesViewModel()

    private lateinit var mMoviesRecycler: RecyclerView
    private lateinit var mMoviesAdapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        initViews()
        initObservers()
        mViewModel.getMovies()

    }

    private fun initObservers() {
        mViewModel.apply {
            movies.observe(this@MoviesActivity) {
                mMoviesAdapter = CustomAdapter(it, this@MoviesActivity)
                mMoviesRecycler.adapter = mMoviesAdapter
            }
        }
    }

    private fun initViews() {
        mMoviesRecycler = binding!!.recyclerView
        mMoviesRecycler.layoutManager = GridLayoutManager(this, 2)
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this@MoviesActivity, MoviesDetailsActivity::class.java)
        intent.putExtra("id", position)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}