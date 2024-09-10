package com.creativeitinstitute.mvvmcleanecomrestapi.views.product

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.creativeitinstitute.mvvmcleanecomrestapi.R
import com.creativeitinstitute.mvvmcleanecomrestapi.base.BaseFragment
import com.creativeitinstitute.mvvmcleanecomrestapi.data.model.product.ResponseProductItem
import com.creativeitinstitute.mvvmcleanecomrestapi.databinding.FragmentProductBinding
import com.creativeitinstitute.mvvmcleanecomrestapi.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class ProductFragment : BaseFragment<FragmentProductBinding>(FragmentProductBinding::inflate), ProductAdapter.Listener {

    private val viewmodel by viewModels<ProductViewModel> ()

    private val adapter:ProductAdapter by lazy {
        ProductAdapter(this)
    }

    override fun setListener() {
        binding.productRcv.adapter = adapter
    }

    override fun allObserver() {

        viewmodel.productResponse.observe(viewLifecycleOwner){
            when(it){
                is DataState.Error -> {}
                is DataState.Loading -> {
                    loading.show()
                }
                is DataState.Success -> {
                    loading.dismiss()

                    setUIData(it.data)
                }
            }
        }

    }

    private fun setUIData(data: List<ResponseProductItem>?) {
        data?.let { products->

          adapter.submitList(products)

        }
    }

    override fun productClick(productId: Int) {

    }


}