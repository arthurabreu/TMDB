package com.arthur.android.tmdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.arthur.android.tmdb.databinding.ActivityMainBinding
import com.arthur.android.tmdb.search.SearchProvider
import kotlinx.android.synthetic.main.activity_main.searchView
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null
    private val searchProvider: SearchProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding: ActivityMainBinding? = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        searchView?.setOnSearchClickListener {
            navController?.navigate(R.id.searchFragment)
        }

        binding?.run {
            searchProvider = this@MainActivity.searchProvider
        }

        navController?.navigate(R.id.fragment_list_movies)
    }
}