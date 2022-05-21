package com.mfkf.codechallenge.utils

import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mfkf.codechallenge.presentation.base.BaseFragment
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

/**
 * Static String type function that returns an empty string.
 *
 * @return An empty string.
 */
fun String.Companion.empty() = ""

/**
 *
 * Generic function that initializes an observer.
 *
 * @param flow Data stream to be observed.
 * @param collect Higher-order function to be used as the flow's callback.
 */
fun <T> BaseFragment.lifecycleCollectLatest(flow: Flow<T>, collect: suspend (T) -> Unit) {
	lifecycleScope.launchWhenStarted {
		flow.collectLatest(collect)
	}
}

/**
 *
 * Generic function to initialize a RecyclerView using a PagingDataAdapter.
 *
 * @param adapter Adapter to be set to the RecyclerView. Must inherit from PagingDataAdapter
 * @param recyclerView RecyclerView to be used.
 */
fun <A, B, C : PagingDataAdapter<A, B>, D : RecyclerView> BaseFragment.setupPagingRecyclerView(adapter: C, recyclerView: D) {
	recyclerView.apply {
		setHasFixedSize(true)
		this.adapter = adapter
	}
}