package com.mfkf.codechallenge.data.remote.di

import com.mfkf.codechallenge.data.remote.Endpoints
import com.mfkf.codechallenge.data.remote.repositories.ShowsRemoteRepositoryImpl
import com.mfkf.codechallenge.domain.repositories.ShowsRemoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {
	@Singleton
	@Provides
	fun provideShowsRemoteRepositoryImpl(endpoints: Endpoints): ShowsRemoteRepository =
		ShowsRemoteRepositoryImpl(endpoints)
}