package com.sprist.lib_base.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import org.jetbrains.annotations.NotNull


/**
 *
 * DESC：Lifecycle的基类
 * Created by liyuhang on 2019/7/3
 *
 */

open class BaseLifecycle : ILifecycle {

    override fun onResume(owner: LifecycleOwner) {
    }

    override fun onPause(owner: LifecycleOwner) {
    }

    override fun onStop(owner: LifecycleOwner) {
    }

    override fun onLifecycleChanged(@NotNull owner: LifecycleOwner, @NotNull event: Lifecycle.Event) {
    }

    override fun onCreate(@NotNull owner: LifecycleOwner) {
    }

    override fun onDestroy(@NotNull owner: LifecycleOwner) {
    }


}