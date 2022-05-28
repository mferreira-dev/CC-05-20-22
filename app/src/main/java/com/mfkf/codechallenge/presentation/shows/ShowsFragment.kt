package com.mfkf.codechallenge.presentation.shows

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfkf.codechallenge.R
import com.mfkf.codechallenge.databinding.FragmentShowsBinding
import com.mfkf.codechallenge.presentation.base.BaseFragment
import com.mfkf.codechallenge.presentation.container.ContainerViewModel
import com.mfkf.codechallenge.presentation.shows.ShowsViewModel.*
import com.mfkf.codechallenge.utils.Result
import com.mfkf.codechallenge.utils.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowsFragment : BaseFragment() {

	private var _binding: FragmentShowsBinding? = null
	private val binding get() = _binding!!
	lateinit var fragmentViewModel: ShowsViewModel
	lateinit var activityViewModel: ContainerViewModel

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentShowsBinding.inflate(inflater, container, false)
		fragmentViewModel = ViewModelProvider(requireActivity())[ShowsViewModel::class.java]
		activityViewModel = ViewModelProvider(requireActivity())[ContainerViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupUI()
		setupButtons()
		setupObservers()
		setupButtons()

		setHasOptionsMenu(true)
	}

	override fun setupUI() {
		binding.showsList.layoutManager = LinearLayoutManager(
			requireContext(),
			LinearLayoutManager.HORIZONTAL,
			false
		)
	}

	override fun setupButtons() {

	}

	override fun setupObservers() {
		fragmentViewModel.viewState.observe(viewLifecycleOwner) {
			render(it)
		}

		fragmentViewModel.isLoading.observe(viewLifecycleOwner) {
			if (it.hasNotBeenHandled())
				activityViewModel.isLoading(it.peekContent())
		}

		fragmentViewModel.media.observe(viewLifecycleOwner) {
			(it as? Result.Success)?.apply {
				binding.showsList.adapter = ShowsAdapter(
					data,
					requireContext(),
					requireActivity().supportFragmentManager
				)
			}
		}
	}

	private fun render(viewState: ShowsViewState) {
		binding.showsLblGetStarted.visibility = if (viewState.getStarted) View.VISIBLE else View.GONE
		binding.showsLblNoResults.visibility = if (viewState.noResults) View.VISIBLE else View.GONE
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

	override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
		super.onCreateOptionsMenu(menu, inflater)
		inflater.inflate(R.menu.menu, menu)

		val searchItem = menu.findItem(R.id.action_search)
		val searchView = searchItem?.actionView as SearchView

		searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String?): Boolean {
				query?.let {
					view?.hideKeyboard()
					fragmentViewModel.searchShow(it)
				}
				return true
			}

			override fun onQueryTextChange(newText: String?): Boolean {
				return true
			}
		})
	}

}