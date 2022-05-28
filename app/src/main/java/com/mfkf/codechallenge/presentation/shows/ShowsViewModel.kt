package com.mfkf.codechallenge.presentation.shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfkf.codechallenge.domain.entities.Media
import com.mfkf.codechallenge.domain.repositories.ShowsRemoteRepository
import com.mfkf.codechallenge.utils.Event
import com.mfkf.codechallenge.utils.Failure
import com.mfkf.codechallenge.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel
@Inject
constructor(
	private val showsRemoteRepository: ShowsRemoteRepository
) : ViewModel() {

	private val _isLoading = MutableLiveData<Event<Boolean>>()
	val isLoading: LiveData<Event<Boolean>>
		get() = _isLoading

	val _viewState = ShowsViewState()
	val viewState = MutableLiveData<ShowsViewState>()

	private val _media = MutableLiveData<Result<List<Media>, Failure>>()
	val media: LiveData<Result<List<Media>, Failure>>
		get() = _media

	data class ShowsViewState(
		val getStarted: Boolean = true,
		val noResults: Boolean = false,
		val requestError: Boolean = false
	)

	fun searchShow(query: String) {
		viewModelScope.launch {
			_isLoading.postValue(Event(true))
			val result = showsRemoteRepository.searchShow(query)

			_media.value = when (result) {
				is Result.Success -> {
					if (result.data.isEmpty())
						viewState.value = ShowsViewState(
							getStarted = false,
							noResults = true,
							requestError = false
						)
					else
						viewState.value = ShowsViewState(getStarted = false, noResults = false)

					Result.Success(result.data)
				}
				is Result.Error -> {
					viewState.value = ShowsViewState(getStarted = false, requestError = true)
					Result.Error(result.message)
				}
			}

			_isLoading.postValue(Event(false))
		}
	}

}