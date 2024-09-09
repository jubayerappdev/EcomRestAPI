package com.creativeitinstitute.mvvmcleanecomrestapi.views.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.RequestLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.ResponseLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.user.ResponseUser
import com.creativeitinstitute.mvvmcleanecomrestapi.data.repo.PlatziRepository
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.DataState
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val repository: PlatziRepository): ViewModel() {

    private val _profileResponse = MutableStateFlow<DataState<ResponseUser>>(DataState.Loading())

    val profileResponse : StateFlow<DataState<ResponseUser>>
        get() = _profileResponse

    fun getProfile(){
        viewModelScope.launch {
            val response = repository.getProfile()

            if (response.isSuccessful){
                _profileResponse.emit(DataState.Success(response.body()))
            }else{
                _profileResponse.emit(DataState.Error(response.message()))
            }
        }
    }





}