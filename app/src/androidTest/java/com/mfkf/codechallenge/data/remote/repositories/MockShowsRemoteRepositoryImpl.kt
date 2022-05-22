package com.mfkf.codechallenge.data.remote.repositories

import com.mfkf.codechallenge.domain.entities.Alias
import com.mfkf.codechallenge.domain.entities.Alias.Country
import com.mfkf.codechallenge.domain.entities.Media
import com.mfkf.codechallenge.domain.entities.Media.Show
import com.mfkf.codechallenge.domain.repositories.ShowsRemoteRepository
import com.mfkf.codechallenge.utils.Failure
import com.mfkf.codechallenge.utils.Result

class MockShowsRemoteRepositoryImpl : ShowsRemoteRepository {

	private var shouldCallBeSuccessful = false

	val mockMediaList = listOf(
		Media(9.0, Show(id = 1, name = "Some show", language = "English")),
		Media(8.0, Show(id = 2, name = "Some other show", language = "English"))
	)

	val mockAliasList = listOf(
		Alias("Some alias", Country("US")),
		Alias("Some other alias", Country("US"))
	)

	override suspend fun searchShow(query: String): Result<List<Media>, Failure> =
		if (shouldCallBeSuccessful)
			Result.Success(mockMediaList)
		else
			Result.Error(Failure.ServerError)

	override suspend fun fetchAliases(id: Int): Result<List<Alias>, Failure> =
		if (shouldCallBeSuccessful)
			Result.Success(mockAliasList)
		else
			Result.Error(Failure.ServerError)

	fun prepareSuccess() {
		shouldCallBeSuccessful = true
	}

	fun prepareError() {
		shouldCallBeSuccessful = false
	}

}