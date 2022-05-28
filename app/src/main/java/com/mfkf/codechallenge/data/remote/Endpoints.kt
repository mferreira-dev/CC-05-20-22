package com.mfkf.codechallenge.data.remote

import com.mfkf.codechallenge.domain.entities.Alias
import com.mfkf.codechallenge.domain.entities.Media
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoints {

	@GET("search/shows")
	suspend fun searchShows(
		@Query("q") query: String
	): List<Media>

	@GET("singlesearch/shows")
	suspend fun singleSearchShows(
		@Query("q") query: String
	): List<Media>

	@GET("shows/{id}/akas")
	suspend fun fetchAliases(
		@Path("id") id: Int
	): List<Alias>

}