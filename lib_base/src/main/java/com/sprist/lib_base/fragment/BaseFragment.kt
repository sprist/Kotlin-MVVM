package com.sprist.lib_base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.sprist.lib_base.lifecycle.BaseLifecycle
import com.sprist.lib_ui.dialog.LoadingDialog
import com.sprist.lib_ui.utils.DialogHelper

/**
 * DESC：Fragment的抽象类
 * Created by liyuhang on 2019/7/3
 */
abstract class BaseFragment : Fragment() {

    lateinit var mLoadingDialog: LoadingDialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LayoutInflater.from(context).inflate(getContentView(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLoadingDialog = DialogHelper.getLoadingDialog(requireActivity())
        initLifecycle()
        initData()
        inject()
        initListener()
    }

    @LayoutRes
    abstract fun getContentView(): Int

    abstract fun initListener()

    abstract fun initData()


    abstract fun inject()

    private fun initLifecycle() {
        lifecycle.addObserver(BaseLifecycle())
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

    /**
     * Toast提示
     */
    fun showToast(msg: String?) {
        msg?.let {
            DialogHelper.showToast(requireActivity(), it)
        }
    }



}