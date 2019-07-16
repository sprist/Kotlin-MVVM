package com.sprist.module_demo.ui

import com.sprist.lib_base.activity.BaseToolBarActivity
import com.sprist.module_demo.R
import kotlinx.android.synthetic.main.activity_main.*


/**
 *
 * DESC：
 * Created by liyuhang on 2019/7/3
 *
 */

class MainActivity:BaseToolBarActivity(){

    override fun getContentView()= R.layout.activity_main

    override fun initData() {
        ToolBar(this).setTitle("首页").goneBack(true)

    }

    override fun inject() {

    }

    override fun initListener() {
        btn_list.setOnClickListener {
            CommonListActivity.postActivity(this)
        }
        btn_paging.setOnClickListener {
            PagingListActivity.postActivity(this)
        }
    }
}