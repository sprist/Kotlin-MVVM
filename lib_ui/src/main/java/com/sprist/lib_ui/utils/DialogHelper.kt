package com.sprist.lib_ui.utils

import android.content.Context
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.sprist.lib_ui.R
import com.sprist.lib_ui.dialog.LoadingDialog


/**
 *
 * DESC：显示交互弹框
 * Created by liyuhang on 2019/7/3
 *
 */


object DialogHelper {

    fun getLoadingDialog(context: Context, message: String="加载中..."): LoadingDialog {
        val loadingDialog = LoadingDialog(context)
        loadingDialog.tvProgressText.text = message
        return loadingDialog
    }



    fun showToast(context: Context, message: String) {
        val textView = TextView(context.applicationContext).apply {
            text = message
            setBackgroundResource(R.drawable.bg_dialog_loading)
            setTextColor(ContextCompat.getColor(context, R.color.colorWhite))
            setPadding(
                DensityUtils.dp2px(context, 32f), DensityUtils.dp2px(context, 14f),
                DensityUtils.dp2px(context, 32f), DensityUtils.dp2px(context, 14f)
            )
        }
        val mToast = Toast(context.applicationContext)
        mToast.view = textView
        mToast.setGravity(Gravity.TOP, 0, 350)
        mToast.duration = Toast.LENGTH_SHORT
        mToast.show()
    }

}