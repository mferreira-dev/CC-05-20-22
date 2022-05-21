package com.mfkf.codechallenge.presentation.shows

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfkf.codechallenge.data.remote.models.Media
import com.mfkf.codechallenge.domain.usecases.ShowsRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowsViewModel
@Inject
constructor(private val showsRemoteUseCase: ShowsRemoteUseCase) : ViewModel() {

	private val _results = MutableStateFlow<List<Media>?>(null)
	val results = _results.asStateFlow()

	init {
		viewModelScope.launch { searchShows("lord of the rings") }
	}

	private suspend fun searchShows(query: String) {
		showsRemoteUseCase.searchShows(query).either(
			success = {
				_results.value = it
			},
			failure = {}
		)
	}

}