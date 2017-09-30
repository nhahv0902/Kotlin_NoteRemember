package com.nhahv.noteremember.ui.notebook

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.nhahv.noteremember.R
import com.nhahv.noteremember.data.Notebook
import com.nhahv.noteremember.ui.BaseRecyclerAdapter


/**
 * Exposes the data to be used in the Notebook screen.
 */

class NotebookViewModel : ViewModel(){


    val isRefresh: ObservableBoolean = ObservableBoolean()
    val notebooks: ArrayList<Notebook> = ArrayList()
    val adapter : ObservableField<BaseRecyclerAdapter<Notebook>> = ObservableField(BaseRecyclerAdapter(notebooks, this, R.layout.item_notebook))

    fun onLoadNotebookData() {
        isRefresh.set(false)
    }
}
