package com.creativeitinstitute.mvvmcleanecomrestapi.views.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.RequestLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.login.ResponseLogin
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.product.ResponseProductItem
import com.creativeitinstitute.mvvmcleanecomrestapi.data.repo.PlatziRepository
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: PlatziRepository): ViewModel() {

    private val _productResponse = MutableLiveData<DataState<List<ResponseProductItem>>>()

    val productResponse: LiveData<DataState<List<ResponseProductItem>>>
        get() = _productResponse


    init {
        getAllProduct()
    }

   private fun getAllProduct (){
        _productResponse.postValue(DataState.Loading())

        viewModelScope.launch {

          val response =  repository.getAllProducts().body()



                try {

                    _productResponse.postValue(DataState.Success(response))
                }catch (e: Exception){
                    _productResponse.postValue(DataState.Error("${e.message}"))
                }

        }


    }


}