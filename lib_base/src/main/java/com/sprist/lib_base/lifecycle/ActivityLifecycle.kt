package com.sprist.lib_base.lifecycle

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import java.util.*

/**
 * DESC：任务栈管理
 * Created by liyuhang on 2019/7/3
 */
object ActivityLifecycle : BaseLifecycle() {

    val store by lazy {
        Stack<Activity>()
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        store.remove(owner as Activity)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        store.add(owner as Activity)
    }
}