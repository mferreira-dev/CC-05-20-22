package com.mfkf.codechallenge.presentation.shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfkf.codechallenge.domain.entities.Media
import com.mfkf.codechallenge.domain.repositories.ShowsRemoteRepository
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

	private val _media = MutableLiveData<Result<List<Media>, Failure>>()
	val media: LiveData<Result<List<Media>, Failure>>
		get() = _media

	fun searchShow(query: String) {
		viewModelScope.launch {
			val result = showsRemoteRepository.searchShow(query)

			_media.value = when (result) {
				is Result.Success -> Result.Success(result.data)
				is Result.Error -> Result.Error(result.message)
			}
		}
	}

}