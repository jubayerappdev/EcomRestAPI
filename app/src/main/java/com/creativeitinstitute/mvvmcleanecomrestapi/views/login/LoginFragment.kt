package com.creativeitinstitute.mvvmcleanecomrestapi.views.login

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.mvvmcleanecomrestapi.R
import com.creativeitinstitute.mvvmcleanecomrestapi.base.BaseFragment
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.RequestLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.databinding.FragmentLoginBinding
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.DataState
import com.creativeitinstitute.mvvmcleanecomrestapi.views.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class  LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()


    override fun setListener() {

        binding.loginBtn.setOnClickListener {
            val email = binding.userEmail?.text.toString()
            val password = binding.password?.text.toString()

            handleLogin("john@mail.com", "changeme")
        }
        binding.registerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

    }

    private fun handleLogin(email:String, password:String) {

        val requestLogin = RequestLogin(email = email, password = password)
        viewModel.login(requestLogin)
    }

    override fun allObserver() {

        viewModel.loginResponse.observe(viewLifecycleOwner){ response->

            when(response){
                is DataState.Error -> {
                    loading.dismiss()
                }
                is DataState.Loading -> {
                    loading.show()
                }
                is DataState.Success -> {
                    loading.dismiss()
                    Log.d("TAG", "allObserver: ${response.data} ")
                }
            }


        }

    }


}