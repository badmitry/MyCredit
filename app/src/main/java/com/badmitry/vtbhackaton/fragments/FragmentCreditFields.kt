package com.badmitry.vtbhackaton.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.badmitry.data.DataSaver
import com.badmitry.domain.entities.yandexpartitions.Partitions
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
        binding.btnSendApplication.setOnClickListener {
            viewModel.creditApplication()
        }
        binding.layoutPartition.btnClose.visibility = View.GONE
        binding.layoutPartition.btnSendApplication.visibility = View.GONE
        binding.layoutPartition.tvTitle.text = getText(R.string.selected_partition)
        viewModel.getPartition()
        binding.params = DataSaver.instance.authData
        binding.tvGenderTitle.setOnClickListener{
            binding.llGender.visibility = View.VISIBLE
        }
        binding.tvGenderMale.setOnClickListener {
            binding.llGender.visibility = View.GONE
            binding.tvGender.text = binding.tvGenderMale.text
        }
        binding.tvGenderFemale.setOnClickListener {
            binding.llGender.visibility = View.GONE
            binding.tvGender.text = binding.tvGenderFemale.text
        }
        binding.tvStatusTitle.setOnClickListener{
            binding.llStatus.visibility = View.VISIBLE
        }
        binding.tvStatusSingle.setOnClickListener {
            binding.llStatus.visibility = View.GONE
            binding.tvStatus.text = binding.tvStatusSingle.text
        }
        binding.tvStatusFamily.setOnClickListener {
            binding.llStatus.visibility = View.GONE
            binding.tvStatus.text = binding.tvStatusFamily.text
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, vmFactory)[FragmentCreditViewModel::class.java]
        viewModel.observe(this, ::onProgress, ::onError)
        viewModel.liveData.observe(this, ::onDataChanged)
        viewModel.partitionLiveData.observe(this, ::partitionSelected)
    }

    private fun partitionSelected(partitions: Partitions) {
        partitions.properties.let {
            binding.layoutPartition.tvAddress.text = it.companyMetaData.address
            binding.layoutPartition.tvHours.text = it.companyMetaData.hourse.text
            binding.layoutPartition.nsvContainer.visibility = View.VISIBLE
        }
    }

    private fun onDataChanged(data: Int) {

    }

    override fun setToolbar() {
        (requireActivity() as MainActivity).initToolbar(R.string.input_application, true)
    }
}