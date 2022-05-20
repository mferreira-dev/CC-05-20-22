package com.mfkf.codechallenge.data.remote.di

import com.mfkf.codechallenge.CodeChallenge
import com.mfkf.codechallenge.data.remote.Endpoints
import com.mfkf.codechallenge.data.remote.utils.ConnectivityInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object EndpointsModule {

	private const val BASE_URL = "https://api.tvmaze.com/"

	@Singleton
	@Provides
	fun provideEndpoints(okHttpClient: OkHttpClient, moshi: Moshi): Endpoints =
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(MoshiConverterFactory.create(moshi))
			.build()
			.create(Endpoints::class.java)

	@Singleton
	@Provides
	fun provideOkHttpClient(): OkHttpClient {
		val logging = HttpLoggingInterceptor()
		logging.setLevel(HttpLoggingInterceptor.Level.BODY)

		return OkHttpClient.Builder()
			.connectTimeout(1, TimeUnit.MINUTES)
			.addInterceptor(logging)
			.addInterceptor(ConnectivityInterceptor(CodeChallenge.applicationContext()))
			.readTimeout(30, TimeUnit.SECONDS)
			.build()
	}

	@Singleton
	@Provides
	fun provideMoshi(): Moshi = Moshi.Builder().build()

}