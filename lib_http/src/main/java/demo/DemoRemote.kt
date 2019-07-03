package demo

import com.sprist.lib_http.HttpClientManager
import com.sprist.lib_http.infe.OnApiResultListener
import com.sprist.lib_http.infe.RemoteHelper
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONObject


/**
 *
 * DESC：远程调用接口示例
 * Created by liyuhang on 2019/7/2
 *
 */
class DemoRemote : RemoteHelper() {


    private val mReto by lazy { HttpClientManager.getRetrofit() }
    private val mServiceApi by lazy { mReto.create(DemoApi::class.java) }


    fun postResult(pageSize: Int, pageNumber: Int, resultCallBack: OnApiResultListener<String>) {
        val json = JSONObject()
        json.put("pageSize", pageSize)
        json.put("pageNumber", pageNumber)
        json.put("materialCode", "WL-10052")
        val requestBody = RequestBody.create(MediaType.parse("application/json"), json.toString())
        observe(mServiceApi.postResult3("", requestBody), resultCallBack)
    }


}