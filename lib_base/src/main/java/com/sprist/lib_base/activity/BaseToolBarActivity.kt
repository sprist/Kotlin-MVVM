package com.sprist.lib_base.activity

import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import com.google.android.material.appbar.AppBarLayout
import com.gyf.barlibrary.ImmersionBar
import com.sprist.lib_base.R

/**
 *
 * DESC：带ToolBar的Activity的抽象类
 * Created by liyuhang on 2019/7/3
 *
 */

abstract class BaseToolBarActivity : BaseLoadingActivity() {


    override fun initContentView() {
        setContentView(R.layout.activity_ui_toolbar)
        getContentView()?.let {
            findViewById<FrameLayout>(R.id.layout_content).addView(
                layoutInflater.inflate(
                    it, findViewById<FrameLayout>(R.id.layout_content), false
                )
            )
        }

    }

    override fun setStatusBar() {
        super.setStatusBar()
        //状态栏高度
        findViewById<AppBarLayout>(R.id.app_bar).setPadding(0, ImmersionBar.getStatusBarHeight(this), 0, 0)
    }


    class ToolBar(private val activity: BaseToolBarActivity) {
        private var headTitle: TextView = activity.findViewById(R.id.tv_head_title)
        private var headBack: ImageView = activity.findViewById(R.id.iv_back)
        private var headRightFlat: TextView = activity.findViewById(R.id.tv_right_flat)


        fun setTitle(title: String): ToolBar {
            headTitle.text = title
            headBack.setOnClickListener { activity.finish() }
            return this
        }

        fun setTitle(@StringRes title: Int): ToolBar {
            headTitle.setText(title)
            headBack.setOnClickListener { activity.finish() }
            return this
        }

        fun goneBack(isGone: Boolean): ToolBar {
            headBack.visibility = if (isGone) View.GONE else View.VISIBLE
            return this
        }


        fun setRightFlat(text: String, listen: View.OnClickListener): ToolBar {
            headRightFlat.visibility = View.VISIBLE
            headRightFlat.text = text
            headRightFlat.setOnClickListener(listen)
            return this
        }


    }


}