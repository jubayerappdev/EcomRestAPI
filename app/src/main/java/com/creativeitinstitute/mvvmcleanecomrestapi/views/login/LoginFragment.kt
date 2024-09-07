package com.creativeitinstitute.mvvmcleanecomrestapi.views.login

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.creativeitinstitute.mvvmcleanecomrestapi.R
import com.creativeitinstitute.mvvmcleanecomrestapi.base.BaseFragment
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.RequestLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.databinding.FragmentLoginBinding
import com.creativeitinstitute.mvvmcleanecomrestapi.prefs.PrefsManager
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.DataState
import com.creativeitinstitute.mvvmcleanecomrestapi.views.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class  LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val viewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var prefsManager: PrefsManager


    override fun setListener() {

        binding.loginBtn.setOnClickListener {
            val email = binding.userEmail.text.toString()
            val password = binding.password.text.toString()

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

                    prefsManager.setPrefs(ACCESS_TOKEN, "${response.data?.accessToken}")
                    prefsManager.setPrefs(REFRESH_TOKEN, "${response.data?.refreshToken}")

                    Log.d("TAG", "accessToken: ${prefsManager.getPrefs(ACCESS_TOKEN)} ")
                    Log.d("TAG", "refreshToken: ${prefsManager.getPrefs(REFRESH_TOKEN)} ")
                }
            }


        }
//
    }
    companion object{
        const val ACCESS_TOKEN = "accessToken"
        const val REFRESH_TOKEN = "refreshToken"
    }

}