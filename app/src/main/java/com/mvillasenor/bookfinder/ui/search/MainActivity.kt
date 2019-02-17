package com.mvillasenor.bookfinder.ui.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mvillasenor.bookfinder.R
import com.mvillasenor.bookfinder.domain.SearchResult
import com.mvillasenor.bookfinder.extensions.observeWith
import com.mvillasenor.bookfinder.ui.common.Resource
import com.mvillasenor.bookfinder.ui.common.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val searchVewModel: SearchViewModel by viewModel()
    private val searchController = SearchController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchVewModel.searchResults
            .observeWith(this, ::onSearchResult)

        searchResults.layoutManager = LinearLayoutManager(this)
        searchResults.adapter = searchController.adapter
    }

    override fun onResume() {
        super.onResume()
        searchVewModel.onSearch("lord")
    }

    private fun onSearchResult(searchResult: Resource<SearchResult>) {
        when (searchResult.status) {
            Status.ERROR -> searchResult.message?.let { showError(it) }
            Status.SUCCESS -> searchResult.data?.let { searchController.setData(it.books) }
        }
    }

    private fun showError(error: String) {
        Snackbar.make(mainContainer, error, Snackbar.LENGTH_SHORT).show()
    }
}
