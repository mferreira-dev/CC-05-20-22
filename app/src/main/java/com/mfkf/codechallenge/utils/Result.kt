package com.mfkf.codechallenge.utils

/**
 *
 * Network request response handler class.
 */
sealed class Result<out L, out R> {
	data class Success<L>(val data: L) : Result<L, Nothing>()
	data class Error<R>(val message: R) : Result<Nothing, R>()

	fun either(success: (L) -> Unit, error: (R) -> Unit) {
		when (this) {
			is Success -> { success(data) }
			is Error -> { error(message) }
		}
	}
}