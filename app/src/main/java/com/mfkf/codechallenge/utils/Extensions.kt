package com.mfkf.codechallenge.utils

import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mfkf.codechallenge.data.remote.utils.NoConnectivityException
import com.mfkf.codechallenge.domain.usecases.BaseUseCase
import com.mfkf.codechallenge.presentation.base.BaseFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

/**
 * Returns an empty string.
 *
 * @return An empty string.
 */
fun String.Companion.empty() = ""

/**
 *
 * Generic function that aids network call error handling.
 *
 * @param action A higher-order function that represents a network call.
 * @return An instance of the Either sealed class, Success and Failure respectively.
 */
suspend fun <T> BaseUseCase.networkCall(action: suspend () -> T): Either<T, Failure> {
	return try {
		Either.Success(action.invoke())
	} catch (ex: NoConnectivityException) {
		Either.Failure(Failure.NoConnectivity)
	} catch (ex: Exception) {
		Either.Failure(Failure.ServerError)
	}
}

/**
 *
 * Generic function that initializes a Kotlin flow observer.
 *
 * @param flow Data stream to be observed.
 * @param collect Higher-order function to be used as a callback.
 */
fun <T> BaseFragment.lifecycleCollectLatest(
	flow: Flow<T>,
	collect: suspend (T) -> Unit
) {
	lifecycleScope.launchWhenStarted {
		flow.collectLatest(collect)
	}
}

/**
 *
 * Generic function that initializes a Kotlin flow observer.
 *
 * @param flow Data stream to be observed.
 * @param collect Higher-order function to be used a callback.
 */
fun <T> BottomSheetDialogFragment.lifecycleCollectLatest(
	flow: Flow<T>,
	collect: suspend (T) -> Unit
) {
	lifecycleScope.launchWhenStarted {
		flow.collectLatest(collect)
	}
}

// TODO: Replace with regex.
/**
 *
 * Removes all HTML tags from a given string.
 *
 * @return A HTML tag-less string.
 */
fun String.Companion.removeHTML(oldString: String): String {
	var newString = oldString
	newString = newString.replace("<p>", "")
	newString = newString.replace("</p>", "")
	newString = newString.replace("<b>", "")
	newString = newString.replace("</b>", "")
	newString = newString.replace("<i>", "")
	newString = newString.replace("</i>", "")
	newString = newString.replace("<a>", "")
	newString = newString.replace("</a>", "")
	newString = newString.replace("<br />", "")
	return newString
}