package com.badmitry.vtbhackaton

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.badmitry.domain.entities.EventArgs
import com.badmitry.vtbhackaton.databinding.ActivityMainBinding
import com.badmitry.vtbhackaton.navigation.Screens
import com.badmitry.vtbhackaton.view.OnErrorView
import com.badmitry.vtbhackaton.view.OnProgressView
import com.badmitry.vtbhackaton.viewmodules.MainViewModel
import dagger.android.support.DaggerAppCompatActivity
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), OnErrorView, OnProgressView {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initViewModel()
        setNavigator()
    }

    override fun onResume() {
        super.onResume()
        val uri: Uri? = intent.data
        if (uri != null && uri.toString().startsWith("https://badmitry.com")) {
            // use the parameter your API exposes for the code (mostly it's "code")
            val code: String? = uri.getQueryParameter("code")
            if (code != null) {
                Toast.makeText(this, uri.getQueryParameter("code"), Toast.LENGTH_LONG)
                viewModel.replaceFragment(Screens.SIGNING)
            } else if (uri.getQueryParameter("error") != null) {
                Toast.makeText(this, uri.getQueryParameter("error"), Toast.LENGTH_LONG)
            }
        }
        if (supportFragmentManager.findFragmentById(binding.container.id) == null) {
            viewModel.replaceFragment(Screens.SIGNING)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, vmFactory)[MainViewModel::class.java]
        viewModel.observe(this, ::onProgress, ::onError)
    }

    private fun setNavigator() {
        val navigator = SupportAppNavigator(this, supportFragmentManager, binding.container.id)
        viewModel.setNavigator(navigator)
    }

    fun initToolbar(idTitle: Int, onBackBtnVisible: Boolean) {
        setSupportActionBar(binding.toolbar.materialToolbar)
        binding.toolbar.materialToolbar.title = getString(idTitle)
        supportActionBar?.apply {
            setHomeButtonEnabled(onBackBtnVisible)
            setDisplayHomeAsUpEnabled(onBackBtnVisible)
        }
    }

    override fun onError(arg: EventArgs<Throwable>) {
        Toast.makeText(this, arg.args.message, Toast.LENGTH_LONG).show()
    }

    override fun onProgress(arg: EventArgs<Boolean>) {
        if (arg.args) {
            binding.pbLoadingLayout.visibility = View.VISIBLE
        } else {
            binding.pbLoadingLayout.visibility = View.GONE
        }
    }
}