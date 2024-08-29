package com.creativeitinstitute.mvvmcleanecomrestapi.views

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.RequestLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.ResponseLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.repo.PlatziRepository
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.DataState
import com.google.gson.Gson
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

          val response =  repository.login(requestLogin)

            if (response.isSuccessful){
                Log.d("TAG", "login:  ${response.body()}")
                try {
                    val data =  Gson().fromJson(response.body()?.string().toString(), ResponseLogin::class.java)
                    _loginResponse.postValue(DataState.Success(data))
                }catch (e: Exception){
                    _loginResponse.postValue(DataState.Error("${e.message}"))
                }


            }else{
                _loginResponse.postValue(DataState.Error(response.message()))
            }
        }


    }
}