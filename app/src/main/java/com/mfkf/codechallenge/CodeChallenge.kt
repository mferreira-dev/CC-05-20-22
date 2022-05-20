package com.mfkf.codechallenge

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CodeChallenge : Application() {

	companion object {
		var instance: CodeChallenge? = null
		fun applicationContext(): Context = instance!!.applicationContext
	}

	override fun onCreate() {
		super.onCreate()
		instance = this
	}

}