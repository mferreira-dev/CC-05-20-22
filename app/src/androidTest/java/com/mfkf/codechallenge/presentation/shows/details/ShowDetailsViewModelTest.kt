package com.mfkf.codechallenge.presentation.shows.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.mfkf.codechallenge.data.remote.repositories.MockShowsRemoteRepositoryImpl
import com.mfkf.codechallenge.utils.Result
import com.mfkf.codechallenge.utils.getOrAwaitValue
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ShowDetailsViewModelTest {

	@get:Rule
	val instantTaskRule = InstantTaskExecutorRule()

	private lateinit var showDetailsViewModel: ShowDetailsViewModel
	private val mockShowsRemoteRepositoryImpl = MockShowsRemoteRepositoryImpl()

	@Before
	fun setUp() {
		showDetailsViewModel = ShowDetailsViewModel(mockShowsRemoteRepositoryImpl)
	}

	@Test
	fun test_fetchAliasesSuccess() = runBlockingTest {
		mockShowsRemoteRepositoryImpl.prepareSuccess()

		showDetailsViewModel.fetchAliases(1)
		val result = showDetailsViewModel.aliases.getOrAwaitValue()

		assertThat(result is Result.Success).isTrue()
	}

	@Test
	fun test_fetchAliasesFailure() = runBlockingTest {
		mockShowsRemoteRepositoryImpl.prepareError()

		showDetailsViewModel.fetchAliases(1)
		val result = showDetailsViewModel.aliases.getOrAwaitValue()

		assertThat(result is Result.Error).isTrue()
	}

}