package com.mfkf.codechallenge.presentation.container

import android.os.Bundle
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

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityContainerBinding.inflate(layoutInflater)
		setContentView(binding.root)
	}

	override fun setupUI() {
		val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
		navController = navHostFragment.findNavController()

		// Up button will not be displayed on these fragments.
		appBarConfiguration = AppBarConfiguration(setOf(R.id.showsFragment))

		setSupportActionBar(binding.containerToolbar)
		setupActionBarWithNavController(navController, appBarConfiguration)
	}

}