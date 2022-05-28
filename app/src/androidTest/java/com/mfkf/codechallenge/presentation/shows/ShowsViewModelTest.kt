package com.mfkf.codechallenge.presentation.shows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.mfkf.codechallenge.data.remote.repositories.MockShowsRemoteRepositoryImpl
import com.mfkf.codechallenge.utils.Result
import com.mfkf.codechallenge.utils.getOrAwaitValue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShowsViewModelTest {

	@get:Rule
	val instantTaskRule = InstantTaskExecutorRule()

	private lateinit var showsViewModel: ShowsViewModel
	private val mockShowsRemoteRepositoryImpl = MockShowsRemoteRepositoryImpl()

	@Before
	fun setUp() {
		showsViewModel = ShowsViewModel(mockShowsRemoteRepositoryImpl)
	}

	@Test
	fun test_searchShowSuccess() = runBlockingTest {
		mockShowsRemoteRepositoryImpl.prepareSuccess()

		showsViewModel.searchShow("Some show")
		val result = showsViewModel.media.getOrAwaitValue()

		assertThat(result is Result.Success).isTrue()
	}

	@Test
	fun test_searchShowFailure() = runBlockingTest {
		mockShowsRemoteRepositoryImpl.prepareError()

		showsViewModel.searchShow("Some show")
		val result = showsViewModel.media.getOrAwaitValue()

		assertThat(result is Result.Error).isTrue()
	}

}