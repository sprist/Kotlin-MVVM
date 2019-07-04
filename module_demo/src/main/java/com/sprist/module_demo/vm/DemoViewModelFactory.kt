package com.sprist.module_demo.vm

import android.app.Activity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 *
 * DESC：
 * Created by liyuhang on 2019/4/3
 *
 */
class DemoViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DemoViewModel::class.java)) {
            return DemoViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}