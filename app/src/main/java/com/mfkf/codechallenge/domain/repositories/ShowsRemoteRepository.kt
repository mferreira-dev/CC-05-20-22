package com.mfkf.codechallenge.domain.repositories

import com.mfkf.codechallenge.domain.entities.Alias
import com.mfkf.codechallenge.domain.entities.Media
import com.mfkf.codechallenge.utils.Result
import com.mfkf.codechallenge.utils.Failure

interface ShowsRemoteRepository : BaseRemoteRepository {
	suspend fun searchShow(query: String): Result<List<Media>, Failure>
	suspend fun fetchAliases(id: Int): Result<List<Alias>, Failure>
}