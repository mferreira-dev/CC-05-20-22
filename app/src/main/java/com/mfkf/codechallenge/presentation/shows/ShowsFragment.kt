package com.mfkf.codechallenge.presentation.shows

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfkf.codechallenge.databinding.FragmentShowsBinding
import com.mfkf.codechallenge.presentation.base.BaseFragment
import com.mfkf.codechallenge.utils.Result
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowsFragment : BaseFragment() {

	private var _binding: FragmentShowsBinding? = null
	private val binding get() = _binding!!
	lateinit var viewModel: ShowsViewModel

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentShowsBinding.inflate(inflater, container, false)
		viewModel = ViewModelProvider(requireActivity())[ShowsViewModel::class.java]
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
		binding.showsList.layoutManager = LinearLayoutManager(
			requireContext(),
			LinearLayoutManager.HORIZONTAL,
			false
		)

		viewModel.searchShow("pokemon")
	}

	override fun setupButtons() {

	}

	override fun setupObservers() {
		viewModel.media.observe(viewLifecycleOwner) {
			(it as? Result.Success)?.apply {
				binding.showsList.adapter = ShowsAdapter(
					data,
					requireContext(),
					requireActivity().supportFragmentManager
				)
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

}