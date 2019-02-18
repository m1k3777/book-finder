package com.mvillasenor.bookfinder.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvillasenor.bookfinder.R
import com.mvillasenor.bookfinder.domain.SearchResult
import com.mvillasenor.bookfinder.extensions.observeWith
import com.mvillasenor.bookfinder.ui.common.Resource
import com.mvillasenor.bookfinder.ui.common.Status
import com.mvillasenor.bookfinder.ui.search.recycler.SearchController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_error.*
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
        searchController.setData(emptyList())

        searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                searchVewModel.onSearch(s.toString())
            }
        })
        retry.setOnClickListener { searchVewModel.onSearch(searchText.text.toString()) }
    }

    private fun onSearchResult(searchResult: Resource<SearchResult>) {
        when (searchResult.status) {
            Status.ERROR -> searchResult.message?.let { showError(it) }
            Status.SUCCESS -> searchResult.data?.let {
                animator.displayedChild = 0
                searchController.setData(it.books)
            }
            else -> animator.displayedChild = 1
        }
    }

    private fun showError(error: String) {
        animator.displayedChild = 2
        errorMessage.text = error
    }
}
