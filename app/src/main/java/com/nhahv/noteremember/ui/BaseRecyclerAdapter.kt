package com.nhahv.noteremember.ui

import android.arch.lifecycle.ViewModel
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhahv.noteremember.BR
import com.nhahv.noteremember.databinding.ItemAlbumBinding

/**
 * Created by nhahv on 9/30/17.
 */
class BaseRecyclerAdapter<B>(
        private val items: ArrayList<B>,
        private val viewModel: ViewModel,
        private val layout: Int) : RecyclerView.Adapter<BaseRecyclerAdapter.BaseViewHolder<ViewDataBinding, B>>() {

    private var inflater: LayoutInflater? = null

    override fun onCreateViewHolder(parent: ViewGroup?,
                                    viewType: Int): BaseViewHolder<ViewDataBinding, B> {
        if (inflater == null) {
            inflater = LayoutInflater.from(parent?.context)
        }
        val binding: ViewDataBinding = DataBindingUtil.inflate(inflater, layout, parent, false)
        return BaseViewHolder(binding, viewModel)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ViewDataBinding, B>?, position: Int) {
        holder?.bind(items[position], position)
    }

    override fun getItemCount(): Int = items.size


    class BaseViewHolder<out T : ViewDataBinding, in B>(val binding: T,
                                                        val viewModel: ViewModel) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: B, position: Int) {
            binding.setVariable(BR.item, item)
            binding.setVariable(BR.viewModel, viewModel)
            binding.setVariable(BR.position, position)
            binding.executePendingBindings()
        }
    }

}