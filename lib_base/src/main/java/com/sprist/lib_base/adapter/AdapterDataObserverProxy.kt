package com.sprist.lib_base.adapter

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

class AdapterDataObserverProxy(
        private val adapterDataObserver: RecyclerView.AdapterDataObserver,
        private val headerCount: MutableLiveData<Int>
) : RecyclerView.AdapterDataObserver() {

    override fun onChanged() {
        adapterDataObserver.onChanged()
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        adapterDataObserver.onItemRangeChanged(positionStart + (headerCount.value ?: 0), itemCount)
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
        adapterDataObserver.onItemRangeChanged(positionStart + (headerCount.value
                ?: 0), itemCount, payload)
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        adapterDataObserver.onItemRangeInserted(positionStart + (headerCount.value ?: 0), itemCount)
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        adapterDataObserver.onItemRangeRemoved(positionStart + (headerCount.value ?: 0), itemCount)
    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        super.onItemRangeMoved(fromPosition + (headerCount.value
                ?: 0), toPosition + (headerCount.value ?: 0), itemCount)
    }
}