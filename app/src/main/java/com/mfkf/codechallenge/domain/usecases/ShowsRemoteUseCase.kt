package com.mfkf.codechallenge.domain.usecases

import com.mfkf.codechallenge.data.remote.models.Media
import com.mfkf.codechallenge.data.remote.repositories.ShowsRemoteRepositoryImpl
import com.mfkf.codechallenge.data.remote.utils.NoConnectivityException
import com.mfkf.codechallenge.utils.Either
import com.mfkf.codechallenge.utils.Failure
import javax.inject.Inject

class ShowsRemoteUseCase
@Inject
constructor(private var showsRemoteRepositoryImpl: ShowsRemoteRepositoryImpl) {

	suspend fun searchShows(query: String): Either<List<Media>, Failure> =
		try {
			Either.Success(showsRemoteRepositoryImpl.searchShows(query))
		} catch (ex: NoConnectivityException) {
			Either.Failure(Failure.NoConnectivity)
		} catch (ex: Exception) {
			println(ex.localizedMessage)
			Either.Failure(Failure.ServerError)
		}


	suspend fun singleSearchShows(query: String): Either<List<Media>, Failure> =
		try {
			Either.Success(showsRemoteRepositoryImpl.singleSearchShows(query))
		} catch (ex: NoConnectivityException) {
			Either.Failure(Failure.NoConnectivity)
		} catch (ex: Exception) {
			Either.Failure(Failure.ServerError)
		}

}