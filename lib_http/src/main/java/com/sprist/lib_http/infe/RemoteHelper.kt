package com.sprist.lib_http.infe


import com.sprist.lib_http.HttpContants
import com.sprist.lib_http.bean.BaseResponse
import com.sprist.lib_http.exception.HttpClientException
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 *
 * DESC：远程调用方法类
 * Created by liyuhang on 2019/7/2
 *
 */
open class RemoteHelper {



    fun <T> observe(observable: Observable<BaseResponse<T>>, mResponseListener: OnApiResultListener<T>) {
        observable
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<BaseResponse<T>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(result: BaseResponse<T>) {
                    if (result.isSuccess()) {
                        mResponseListener.onSuccess(result)
                    } else if (HttpContants.mCustomResponseFilter?.onResponseFilter(
                            result.mCode,
                            result.mMessage
                        ) != true
                    ) {
                        mResponseListener.onError(result)
                    } else {
                        mResponseListener.onFilter(result)
                    }
                }

                override fun onError(e: Throwable) {
                    val responseError = HttpClientException.getError(e)
                    val response = BaseResponse<T>()
                    response.mCode= responseError.errorCode
                    response.mMessage = responseError.errorMessage
                    if (HttpContants.mCustomResponseFilter?.onResponseFilter(
                            responseError.errorCode,
                            responseError.errorMessage
                        ) != true
                    ) {
                        mResponseListener.onError(response)
                    } else {
                        mResponseListener.onFilter(response)
                    }
                }
            })
    }

}