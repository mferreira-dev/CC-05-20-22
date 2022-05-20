package com.mfkf.codechallenge.data.remote.utils

import okio.IOException

object NoConnectivityException : IOException() {
	override val message: String = "NoConnectivityException"
}