package com.dezc.coffeesaleapp.ui.utils.commons

import android.os.AsyncTask
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

class DataAsyncUpdater<T, V : ViewDataBinding>(private val mDataBoundListAdapter:
                                               DataBoundListAdapter<T, V>)
    : AsyncTask<T, Unit, DiffUtil.DiffResult>() {

    private var mStartVersion: Int = 0

    private lateinit var mOldItems: List<T>

    private lateinit var mUpdate: List<T>

    override fun doInBackground(vararg update: T)
            : DiffUtil.DiffResult {
        mStartVersion = mDataBoundListAdapter.dataVersion
        mOldItems = mDataBoundListAdapter.items
        mUpdate = update.toList()
        return DiffUtil.calculateDiff(object : DiffUtil.Callback() {

            override fun getOldListSize(): Int = mDataBoundListAdapter.items.size

            override fun getNewListSize(): Int = mUpdate.size

            override fun areContentsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
            ): Boolean = mDataBoundListAdapter
                    .areContentsTheSame(mOldItems[oldItemPosition], mUpdate[newItemPosition])

            override fun areItemsTheSame(
                    oldItemPosition: Int,
                    newItemPosition: Int
            ): Boolean = mDataBoundListAdapter
                    .areItemsTheSame(mOldItems[oldItemPosition], mUpdate[newItemPosition])
        })
    }

    override fun onPostExecute(result: DiffUtil.DiffResult?) {
        if (mStartVersion != mDataBoundListAdapter.dataVersion) return
        mDataBoundListAdapter.items = mUpdate
        result?.dispatchUpdatesTo(mDataBoundListAdapter)
    }
}
