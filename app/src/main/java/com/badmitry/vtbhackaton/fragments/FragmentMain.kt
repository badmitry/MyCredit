package com.badmitry.vtbhackaton.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.badmitry.data.DataSaver
import com.badmitry.domain.entities.AuthData
import com.badmitry.vtbhackaton.MainActivity
import com.badmitry.vtbhackaton.R
import com.badmitry.vtbhackaton.databinding.FragmentMainBinding
import com.badmitry.vtbhackaton.navigation.Screens
import com.badmitry.vtbhackaton.viewmodules.FragmentMainViewModel
import javax.inject.Inject


class FragmentMain : BaseFragment() {

    private val AUTH_DATA = "AuthData"
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: FragmentMainViewModel

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
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("ServiceCast")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
        viewModel.getApplicationId()
    }

    private fun initComponent() {
        arguments?.getSerializable(AUTH_DATA)?.let {
            viewModel.setAuthData(it as AuthData)
        }
        binding.btnSendApplication.setOnClickListener {
            viewModel.navigateFragment(Screens.SELECT_PARTITION)
        }
        binding.cvApplications.setOnClickListener {
            viewModel.getApplicationId()
            if (binding.rvApplications.visibility == View.VISIBLE) {
                binding.rvApplications.visibility = View.GONE
            } else {
                binding.rvApplications.visibility = View.VISIBLE
            }
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, vmFactory)[FragmentMainViewModel::class.java]
        viewModel.observe(this, ::onProgress, ::onError)
        viewModel.liveData.observe(this, ::onSetUser)
    }

    private fun onSetUser(authData: AuthData) {
        binding.user = authData.user
    }

    override fun setToolbar() {
        (requireActivity() as MainActivity).initToolbar(R.string.app_name, false)
    }

    companion object {
        fun createInstance(data: AuthData?): FragmentMain {
            return FragmentMain().apply {
                data?.let {
                    arguments = Bundle().apply {
                        putSerializable(AUTH_DATA, it)
                    }
                }
            }
        }
    }
}