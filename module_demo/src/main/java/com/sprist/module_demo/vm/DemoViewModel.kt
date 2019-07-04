package com.sprist.module_demo.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sprist.lib_base.net.Response
import com.sprist.module_demo.repository.DemoRepositoryImpl


/**
 *
 * DESC：
 * Created by liyuhang on 2019/7/4
 *
 */

class DemoViewModel:ViewModel(){


    private val mDemoRepositoryImpl by lazy { DemoRepositoryImpl() }


    var mCommonListItems = MutableLiveData<Response<ArrayList<String>>>()



    fun getCommonItems(){
        mDemoRepositoryImpl.getCommonList1(mCommonListItems)
    }







}