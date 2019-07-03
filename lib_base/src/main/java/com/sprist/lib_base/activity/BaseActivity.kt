package com.sprist.lib_base.activity

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.gyf.barlibrary.ImmersionBar
import com.sprist.lib_base.lifecycle.ActivityLifecycle
import com.sprist.lib_ui.dialog.LoadingDialog
import com.sprist.lib_ui.utils.DialogHelper


/**
 *
 * DESC：activity的基础抽象类
 * Created by liyuhang on 2019/7/3
 *
 */
abstract class BaseActivity : AppCompatActivity() {


    lateinit var mLoadingDialog: LoadingDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        initActivity()
        initView()
        initData()
        inject()
        initListener()
    }

    private fun initActivity() {
        mLoadingDialog = DialogHelper.getLoadingDialog(this)
        lifecycle.addObserver(ActivityLifecycle)
        setStatusBar()
    }


    open fun setStatusBar() {
        //状态栏暗色
        ImmersionBar.with(this).statusBarDarkFont(false).init()

    }

    open fun initContentView() {
        getContentView()?.let { setContentView(it) }
    }

    /**
     * 初始化view
     */
    open fun initView() {

    }

    @LayoutRes
    abstract fun getContentView(): Int?

    abstract fun initData()

    abstract fun inject()

    abstract fun initListener()

    /**
     * 打开软键盘
     */
    fun openKeyBord(mEditText: View, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN)
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
        mEditText.requestFocus()
    }

    /**
     * 关闭软键盘
     */
    fun closeKeyBord(mEditText: View, mContext: Context) {
        val imm = mContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(mEditText.windowToken, 0)
    }

    /**
     * Toast提示
     */
    fun showToast(msg: String?) {
        msg?.let {
            DialogHelper.showToast(this, it)
        }
    }

    /**
     * 交互框显示
     */
    fun showLoading(msg: String? = null) {
        msg?.let {
            mLoadingDialog.tvProgressText.text = it
        }
        mLoadingDialog.show()
    }

    /**
     * 交互框隐藏
     */
    fun dismissLoading() {
        mLoadingDialog.dismiss()
    }


    override fun onDestroy() {
        super.onDestroy()
        if (mLoadingDialog.isShowing) mLoadingDialog.dismiss()
        ImmersionBar.with(this).destroy()

    }
}