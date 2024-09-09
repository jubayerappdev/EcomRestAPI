package com.creativeitinstitute.mvvmcleanecomrestapi.views

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.creativeitinstitute.mvvmcleanecomrestapi.databinding.ActivityMainBinding
import com.creativeitinstitute.mvvmcleanecomrestapi.views.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        binding.loginBtn.setOnClickListener {
//
//            val requestLogin = RequestLogin(
//                email = "john@mail.com",
//                password = "changeme")
//
//
//
//            viewModel.login(requestLogin)
//        }

    }
}