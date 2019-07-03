package com.sprist.lib_ui.dialog

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.sprist.lib_ui.R

/**
 * DESC：加载弹框
 * Created by liyuhang on 2019/7/3
 */
class LoadingDialog(context: Context) : Dialog(context, R.style.DialogTheme) {
    var tvProgressText: TextView
    var ivLoading: ImageView

    init {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.dialog_ui_loading, null)
        tvProgressText = view.findViewById(R.id.tv_progress_text)
        ivLoading = view!!.findViewById(R.id.iv_loading)
        val params =
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        setContentView(view, params)
        setCancelable(false)
    }


    override fun show() {
        Glide.with(context)
            .load(R.drawable.anim_loading)
            .into(ivLoading)
        super.show()
    }


    override fun dismiss() {
        super.dismiss()
        ivLoading.setImageDrawable(null)

    }

}