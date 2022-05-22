package com.mfkf.codechallenge.data.remote.repositories

import com.mfkf.codechallenge.data.remote.Endpoints
import com.mfkf.codechallenge.domain.entities.Alias
import com.mfkf.codechallenge.domain.entities.Media
import com.mfkf.codechallenge.domain.repositories.ShowsRemoteRepository
import com.mfkf.codechallenge.utils.Failure
import com.mfkf.codechallenge.utils.Result
import com.mfkf.codechallenge.utils.networkCall
import javax.inject.Inject

class ShowsRemoteRepositoryImpl
@Inject
constructor(private val endpoints: Endpoints) : ShowsRemoteRepository {

	override suspend fun searchShow(query: String): Result<List<Media>, Failure> =
		networkCall { endpoints.searchShows(query) }

	override suspend fun fetchAliases(id: Int): Result<List<Alias>, Failure> =
		networkCall { endpoints.fetchAliases(id) }

}