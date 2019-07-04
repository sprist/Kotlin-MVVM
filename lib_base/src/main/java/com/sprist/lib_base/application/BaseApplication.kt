package com.sprist.lib_base.application

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.sprist.lib_base.utils.ActivityUtils
import kotlin.properties.Delegates


/**
 *
 * DESC：
 * Created by liyuhang on 2019/7/3
 *
 */

abstract class BaseApplication : Application() {


    companion object {
        var mApplication: Application by Delegates.notNull()
    }


    override fun onCreate() {
        super.onCreate()
        mApplication = this
        initLogger()
        initLeakCanary()
    }

    private fun initLeakCanary() {
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return
//        }
//        LeakCanary.install(this)
    }

    private fun initLogger() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
            .methodCount(1)         // (Optional) How many method line to show. Default 2
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))
    }

    /**
     * 程序终止的时候执行
     */
    override fun onTerminate() {
        super.onTerminate()
        ActivityUtils.exitApp()
    }


}