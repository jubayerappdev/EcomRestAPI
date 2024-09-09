package com.creativeitinstitute.mvvmcleanecomrestapi.views.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.RequestLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.ResponseLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.repo.PlatziRepository
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository: PlatziRepository): ViewModel() {

    private val _loginResponse = MutableLiveData<DataState<ResponseLogin>>()

    val loginResponse: LiveData<DataState<ResponseLogin>>
        get() = _loginResponse



    fun login (requestLogin: RequestLogin){
        _loginResponse.postValue(DataState.Loading())

        viewModelScope.launch {

          val response =  repository.login(requestLogin).body()


                Log.d("TAG", "login:  ${response?.accessToken}")
                try {

                    _loginResponse.postValue(DataState.Success(response))
                }catch (e: Exception){
                    _loginResponse.postValue(DataState.Error("${e.message}"))
                }

        }


    }


}