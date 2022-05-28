package com.mfkf.codechallenge.presentation.container

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.mfkf.codechallenge.R
import com.mfkf.codechallenge.databinding.ActivityContainerBinding
import com.mfkf.codechallenge.presentation.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContainerActivity : BaseActivity() {

	private lateinit var binding: ActivityContainerBinding
	private lateinit var navController: NavController
	private lateinit var appBarConfiguration: AppBarConfiguration
	lateinit var viewModel: ContainerViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityContainerBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}

	override fun setupUI() {
		val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
		navController = navHostFragment.findNavController()

		// Up button won't be displayed on these fragments.
		appBarConfiguration = AppBarConfiguration(setOf(R.id.showsFragment))
		setupActionBarWithNavController(navController, appBarConfiguration)
	}

	override fun setupObservers() {
		viewModel.isLoading.observe(this) {
			if (it.hasNotBeenHandled()) {
				binding.loadingProgressContainer.visibility =
					if (it.peekContent()) View.VISIBLE else View.GONE
			}
		}
	}

}