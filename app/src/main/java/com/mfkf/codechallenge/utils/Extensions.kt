package com.mfkf.codechallenge.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.mfkf.codechallenge.data.remote.utils.NoConnectivityException
import com.mfkf.codechallenge.domain.repositories.BaseRemoteRepository

/**
 * Returns an empty string.
 *
 * @return An empty string.
 */
fun String.Companion.empty() = ""

/**
 *
 * Network call response handler.
 *
 * @param action A higher-order function that represents a network call.
 * @return An instance of the Result sealed class according to the result.
 */
suspend fun <T> BaseRemoteRepository.networkCall(action: suspend () -> T): Result<T, Failure> {
	return try {
		Result.Success(action.invoke())
	} catch (ex: NoConnectivityException) {
		Result.Error(Failure.NoConnectivity)
	} catch (ex: Exception) {
		Result.Error(Failure.ServerError)
	}
}

/**
 *
 * Removes all HTML tags from a given string.
 *
 * @return A HTML tag-less string.
 */
fun String.Companion.removeHTML(oldString: String) =
	oldString.replace(Regex("\\<.*?\\>"), "")

fun View.hideKeyboard() {
	this.clearFocus()
	val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
	imm.hideSoftInputFromWindow(windowToken, 0)
}