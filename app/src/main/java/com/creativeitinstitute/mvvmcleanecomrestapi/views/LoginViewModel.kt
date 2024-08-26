package com.creativeitinstitute.mvvmcleanecomrestapi.views

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.RequestLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.repo.PlatziRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: PlatziRepository): ViewModel() {

    fun login (requestLogin: RequestLogin){
        viewModelScope.launch {

          val response =  repository.login(requestLogin)

            if (response.isSuccessful){
                Log.d("TAG", "login:  ${response.body()}")
            }
        }


    }
}