package com.sprist.lib_base.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.DiffUtil


/**
 *
 * DESC：
 * Created by liyuhang on 2019/7/3
 *
 */

abstract class BasePagingDelegate<T> {
    lateinit var context: Context

    /**
     * Paging专用，数据去重
     *
     * @return
     */
    abstract fun getDiffUtil(): DiffUtil.ItemCallback<T>


    /**
     * 为ViewHolder绑定数据item
     *
     * @param viewType
     * @param helper
     * @param item
     * @param position
     * @return
     */
    abstract fun convert(viewType: Int, helper: BaseViewHolder, item:T, position: Int)

    /**
     * 根据页面以及数据内容返回Item的布局index,默认返回第一个布局
     *
     * @param data
     * @param position
     * @return
     */
    open fun getItemViewType(data: T, position: Int): Int {
        return 0
    }


    /**
     * 绑定单击事件
     *
     * @param view
     * @param data
     * @param position
     */
    open fun onItemClickListener(view: View, data: T, position: Int) {

    }

    /**
     * 绑定长按事件
     *
     * @param view
     * @param data
     * @param position
     */
    open fun onItemLongClickListener(view: View, data: T, position: Int): Boolean {
        return false
    }
}