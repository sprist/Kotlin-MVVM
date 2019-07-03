package com.sprist.lib_base.activity

import androidx.lifecycle.Observer
import com.sprist.lib_base.net.NetState
import com.sprist.lib_base.net.Response


/**
 *
 * DESC：有数据请求的Activity的抽象类
 * Created by liyuhang on 2019/5/6
 *
 */

abstract class BaseLoadingActivity : BaseActivity() {


    open fun <T> loadObserver(
        unit: (data: T?) -> Unit,
        msg: String? = null,
        isShowLoading: Boolean = true
    ): Observer<Response<T>> {
        return Observer {
            when (it.state.status) {
                NetState.Status.LOADING -> if (isShowLoading) showLoading(msg)
                NetState.Status.SUCCESS -> {
                    onSuccess(unit, it.data)
                }
                NetState.Status.FILTER -> onFilter()
                NetState.Status.ERROR -> {
                    onError(it.state.code, it.state.msg)
                }
            }
            onResponseFinish(it)
        }

    }

    open fun <T> loadObserver(unit: (data: T?) -> Unit): Observer<Response<T>> {
        return Observer {
            when (it.state.status) {
                NetState.Status.LOADING -> showLoading()
                NetState.Status.SUCCESS -> {
                    onSuccess(unit, it.data)
                }
                NetState.Status.FILTER -> onFilter()
                NetState.Status.ERROR -> {
                    onError(it.state.code, it.state.msg)
                }
            }
            onResponseFinish(it)
        }

    }

    open fun <T> onResponseFinish(response: Response<T>){

    }



    /**
     * 接口请求成功
     */
    open fun <T> onSuccess(unit: (data: T?) -> Unit, data: T?) {
        dismissLoading()
        unit(data)
    }

    /**
     * 接口请求被拦截
     */
    open fun onFilter() {
        dismissLoading()
    }

    /**
     * 5位code为请求服务异常，6位为交互错误
     */
    open fun onError(code: String?, msg: String?) {
        dismissLoading()
        showToast(msg)
    }

}