package com.sprist.lib_base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.annotations.NotNull


/**
 *
 * DESC：Padding adapter的调用类
 * Created by liyuhang on 2019/7/3
 *
 */

class BasePagingAdapter<T>(
    private val delegate: BasePagingDelegate<T>, @NotNull vararg resLayouts: Int
) :
    PagedListAdapter<T, BaseViewHolder>(delegate.getDiffUtil()) {
    private val mLyouts = resLayouts
    private val ITEM_TYPE_HEADER = 0x00000111
    private val ITEM_TYPE_FOOTER = 0x00000222
    //header footer
    private var mHeaderLayout: LinearLayout? = null
    private var mFooterLayout: LinearLayout? = null
    private val headerCountLiveData = MutableLiveData<Int>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        delegate.context = parent.context
        var viewHolder: BaseViewHolder
        when (viewType) {
            ITEM_TYPE_HEADER -> {
                viewHolder = BaseViewHolder(mHeaderLayout!!,this)
            }
            ITEM_TYPE_FOOTER -> {
                viewHolder = BaseViewHolder(mFooterLayout!!,this)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(getLayoutId(viewType), parent, false)
                viewHolder = BaseViewHolder(view,this)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val viewType = holder.itemViewType
        when (viewType) {
            ITEM_TYPE_HEADER -> {
            }
            ITEM_TYPE_FOOTER -> {
            }
            else -> {
                delegate.convert(
                    getItemViewType(position), holder,
                    getItem(position - getHeaderLayoutCount())!!, position - getHeaderLayoutCount()
                )
                bindViewClickListener(holder, position - getHeaderLayoutCount())
            }
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + getHeaderLayoutCount() + getFooterLayoutCount()
    }

    fun getDataCount(): Int {
        return super.getItemCount()
    }


    private fun getLayoutId(viewType: Int): Int {
        return mLyouts[viewType]
    }


    override fun getItemViewType(position: Int): Int {
        val numHeaders = getHeaderLayoutCount()
        return if (position < numHeaders) {
            ITEM_TYPE_HEADER
        } else {
            var adjPosition = position - numHeaders
            val adapterCount = currentList?.size ?: 0
            if (adjPosition < adapterCount) {
                delegate.getItemViewType(currentList!![adjPosition]!!, adjPosition)
            } else {
                ITEM_TYPE_FOOTER
            }
        }
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
            headerCountLiveData.postValue(getHeaderLayoutCount())
            notifyItemInserted(0)
        }
    }

    private fun bindViewClickListener(holder: BaseViewHolder, position: Int) {
        val view = holder.itemView
        view.setOnClickListener { delegate.onItemClickListener(view, currentList!![position]!!, position) }
        view.setOnLongClickListener {
            delegate.onItemLongClickListener(
                view,
                currentList!![position]!!,
                position
            )
        }
    }


    override fun registerAdapterDataObserver(observer: RecyclerView.AdapterDataObserver) {
        super.registerAdapterDataObserver(
            AdapterDataObserverProxy(
                observer,
                headerCountLiveData
            )
        )
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
            val position = getHeaderLayoutCount() + (currentList?.size ?: 0)
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