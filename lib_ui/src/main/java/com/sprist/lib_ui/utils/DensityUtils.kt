package com.sprist.lib_ui.utils

import android.content.Context
import android.graphics.Point
import android.util.TypedValue

/**
 * DESC：density工具类
 * Created by liyuhang on 2019/7/3
 */
object DensityUtils {


    /**
     * dp转px
     */

    fun dp2px(context: Context, dpVal: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpVal, context.resources.displayMetrics
        ).toInt()
    }


    /**
     * 获取屏幕宽度
     */

    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    /**
     * 获取屏幕宽度
     */

    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }


    /**
     * sp转px
     */

    fun sp2px(context: Context, spVal: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spVal, context.resources.displayMetrics
        )
    }


    /**
     * px转dp
     */

    fun px2dp(context: Context, pxVal: Float): Float {
        val scale = context.resources.displayMetrics.density
        return pxVal / scale
    }


    /**
     * px转sp
     */

    fun px2sp(context: Context, pxVal: Float): Float {
        return pxVal / context.resources.displayMetrics.scaledDensity
    }

    /**
     * 获取dimens定义的大小
     *
     * @param dimensionId
     * @return
     */
    fun getPixelById(context: Context, dimensionId: Int): Int {
        return context.resources.getDimensionPixelSize(dimensionId)
    }


    fun getScreenMetrics(context: Context): Point {
        val dm = context.resources.displayMetrics
        val w_screen = dm.widthPixels
        val h_screen = dm.heightPixels
        return Point(w_screen, h_screen)

    }

    fun getScreenRate(context: Context): Float {
        val P = getScreenMetrics(context)
        val H = P.y.toFloat()
        val W = P.x.toFloat()
        return H / W
    }
}
