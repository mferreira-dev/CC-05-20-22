package com.mfkf.codechallenge.presentation.shows.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfkf.codechallenge.domain.entities.Alias
import com.mfkf.codechallenge.domain.repositories.ShowsRemoteRepository
import com.mfkf.codechallenge.utils.Failure
import com.mfkf.codechallenge.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShowDetailsViewModel
@Inject
constructor(
	private val showsRemoteRepository: ShowsRemoteRepository
) : ViewModel() {

	private val _aliases = MutableLiveData<Result<List<Alias>, Failure>>()
	val aliases: LiveData<Result<List<Alias>, Failure>>
		get() = _aliases

	fun fetchAliases(showId: Int) {
		viewModelScope.launch {
			val result = showsRemoteRepository.fetchAliases(showId)

			_aliases.value = when (result) {
				is Result.Success -> Result.Success(result.data)
				is Result.Error -> Result.Error(result.message)
			}
		}
	}

}