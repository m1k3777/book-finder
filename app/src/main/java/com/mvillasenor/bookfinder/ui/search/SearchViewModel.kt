package com.mvillasenor.bookfinder.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvillasenor.bookfinder.domain.SearchResult
import com.mvillasenor.bookfinder.ui.common.Resource
import kotlinx.coroutines.*
import retrofit2.HttpException
import timber.log.Timber

class SearchViewModel(
    mainCoroutineDispatcher: CoroutineDispatcher,
    private val searchBook: (String, Int) -> Deferred<SearchResult>
) : ViewModel() {

    val searchResults = MutableLiveData<Resource<SearchResult>>()

    private val viewModelJob = Job()
    private val coroutineContext = viewModelJob + mainCoroutineDispatcher

    fun onSearch(query: String) {
        searchResults.postValue(Resource.loading())
        viewModelJob.cancelChildren()
        CoroutineScope(coroutineContext).launch(coroutineContext) {
            try {
                val result = searchBook(query, 1).await()
                searchResults.postValue(Resource.success(result))
            } catch (e: HttpException) {
                Timber.e(e, "Error performing search")
                searchResults.postValue(Resource.error("Error loading search results"))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancelChildren()
    }
}
