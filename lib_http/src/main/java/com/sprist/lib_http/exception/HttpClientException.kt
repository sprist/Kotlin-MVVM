package com.sprist.lib_http.exception

import com.google.gson.JsonSyntaxException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException

/**
 *
 * DESC： 网络请求响应错误
 * Created by liyuhang on 2019/7/2
 *
 */

internal enum class HttpClientException   constructor(
    val errorCode: String,
    var errorMessage: String,
    val errorSupply: String
) {
    UNKNOWN_ERROR("10000", "未知异常", "网络访问异常"),
    SERVER_ERROR("10001", "服务端返回错误代码", "网络访问异常"),
    CONNECTION_TIMEOUT("10002", "网络连接超时", "网络访问异常"),
    INTERNET_DISCONNECT("10003", "网络无法连接", "网络访问异常"),
    JSON_PARSE_ERROR("10004", "数据解析失败", "网络访问异常"),
    SSL_HANDSHAKE_ERROR("10005", "SSL握手失败", "网络访问异常"),
    SSL_HANDSHAKE_ACCESS_ERROR("10006", "SSL握手失败", "网络访问异常"),
    HTTP_ERROR("10007", "网络访问异常", "网络访问异常");

    companion object {
        private const val CODE_UNAUTHORIZED = 401
        private const val CODE_FORBIDDEN = 403
        private const val CODE_NOT_FOUND = 404
        private const val CODE_REQUEST_TIMEOUT = 408
        private const val CODE_INTERNAL_SERVER_ERROR = 500
        private const val CODE_BAD_GATEWAY = 502
        private const val CODE_SERVICE_UNAVAILABLE = 503
        private const val CODE_GATEWAY_TIMEOUT = 504

        fun getError(t: Throwable?): HttpClientException {
            return when (t) {
                is JsonSyntaxException -> JSON_PARSE_ERROR
                is SocketTimeoutException -> CONNECTION_TIMEOUT
                is SSLHandshakeException -> getSSLErrorType(t)
                is ConnectException -> INTERNET_DISCONNECT
                is UnknownHostException -> INTERNET_DISCONNECT
                is HttpException -> getHttpErrorType(t)
                //无法识别的异常
                else -> UNKNOWN_ERROR
            }
        }

        private fun getSSLErrorType(t: Throwable?): HttpClientException {
            if (t == null || t.message.isNullOrEmpty()) {
                return SSL_HANDSHAKE_ACCESS_ERROR
            }
            return if (t.message!!.contains("validate certificate")) {
                SSL_HANDSHAKE_ERROR
            } else SSL_HANDSHAKE_ACCESS_ERROR
        }

        private fun getHttpErrorType(t: HttpException): HttpClientException {
            HTTP_ERROR.errorMessage = when (t.code()) {
                CODE_UNAUTHORIZED -> "当前请求需要用户验证"
                CODE_FORBIDDEN -> "服务器已经理解请求，但是拒绝执行它"
                CODE_NOT_FOUND -> "服务器异常，请稍后再试"
                CODE_REQUEST_TIMEOUT -> "请求超时"
                CODE_GATEWAY_TIMEOUT ->
                    "作为网关或者代理工作的服务器尝试执行请求时，未能及时从上游服务器（URI标识出的服务器，例如HTTP、FTP、LDAP）或者辅助服务器（例如DNS）收到响应"
                CODE_INTERNAL_SERVER_ERROR -> "服务器遇到了一个未曾预料的状况，导致了它无法完成对请求的处理"
                CODE_BAD_GATEWAY -> "作为网关或者代理工作的服务器尝试执行请求时，从上游服务器接收到无效的响应"
                CODE_SERVICE_UNAVAILABLE -> "由于临时的服务器维护或者过载，服务器当前无法处理请求"
                else -> "网络错误"  //其它均视为网络错误
            }
            return HTTP_ERROR
        }
    }
}

