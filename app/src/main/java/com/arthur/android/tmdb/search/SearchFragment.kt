package com.arthur.android.tmdb.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.arthur.android.tmdb.R
import com.arthur.android.tmdb.databinding.SearchFragmentBinding
import com.arthur.android.tmdb.di.observeLifecycleIn
import com.arthur.android.tmdb.search.adapter.SearchAdapter
import com.arthur.android.tmdb.utils.SPAN_COUNT
import org.koin.android.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {

    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.observeLifecycleIn(searchViewModel)
        val activityMainBinding: SearchFragmentBinding? =
            DataBindingUtil.inflate(inflater, R.layout.search_fragment, container, false)

        val view = activityMainBinding?.root
        activityMainBinding?.run {
            this.viewModel = searchViewModel
            initRecycler(activityMainBinding)
            lifecycleOwner = this@SearchFragment
        }

        return view
    }

    private fun initRecycler(
        activityMainBinding: SearchFragmentBinding
    ) {
        val layoutManager = androidx.recyclerview.widget.GridLayoutManager(context, SPAN_COUNT)

        val recyclerMovies = activityMainBinding.searchList
        recyclerMovies.layoutManager = layoutManager
        recyclerMovies.hasFixedSize()
        recyclerMovies.adapter = SearchAdapter()
        recyclerMovies.addItemDecoration(
            androidx.recyclerview.widget.DividerItemDecoration(
                context,
                layoutManager.orientation
            )
        )
    }
}
