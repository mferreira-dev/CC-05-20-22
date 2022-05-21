package com.mfkf.codechallenge.data.remote.repositories

import com.mfkf.codechallenge.data.remote.Endpoints
import com.mfkf.codechallenge.data.remote.models.Alias
import com.mfkf.codechallenge.data.remote.models.Media
import com.mfkf.codechallenge.domain.repositories.ShowsRemoteRepository
import javax.inject.Inject

class ShowsRemoteRepositoryImpl
@Inject
constructor(private val endpoints: Endpoints) : ShowsRemoteRepository {
	override suspend fun searchShows(query: String): List<Media> = endpoints.searchShows(query)
	override suspend fun singleSearchShows(query: String): List<Media> = endpoints.singleSearchShows(query)
	override suspend fun fetchAliases(id: Int): List<Alias> = endpoints.fetchAliases(id)
}