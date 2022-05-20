package com.mfkf.codechallenge.utils

sealed class Failure : Throwable() {
	object NoConnectivity : Failure()
	object ServerError : Failure()
}