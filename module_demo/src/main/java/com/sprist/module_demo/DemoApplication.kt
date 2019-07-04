package com.sprist.module_demo

import com.sprist.lib_base.application.BaseApplication
import com.sprist.lib_http.HttpClientManager


/**
 *
 * DESC：
 * Created by liyuhang on 2019/7/3
 *
 */

class DemoApplication : BaseApplication() {


    override fun onCreate() {
        super.onCreate()
        HttpClientManager.build(this)

    }

}