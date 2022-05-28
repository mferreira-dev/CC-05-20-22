package com.mfkf.codechallenge.data.remote.di

import android.content.Context
import com.mfkf.codechallenge.data.remote.Endpoints
import com.mfkf.codechallenge.data.remote.utils.ConnectivityInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
	fun provideEndpoints(
		okHttpClient: OkHttpClient,
		moshiConverterFactory: MoshiConverterFactory
	): Endpoints =
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(moshiConverterFactory)
			.build()
			.create(Endpoints::class.java)

	@Singleton
	@Provides
	fun provideOkHttpClient(
		connectivityInterceptor: ConnectivityInterceptor
	): OkHttpClient {
		val logging = HttpLoggingInterceptor()
		logging.setLevel(HttpLoggingInterceptor.Level.BODY)

		return OkHttpClient.Builder()
			.connectTimeout(1, TimeUnit.MINUTES)
			.addInterceptor(logging)
			.addInterceptor(connectivityInterceptor)
			.readTimeout(30, TimeUnit.SECONDS)
			.build()
	}

	@Singleton
	@Provides
	fun provideConnectivityInterceptor(@ApplicationContext context: Context): ConnectivityInterceptor =
		ConnectivityInterceptor(context)

	@Singleton
	@Provides
	fun provideMoshi(): Moshi = Moshi.Builder().build()

	@Singleton
	@Provides
	fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
		MoshiConverterFactory.create(moshi)

}