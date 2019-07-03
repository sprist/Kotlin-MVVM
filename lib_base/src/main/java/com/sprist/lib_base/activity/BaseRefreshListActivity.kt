package com.sprist.lib_base.activity

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.sprist.lib_base.R
import com.sprist.lib_base.net.NetState
import com.sprist.lib_base.net.Response


/**
 *
 * DESC：activity的基础抽象类
 * Created by liyuhang on 2019/5/6
 *
 */
abstract class BaseRefreshListActivity : BaseToolBarActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var refreshLayout: SwipeRefreshLayout
    lateinit var layoutEmpty: LinearLayout
    lateinit var ivEmpty: ImageView
    lateinit var tvEmpty: TextView

    var isSwipeRefresh = false//区分下拉刷新，还是网络再次请求刷新


    override fun initView() {
        recyclerView = findViewById(R.id.recycler_view)
        refreshLayout = findViewById(R.id.refresh_layout)
        layoutEmpty = findViewById(R.id.layout_empty)
        ivEmpty = findViewById(R.id.iv_empty)
        tvEmpty = findViewById(R.id.tv_empty)
        recyclerView.layoutManager = getLayoutManager()
        recyclerView.adapter = getRecyclerViewAdapter()
        refreshLayout.setOnRefreshListener {
            isSwipeRefresh = true
            refresh()
        }
    }


    /**
     * 设置layoutmanager
     */
    open fun getLayoutManager(): RecyclerView.LayoutManager {
        return LinearLayoutManager(this)
    }


    /**
     * 设置adapter
     */
    abstract fun getRecyclerViewAdapter(): RecyclerView.Adapter<*>?

    /**
     * 刷新方法，具体需要重写
     */
    abstract fun refresh()


    /**
     * 重置下拉刷新状态
     */
    override fun <T> onResponseFinish(response: Response<T>) {
        refreshLayout.isRefreshing =
            (isSwipeRefresh && response.state == NetState.LOADING)
    }

    /**
     * 接口请求成功
     */
    override fun <T> onSuccess(unit: (data: T?) -> Unit, data: T?) {
        dismissLoading()
        unit(data)
        isSwipeRefresh = false
        checkData()
    }


    /**
     * 接口请求被拦截
     */
    override fun onFilter() {
        dismissLoading()
        isSwipeRefresh = false
        checkData()
    }

    /**
     * 错误返回
     */
    override fun onError(code: String?, msg: String?) {
        dismissLoading()
        showToast(msg)
        isSwipeRefresh = false
        checkData(code)
    }

    /**
     * 数据校验
     */
    private fun checkData(code: String? = null) {
        if (recyclerView.adapter?.itemCount == 0) {
            if (code != null && code.length == 5) {//code 5位为网络异常，展示重新加载页面
                initDataNetError()
            } else {
                initDataEmpty()
            }
            showEmpty()
        } else {
            showContent()
        }


    }


    /**
     * 展示缺省UI
     */
    fun showEmpty() {
        if (layoutEmpty.visibility != View.VISIBLE) {
            layoutEmpty.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    /**
     * 展示缺省列表数据
     */
    fun showContent() {
        if (recyclerView.visibility != View.VISIBLE) {
            recyclerView.visibility = View.VISIBLE
            layoutEmpty.visibility = View.GONE
        }
    }


    private fun initDataEmpty() {
        ivEmpty.setImageResource(R.drawable.icon_data_empty)
        tvEmpty.text = "暂无数据"
        tvEmpty.setOnClickListener(null)
    }


    private fun initDataNetError() {
        ivEmpty.setImageResource(R.drawable.icon_data_net_error)
        tvEmpty.text = "刷新重试"
        tvEmpty.setOnClickListener {
            refresh()
        }
    }


}