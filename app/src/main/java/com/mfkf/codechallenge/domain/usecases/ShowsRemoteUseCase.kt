package com.mfkf.codechallenge.domain.usecases

import com.mfkf.codechallenge.data.remote.models.Alias
import com.mfkf.codechallenge.data.remote.models.Media
import com.mfkf.codechallenge.data.remote.repositories.ShowsRemoteRepositoryImpl
import com.mfkf.codechallenge.utils.Either
import com.mfkf.codechallenge.utils.Failure
import com.mfkf.codechallenge.utils.networkCall
import javax.inject.Inject

class ShowsRemoteUseCase
@Inject
constructor(private var showsRemoteRepositoryImpl: ShowsRemoteRepositoryImpl) : BaseUseCase() {

	suspend fun searchShows(query: String): Either<List<Media>, Failure> =
		networkCall {
			showsRemoteRepositoryImpl.searchShows(query)
		}

	suspend fun singleSearchShows(query: String): Either<List<Media>, Failure> =
		networkCall {
			showsRemoteRepositoryImpl.singleSearchShows(query)
		}

	suspend fun fetchAliases(id: Int): Either<List<Alias>, Failure> =
		networkCall {
			showsRemoteRepositoryImpl.fetchAliases(id)
		}

}