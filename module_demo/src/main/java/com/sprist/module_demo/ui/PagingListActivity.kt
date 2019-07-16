package com.sprist.module_demo.ui

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.sprist.lib_base.activity.BaseRefreshListActivity
import com.sprist.lib_base.adapter.BaseListAdapter
import com.sprist.lib_base.adapter.BasePagingAdapter
import com.sprist.module_demo.R
import com.sprist.module_demo.adapter.CommonListDelegate
import com.sprist.module_demo.adapter.PagingListDelegate
import com.sprist.module_demo.vm.DemoViewModel
import com.sprist.module_demo.vm.DemoViewModelFactory


/**
 *
 * DESC：
 * Created by liyuhang on 2019/7/3
 *
 */
class PagingListActivity : BaseRefreshListActivity() {


    companion object {

        fun postActivity(context: Context) {
            context.startActivity(Intent(context, PagingListActivity::class.java))
        }

    }

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProviders.of(this, DemoViewModelFactory()).get(DemoViewModel::class.java)
    }


    private val adapter by lazy {
        BasePagingAdapter(PagingListDelegate(), R.layout.list_item_single_text)
    }


    override fun getRecyclerViewAdapter(): RecyclerView.Adapter<*>? {
        return adapter
    }

    override fun refresh() {
        viewModel.mCommonListItems.value?.refresh?.invoke()
    }

    override fun getContentView() = R.layout.activity_ui_refresh_list

    override fun initData() {
        ToolBar(this).setTitle("Paging列表")
        viewModel.getPagingItems()
    }

    override fun inject() {
        viewModel.mPagingListItems.observe(this, loadObserver({
            adapter.submitList(it)
        }, isShowLoading = false))
    }

    override fun initListener() {
    }

}