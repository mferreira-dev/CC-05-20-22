package com.mfkf.codechallenge.utils

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import androidx.core.util.Preconditions
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.mfkf.codechallenge.HiltTestActivity

inline fun <reified T : Fragment> launchFragmentInHiltContainer(
	fragmentArgs: Bundle? = null,
	themeResId: Int = androidx.fragment.testing.R.style.FragmentScenarioEmptyFragmentActivityTheme,
	fragmentFactory: FragmentFactory? = null,
	crossinline action: T.() -> Unit = {}
) {
	val mainActivityIntent = Intent.makeMainActivity(
		ComponentName(
			ApplicationProvider.getApplicationContext(),
			HiltTestActivity::class.java
		)
	).putExtra(K.FRAGMENT_SCENARIO, themeResId)

	ActivityScenario.launch<HiltTestActivity>(mainActivityIntent).onActivity { activity ->
		fragmentFactory?.let {
			activity.supportFragmentManager.fragmentFactory = it
		}

		val fragment = activity.supportFragmentManager.fragmentFactory.instantiate(
			Preconditions.checkNotNull(T::class.java.classLoader),
			T::class.java.name
		)

		fragment.arguments = fragmentArgs

		activity.supportFragmentManager.beginTransaction()
			.add(android.R.id.content, fragment, String.empty())
			.commitNow()

		(fragment as T).action()
	}
}