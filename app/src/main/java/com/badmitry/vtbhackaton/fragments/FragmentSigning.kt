package com.badmitry.vtbhackaton.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.badmitry.vtbhackaton.MainActivity
import com.badmitry.vtbhackaton.R
import com.badmitry.vtbhackaton.databinding.FragmentSigningBinding
import com.badmitry.vtbhackaton.navigation.Screens
import com.badmitry.vtbhackaton.viewmodules.FragmentSigningViewModel
import javax.inject.Inject


class FragmentSigning : BaseFragment() {
    private lateinit var binding: FragmentSigningBinding
    private lateinit var viewModel: FragmentSigningViewModel

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSigningBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnOption.setOnClickListener { viewModel.replaceFragment(Screens.MAIN) }
        binding.btnPortfolioGraph.setOnClickListener { viewModel.replaceFragment(Screens.MAIN) }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, vmFactory)[FragmentSigningViewModel::class.java]
        viewModel.observe(this, ::onProgress, ::onError)
        viewModel.liveData.observe(this, ::onDataChanged)
    }

    override fun setToolbar() {
        (requireActivity() as MainActivity).initToolbar(R.string.app_name, false)
    }

    private fun onDataChanged(int: Int) {

    }
}