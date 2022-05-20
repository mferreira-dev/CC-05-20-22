package com.mfkf.codechallenge.utils

/**
 *
 * Network request response handler class.
 */
sealed class Either<out L, out R>{
	data class Success<L>(val response: L) : Either<L, Nothing>()
	data class Failure<R>(val reason: R) : Either<Nothing, R>()

	fun either(success: (L) -> Unit, failure: (R) -> Unit) {
		when (this) {
			is Success -> { success(response) }
			is Failure -> { failure(reason) }
		}
	}
}