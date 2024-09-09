package com.creativeitinstitute.mvvmcleanecomrestapi.views.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.creativeitinstitute.mvvmcleanecomrestapi.R
import com.creativeitinstitute.mvvmcleanecomrestapi.base.BaseFragment
import com.creativeitinstitute.mvvmcleanecomrestapi.databinding.FragmentProfileBinding
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding> (FragmentProfileBinding::inflate){

    val viewModel by viewModels <ProfileViewModel>()

    override fun setListener() {

        viewModel.getProfile()

    }

    override fun allObserver() {


        lifecycleScope.launch {

            viewModel.profileResponse.collect{ state->
                when(state){
                    is DataState.Error -> {}
                    is DataState.Loading -> {}
                    is DataState.Success -> {
                        Log.d("TAG", "allObserver: ${state.data}")
                    }
                }

            }
        }





    }


}