package demo

import android.app.Application
import com.sprist.lib_http.HttpClientManager
import com.sprist.lib_http.filter.CustomResponseFilter


/**
 *
 * DESC：在Application中初始化Http
 * Created by liyuhang on 2019/7/2
 *
 */

class DemoApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        initHttpClient()

    }

    private fun initHttpClient() {
        HttpClientManager.build(this, "http://192.168.0.1/api/")
            .setCustomErrorFilter(object : CustomResponseFilter {
                override fun onResponseFilter(code: String, msg: String): Boolean {
                    when (code) {
                        "99999" -> {
                            /**
                             * 拦截错误code，比如用户需要重新登录,返回true表示要进程特殊优先处理
                             */
                            return true
                        }
                        else -> return false
                    }

                }
            })
    }


}