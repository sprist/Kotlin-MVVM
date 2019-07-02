package com.sprist.lib_http.utils

import android.content.Context
import com.sprist.lib_http.HttpContants

/**
 *
 * DESC：数据缓存工具类
 * Created by liyuhang on 2019/7/2
 *
 */

object DataStoreUtils {

    private const val KEY_HTTP_SHARED_PREFERENCE = "http_info"
    private const val KEY_USER_TOKEN = "http_user_token"


    fun getUserToken(): String? {
        HttpContants.mApplication?.let {
            return getString(it, KEY_USER_TOKEN)
        }
        return null
    }

    fun saveUserToken(context: Context, token: String?) {
        saveString(context, KEY_USER_TOKEN, token)
    }


    private fun saveString(context: Context, key: String, value: String?) {
        context.getSharedPreferences(KEY_HTTP_SHARED_PREFERENCE, Context.MODE_PRIVATE).edit().putString(key, value)
            .apply()
    }

    private fun getString(context: Context, key: String): String? {
        return context.getSharedPreferences(KEY_HTTP_SHARED_PREFERENCE, Context.MODE_PRIVATE).getString(key, null)
    }


}