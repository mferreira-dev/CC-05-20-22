package com.mfkf.codechallenge.presentation.shows.details

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mfkf.codechallenge.R
import com.mfkf.codechallenge.data.remote.models.Media
import com.mfkf.codechallenge.databinding.FragmentShowDetailsBinding
import com.mfkf.codechallenge.utils.lifecycleCollectLatest
import com.mfkf.codechallenge.utils.removeHTML
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowDetailsBottomSheet : BottomSheetDialogFragment() {

	companion object {
		private const val SHOW_DETAILS = "show_details"

		fun newInstance(media: Media) =
			ShowDetailsBottomSheet().apply {
				arguments = Bundle().apply {
					putParcelable(SHOW_DETAILS, media)
				}
			}
	}

	private var _binding: FragmentShowDetailsBinding? = null
	private val binding get() = _binding!!
	private lateinit var viewModel: ShowDetailsViewModel

	private lateinit var media: Media

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		arguments?.getParcelable<Media>(SHOW_DETAILS)?.let {
			media = it
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentShowDetailsBinding.inflate(inflater, container, false)
		viewModel = ViewModelProvider(this)[ShowDetailsViewModel::class.java]
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupUI()
		setupObservers()
	}

	private fun setupUI() {
		binding.apply {
			if (media.show?.image == null)
				showDetailsNoPosterImage.visibility = View.VISIBLE
			else {
				media.show?.let {
					Glide.with(requireContext())
						.load(it.image?.medium)
						.into(showDetailsPoster)
				}
			}

			if (media.show?.status == requireContext().getString(R.string.ended)) {
				showDetailsStatus.setTextColor(Color.parseColor("#ff0000"))
				showDetailsStatus.setTypeface(null, Typeface.BOLD);
			}

			showDetailsTitle.text = media.show?.name
			showDetailsStatus.text = media.show?.status
			showDetailsPosterLoading.visibility = View.GONE

			media.show?.rating?.average?.let {
				showDetailsRatingLayout.visibility = View.VISIBLE
				showDetailsRating.text = it.toString()
			}
			media.show?.summary?.let { showDetailsSummary.text = String.removeHTML(it) }
			media.show?.id?.let { viewModel.fetchAliases(it) }
		}
	}

	private fun setupObservers() {
		lifecycleCollectLatest(viewModel.aliases) {
			it?.let {
				if (it.isNotEmpty())
					binding.showDetailsAliasesLbl.visibility = View.VISIBLE

				it.forEachIndexed { index, alias ->
					val text = binding.showDetailsAliases.text

					if (index == 0)
						binding.showDetailsAliases.text = "${alias.name}"
					else
						binding.showDetailsAliases.text = "$text, ${alias.name}"
				}
			}
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}

}