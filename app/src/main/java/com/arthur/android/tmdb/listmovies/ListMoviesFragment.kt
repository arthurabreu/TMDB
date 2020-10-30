package com.arthur.android.tmdb.listmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.arthur.android.tmdb.R
import com.arthur.android.tmdb.databinding.FragmentListMoviesBinding
import com.arthur.android.tmdb.di.observeLifecycleIn
import com.arthur.android.tmdb.listmovies.adapter.ItemsAdapter
import com.arthur.android.tmdb.utils.SPAN_COUNT
import org.koin.android.viewmodel.ext.android.viewModel

class ListMoviesFragment : Fragment() {

    private val listMoviesViewModel: ListMoviesViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.observeLifecycleIn(listMoviesViewModel)
        val activityMainBinding: FragmentListMoviesBinding? =
            DataBindingUtil.inflate(inflater, R.layout.fragment_list_movies, container, false)

        val view = activityMainBinding?.root
        activityMainBinding?.run {
            this.viewModel = listMoviesViewModel
            initRecycler(activityMainBinding)
            lifecycleOwner = this@ListMoviesFragment
        }
        val toolbar = activity?.findViewById(R.id.toolbar_main) as? Toolbar
        toolbar?.visibility = View.VISIBLE

        return view
    }

    private fun initRecycler(
        activityMainBinding: FragmentListMoviesBinding
    ) {
        val layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, SPAN_COUNT)

        val recyclerMovies = activityMainBinding.recyclerMovies
        recyclerMovies.layoutManager = layoutManager
        recyclerMovies.hasFixedSize()
        recyclerMovies.adapter = ItemsAdapter()
        recyclerMovies.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                context,
                layoutManager.orientation
            )
        )
    }
}
