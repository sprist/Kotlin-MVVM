package com.sprist.module_demo.adapter

import android.view.View
import com.sprist.lib_base.adapter.BaseDelegate
import com.sprist.lib_base.adapter.BaseViewHolder
import com.sprist.module_demo.R


/**
 *
 * DESC：
 * Created by liyuhang on 2019/7/4
 *
 */

class CommonListDelegate: BaseDelegate<String>(){


    override fun convert(viewType: Int, helper: BaseViewHolder, item: String, position: Int) {
        helper.setText(R.id.tv_content,item)
    }

    override fun onItemClickListener(view: View, data: String, position: Int) {
        super.onItemClickListener(view, data, position)
    }

}