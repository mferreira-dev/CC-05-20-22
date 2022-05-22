package com.mfkf.codechallenge.presentation

import com.mfkf.codechallenge.presentation.shows.ShowsFragmentTest
import com.mfkf.codechallenge.presentation.shows.ShowsViewModelTest
import com.mfkf.codechallenge.presentation.shows.details.ShowDetailsViewModelTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
	ShowsViewModelTest::class,
	ShowDetailsViewModelTest::class,
	ShowsFragmentTest::class
)
class ShowTestSuite