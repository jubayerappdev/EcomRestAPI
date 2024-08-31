package com.creativeitinstitute.mvvmcleanecomrestapi.prefs

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PrefsManager @Inject constructor(@ApplicationContext context: Context){

    private val prefs = context.getSharedPreferences("ecom_prefs", Context.MODE_PRIVATE)



    fun setPrefs(key : String, value : String){
        val editor : SharedPreferences.Editor = prefs.edit()

        editor.putString(key, value)

        editor.apply()
    }

    fun getPrefs(key : String): String? {

        val prefsValue = prefs.getString(key,null)

        return prefsValue

    }

}