package com.badmitry.vtbhackaton.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.badmitry.vtbhackaton.MainActivity
import com.badmitry.vtbhackaton.R
import com.badmitry.vtbhackaton.databinding.FragmentCreditFieldsBinding
import com.badmitry.vtbhackaton.viewmodules.FragmentCreditViewModel
import javax.inject.Inject


class FragmentCreditFields : BaseFragment() {
    private lateinit var binding: FragmentCreditFieldsBinding
    private lateinit var viewModel: FragmentCreditViewModel

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
        binding = FragmentCreditFieldsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ServiceCast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
    }

    private fun initComponent() {
        binding.btnSigning.setOnClickListener {
            viewModel.creditApplication()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, vmFactory)[FragmentCreditViewModel::class.java]
        viewModel.observe(this, ::onProgress, ::onError)
        viewModel.liveData.observe(this, ::onDataChanged)
    }

    private fun onDataChanged(data: Int) {

    }

    override fun setToolbar() {
        (requireActivity() as MainActivity).initToolbar(R.string.input_application, true)
    }
}