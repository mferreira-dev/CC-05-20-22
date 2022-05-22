package com.mfkf.codechallenge.presentation.shows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.mfkf.codechallenge.R
import com.mfkf.codechallenge.data.remote.repositories.MockShowsRemoteRepositoryImpl
import com.mfkf.codechallenge.presentation.shows.ShowsAdapter.*
import com.mfkf.codechallenge.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ShowsFragmentTest {

	@get:Rule
	val instantTaskRule = InstantTaskExecutorRule()

	@get:Rule
	var hiltRule = HiltAndroidRule(this)

	private lateinit var showsViewModel: ShowsViewModel
	private val mockShowsRemoteRepositoryImpl = MockShowsRemoteRepositoryImpl()

	@Before
	fun setUp() {
		showsViewModel = ShowsViewModel(mockShowsRemoteRepositoryImpl)
	}

	@Test
	fun test_listLoaded() = runBlockingTest {
		launchFragmentInHiltContainer<ShowsFragment> {
			viewModel = showsViewModel
		}

		mockShowsRemoteRepositoryImpl.prepareSuccess()
		showsViewModel.searchShow("Some show")

//		onView(withId(R.id.shows_list)).check(matches(hasItemAtPosition(0, withText("Test Text"))))
	}

	@Test
	fun test_navigateToShowDetails() = runBlockingTest {
		launchFragmentInHiltContainer<ShowsFragment> {
			viewModel = showsViewModel
		}

		mockShowsRemoteRepositoryImpl.prepareSuccess()
		showsViewModel.searchShow("Some show")

		onView(withId(R.id.shows_list)).perform(actionOnItemAtPosition<ViewHolder>(0, click()))
		onView(withId(R.id.show_details_title)).check(matches(withText(mockShowsRemoteRepositoryImpl.mockAliasList.first().name)))
	}

}