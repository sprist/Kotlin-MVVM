package com.sprist.lib_base.utils

import android.app.Activity
import com.sprist.lib_base.lifecycle.ActivityLifecycle

/**
 * DESC： ActivityUtils管理类
 *  Created by liyuhang on 2019/7/3
 */
object ActivityUtils {

    /**
     * 退出应用
     */
    fun exitApp() {
        for (activity in ActivityLifecycle.store) {
            activity.finish()
        }
        android.os.Process.killProcess(android.os.Process.myPid())
        System.exit(0)
    }


    /**
     * 获取当前的Activity
     *
     * @return
     */
    fun getCurActivity(): Activity {
        return ActivityLifecycle.store.lastElement()
    }


}
