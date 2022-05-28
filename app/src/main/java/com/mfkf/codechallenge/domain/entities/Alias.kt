package com.mfkf.codechallenge.domain.entities

import com.mfkf.codechallenge.utils.empty
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Alias(
	val name: String? = String.empty(),
	val country: Country? = Country()
) {
	@JsonClass(generateAdapter = true)
	data class Country(
		val name: String? = String.empty(),
		val code: String? = String.empty(),
		val timezone: String? = String.empty()
	)
}