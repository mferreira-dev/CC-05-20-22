package com.mfkf.codechallenge.presentation.shows

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.mfkf.codechallenge.R
import com.mfkf.codechallenge.databinding.ItemShowsBinding
import com.mfkf.codechallenge.domain.entities.Media
import com.mfkf.codechallenge.presentation.shows.ShowsAdapter.ShowViewHolder
import com.mfkf.codechallenge.presentation.shows.details.ShowDetailsBottomSheet

class ShowsAdapter(
	private val showsList: List<Media>,
	private val ctx: Context,
	private val fragmentManager: FragmentManager
) : Adapter<ShowViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder =
		ShowViewHolder(
			ItemShowsBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)

	override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
		holder.bind(showsList[position])
	}

	override fun getItemCount(): Int {
		return showsList.size
	}

	inner class ShowViewHolder(private val binding: ItemShowsBinding) : ViewHolder(binding.root) {

		fun bind(media: Media) {
			binding.apply {
				if (media.show?.image == null)
					itemShowsNoPosterImage.visibility = View.VISIBLE
				else
					Glide.with(ctx)
						.load(media.show.image.medium)
						.into(itemShowsPoster)

				if (media.show?.status == ctx.getString(R.string.ended)) {
					itemShowsStatus.setTextColor(Color.parseColor("#ff0000"))
					itemShowsStatus.setTypeface(null, Typeface.BOLD);
				}

				itemShowsLayout.setOnClickListener {
					val fragment = ShowDetailsBottomSheet.newInstance(media)
					fragment.show(fragmentManager, null)
				}

				itemShowsTitle.text = media.show?.name
				itemShowsStatus.text = media.show?.status
				itemShowsPosterLoading.visibility = View.GONE
			}
		}

	}

}