package com.mfkf.codechallenge.data.remote.models

import com.mfkf.codechallenge.utils.empty
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
	val score: Double = 0.0,
	val show: Show = Show()
) {
	@JsonClass(generateAdapter = true)
	data class Show(
		val id: Int = 0,
		val url: String = String.empty(),
		val name: String = String.empty(),
		val type: String = String.empty(),
		val language: String = String.empty(),
		val genres: List<String> = listOf(),
		val status: String = String.empty(),
		val runtime: Int = 0,
		val averageRuntime: Int = 0,
		val premiered: String = String.empty(),
		val ended: String = String.empty(),
		val officialSite: String = String.empty(),
		val schedule: Schedule = Schedule(),
		val rating: Rating = Rating(),
		val weight: Int = 0,
		val network: Network = Network(),
		val webChannel: WebChannel = WebChannel(),
		val dvdCountry: String = String.empty(), // confirm
		val externals: Externals = Externals(),
		val image: Image = Image(),
		val summary: String = String.empty(),
		val updated: Long = 0L,
		@Json(name = "_links")
		val links: Links = Links()
	) {
		@JsonClass(generateAdapter = true)
		data class Schedule(
			val time: String = String.empty(),
			val days: List<String> = listOf()
		)

		@JsonClass(generateAdapter = true)
		data class Rating(
			val average: Float = 0f
		)

		@JsonClass(generateAdapter = true)
		data class Network(
			val id: Int = 0,
			val name: String = String.empty(),
			val country: Country = Country(),
			val officialSite: String = String.empty()
		) {
			@JsonClass(generateAdapter = true)
			data class Country(
				val name: String = String.empty(),
				val code: String = String.empty(),
				val timezone: String = String.empty()
			)
		}

		@JsonClass(generateAdapter = true)
		data class WebChannel(
			val id: Int = 0,
			val name: String = String.empty(),
			val country: String = String.empty(), // Need to confirm.
			val officialSite: String = String.empty()
		)

		@JsonClass(generateAdapter = true)
		data class Externals(
			@Json(name = "tvrage")
			val tvRage: Int = 0,
			@Json(name = "thetvdb")
			val theTvDb: String = String.empty(),
			val imdb: String = String.empty()
		)

		@JsonClass(generateAdapter = true)
		data class Image(
			val medium: String = String.empty(),
			val original: String = String.empty()
		)

		@JsonClass(generateAdapter = true)
		data class Links(
			val self: Self = Self(),
			@Json(name = "nextepisode")
			val nextEpisode: NextEpisode = NextEpisode()
		) {
			@JsonClass(generateAdapter = true)
			data class Self(
				val href: String = String.empty()
			)

			@JsonClass(generateAdapter = true)
			data class NextEpisode(
				val href: String = String.empty()
			)
		}
	}
}