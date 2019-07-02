package demo


import com.sprist.lib_http.bean.BaseResponse
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.*


/**
 *
 * DESC：http接口调用方法
 * Created by liyuhang on 2019/7/2
 *
 */

interface DemoApi {


    /**
     * Path是网址中的参数,例如:trades/{userId}
     *  Query是问号后面的参数,例如:trades/{userId}?token={token}
     *  QueryMap 相当于多个@Query
     *  Field用于Post请求,提交单个数据,然后要加@FormUrlEncoded
     *  Body相当于多个@Field,以对象的方式提交
     *  @Streaming:用于下载大文件
     *  @Header,@Headers、加请求头
     */

    /**
     * http://192.168.0.1/api/trades
     * 简单的get请求(没有参数)
     */
    @GET("trades")
    fun getItem(): Observable<BaseResponse<String>>

    /**
     * http://192.168.0.1/api/trades/{userId}
     * 简单的get请求(URL中带有参数)
     */
    @GET("trades/{userId}")
    fun getItem1(@Path("userId") userId: String): Observable<BaseResponse<String>>


    /**
     * http://192.168.0.1/api/trades?userId={用户id}}
     * 简单的get请求(//参数在url问号之后)
     */
    @GET("trades")
    fun getItem2(@Query("userId") userId: String): Observable<BaseResponse<String>>


    /**
     * http://192.168.0.1/api/trades?userId={用户id}&type={类型}}
     * 简单的get请求(//参数在url问号之后)
     */
    @GET("trades")
    fun getItem3(@QueryMap map: Map<String, String>): Observable<BaseResponse<String>>

    /**
     * http://192.168.0.1/api/trades?userId={用户id}&type={类型}}
     * 简单的get请求(//参数在url问号之后)
     */
    @GET("trades")
    fun getItem4(@Query("userId") userId: String,@QueryMap map: Map<String, String>): Observable<BaseResponse<String>>



    /**
     * http://192.168.0.1/api/trades/{userId}
     * 需要补全URL,post的数据只有一条reason
     */
    @FormUrlEncoded
    @POST("trades/{userId}")
    fun postResult1(@Path("userId") userId:String,@Field("reason") reason:String) :Observable<BaseResponse<String>>

    /**
     * http://192.168.0.1/api/trades/{userId}?token={token}
     * 需要补全URL,post的数据只有一条reason
     */
    @FormUrlEncoded
    @POST("trades/{userId}")
    fun postResult2(@Path("userId") userId:String,@Query("token")  token:String,@Field("reason") reason:String) :Observable<BaseResponse<String>>

    /**
     * http://192.168.0.1/api/trades/{userId}
     * post一个对象
     */
    @POST("trades/{userId}")
    fun postResult3(@Path("userId") userId:String,@Body  reason: RequestBody) :Observable<BaseResponse<String>>

    /**
     * http://192.168.0.1/api/trades/{userId}
     * 用不同注解post一个实体
     */
    @POST("trades/{userId}")
    fun postResult4(@Path("userId") userId:String,@Part("entity")  reason:RequestBody) :Observable<BaseResponse<String>>



    /**
     * http://192.168.0.1/api/trades/{userId}
     * 用不同注解post一个实体
     */
    @PUT("trades/{userId}")
    fun putInfo(@Path("userId") userId:String,@Body   reason:RequestBody) :Observable<BaseResponse<String>>



}