package com.mfkf.codechallenge.utils

sealed class Either<out L, out R>{
	data class Success<L>(val response: L) : Either<L, Nothing>()
	data class Error<R>(val reason: R) : Either<Nothing, R>()

	fun either(success: (L) -> Unit, error: (R) -> Unit) {
		when (this) {
			is Success -> { success(response) }
			is Error -> { error(reason) }
		}
	}
}