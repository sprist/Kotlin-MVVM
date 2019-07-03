package com.sprist.lib_base.net

import java.util.concurrent.Executor
import java.util.concurrent.Executors


/**
 *
 * DESC：线程管理
 * Created by liyuhang on 2019/7/3
 *
 */

object NetContants {
    @Suppress("PrivatePropertyName")
    private val DISK_IO = Executors.newSingleThreadExecutor()

    // thread pool used for network requests
    @Suppress("PrivatePropertyName")
    private val NETWORK_IO = Executors.newFixedThreadPool(5)

    fun getNetworkExecutor(): Executor = NETWORK_IO

    fun getDiskIOExecutor(): Executor = DISK_IO
}