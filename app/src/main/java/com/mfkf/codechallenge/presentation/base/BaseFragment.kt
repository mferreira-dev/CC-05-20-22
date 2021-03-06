package com.mfkf.codechallenge.presentation.base

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
	abstract fun setupUI()
	abstract fun setupButtons()
	abstract fun setupObservers()
}