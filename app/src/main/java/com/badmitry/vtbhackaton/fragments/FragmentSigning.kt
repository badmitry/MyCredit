package com.badmitry.vtbhackaton.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.badmitry.vtbhackaton.MainActivity
import com.badmitry.vtbhackaton.R
import com.badmitry.vtbhackaton.databinding.FragmentSigningBinding
import com.badmitry.vtbhackaton.viewmodules.FragmentSigningViewModel
import javax.inject.Inject


class FragmentSigning : BaseFragment() {
    private lateinit var binding: FragmentSigningBinding
    private lateinit var viewModel: FragmentSigningViewModel

    private val appClientId = "920cb60cce58e560c4463b0c2e2a9970"
    private val appClientSecret = "157baf586bde3eb6795dd15727107986"
    private val redirectUri = "https://badmitry.com"

    private val testLogin = "team21@app.hackaton.bankingapi.ru"
    private val testPassword = "jGW2R1Gi"
    private val CODE_PERMISSION_LOCATION = 1100

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (requireActivity().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED &&
                requireActivity().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED
            ) {
                requireActivity().requestPermissions(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    CODE_PERMISSION_LOCATION
                )
            }
        }
        binding.btnSigning.setOnClickListener {
            viewModel.requestAuth(
                binding.etLogin.text.toString(),
                binding.etPassword.text.toString()
            )
        }

        binding.btnTestAccount.setOnClickListener {
            binding.etLogin.setText(testLogin)
            binding.etPassword.setText(testPassword)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, vmFactory)[FragmentSigningViewModel::class.java]
        viewModel.observe(this, ::onProgress, ::onError)
        viewModel.liveData.observe(this, ::onDataChanged)
    }

    override fun setToolbar() {
        (requireActivity() as MainActivity).initToolbar(R.string.signing, false)
    }

    private fun onDataChanged(int: Int) {

    }
}