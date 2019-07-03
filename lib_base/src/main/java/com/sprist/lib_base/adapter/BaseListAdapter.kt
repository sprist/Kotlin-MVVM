package com.sprist.lib_base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.annotations.NotNull


/**
 *
 * DESC：Padding adapter的调用类
 * Created by liyuhang on 2019/7/3
 *
 */

class BaseListAdapter<T>(val datas: ArrayList<T>,
    private val viewHolderDelegate: BaseDelegate<T>, @NotNull vararg resLayouts: Int
) :
    RecyclerView.Adapter<BaseViewHolder>() {
    private val mLyouts = resLayouts
    private val ITEM_TYPE_HEADER = 0x00000111
    private val ITEM_TYPE_FOOTER = 0x00000222
    //header footer
    private var mHeaderLayout: LinearLayout? = null
    private var mFooterLayout: LinearLayout? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        viewHolderDelegate.context = parent.context
        var viewHolder: BaseViewHolder
        when (viewType) {
            ITEM_TYPE_HEADER -> {
                viewHolder = BaseViewHolder(mHeaderLayout!!, this)
            }
            ITEM_TYPE_FOOTER -> {
                viewHolder = BaseViewHolder(mFooterLayout!!, this)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(getLayoutId(viewType), parent, false)
                viewHolder = BaseViewHolder(view, this)
            }
        }
        return viewHolder

    }

    override fun getItemCount(): Int {
        return datas.size + getHeaderLayoutCount() + getFooterLayoutCount()
    }

    fun getDataCount(): Int {
        return datas.size
    }


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val viewType = holder.itemViewType
        when (viewType) {
            ITEM_TYPE_HEADER -> {
            }
            ITEM_TYPE_FOOTER -> {
            }
            else -> {
                viewHolderDelegate.convert(
                    getItemViewType(position), holder,
                    datas[position - getHeaderLayoutCount()], position - getHeaderLayoutCount()
                )
                bindViewClickListener(holder, position - getHeaderLayoutCount())

            }
        }
    }


    fun setData(items: ArrayList<T>) {
        datas.clear()
        datas.addAll(items)
        notifyDataSetChanged()
    }


    private fun bindViewClickListener(holder: BaseViewHolder, position: Int) {
        val view = holder.itemView
        view.setOnClickListener { viewHolderDelegate.onItemClickListener(view, datas[position], position) }
        view.setOnLongClickListener {
            viewHolderDelegate.onItemLongClickListener(
                view,
                datas[position],
                position
            )
        }
    }


    override fun getItemViewType(position: Int): Int {
        val numHeaders = getHeaderLayoutCount()
        return if (position < numHeaders) {
            ITEM_TYPE_HEADER
        } else {
            var adjPosition = position - numHeaders
            val adapterCount = datas.size
            if (adjPosition < adapterCount) {
                viewHolderDelegate.getItemViewType(datas!![adjPosition], adjPosition)
            } else {
                ITEM_TYPE_FOOTER
            }
        }
    }

    private fun getLayoutId(viewType: Int): Int {
        return mLyouts[viewType]
    }


    /**
     * 添加头部局
     */
    fun addHeaderView(header: View) {
        if (mHeaderLayout == null) {
            mHeaderLayout = LinearLayout(header.context)
            mHeaderLayout?.orientation = LinearLayout.VERTICAL
            mHeaderLayout?.layoutParams = RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        }
        val childCount = mHeaderLayout?.childCount ?: 0
        mHeaderLayout?.addView(header, childCount)
        if (mHeaderLayout?.childCount == 1) {

            notifyItemInserted(0)
        }
    }


    /**
     * 添加脚布局
     */
    fun addFooterView(footer: View) {
        if (mFooterLayout == null) {
            mFooterLayout = LinearLayout(footer.context)
            mFooterLayout?.orientation = LinearLayout.VERTICAL
            mFooterLayout?.layoutParams = RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT)

        }
        val childCount = mFooterLayout?.childCount ?: 0
        mFooterLayout?.addView(footer, childCount)
        if (mFooterLayout?.childCount == 1) {
            val position = getHeaderLayoutCount() + (datas?.size ?: 0)
            if (position != -1) {
                notifyItemInserted(position)
            }
        }
    }

    fun getHeaderLayoutCount(): Int {
        return if (mHeaderLayout == null || mHeaderLayout?.childCount == 0) {
            0
        } else 1
    }

    private fun getFooterLayoutCount(): Int {
        return if (mFooterLayout == null || mFooterLayout?.childCount == 0) {
            0
        } else 1
    }

}