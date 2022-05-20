package com.mfkf.codechallenge.presentation.shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.mfkf.codechallenge.databinding.FragmentShowsBinding
import com.mfkf.codechallenge.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowsFragment : BaseFragment() {

	private var _binding: FragmentShowsBinding? = null
	private val binding get() = _binding!!
	private val viewModel: ShowsViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentShowsBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupUI()
		setupButtons()
		setupObservers()
		setupButtons()
	}

	override fun setupUI() {

	}

	override fun setupButtons() {

	}

	override fun setupObservers() {

	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

}