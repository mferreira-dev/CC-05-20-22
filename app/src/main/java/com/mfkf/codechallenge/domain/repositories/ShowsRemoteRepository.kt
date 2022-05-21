package com.mfkf.codechallenge.domain.repositories

import com.mfkf.codechallenge.data.remote.models.Alias
import com.mfkf.codechallenge.data.remote.models.Media

interface ShowsRemoteRepository {
	suspend fun searchShows(query: String): List<Media>
	suspend fun singleSearchShows(query: String): List<Media>
	suspend fun fetchAliases(id: Int): List<Alias>
}