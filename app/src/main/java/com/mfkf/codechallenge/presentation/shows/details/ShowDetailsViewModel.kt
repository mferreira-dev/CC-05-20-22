package com.mfkf.codechallenge.presentation.shows.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfkf.codechallenge.data.remote.models.Alias
import com.mfkf.codechallenge.domain.usecases.ShowsRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel
@Inject
constructor(private val showsRemoteUseCase: ShowsRemoteUseCase) : ViewModel() {

	private val _aliases = MutableStateFlow<List<Alias>?>(null)
	val aliases = _aliases.asStateFlow()

	fun fetchAliases(showId: Int) {
		viewModelScope.launch {
			showsRemoteUseCase.fetchAliases(showId).either(
				success = {
					_aliases.value = it
				},
				failure = {}
			)
		}
	}

}