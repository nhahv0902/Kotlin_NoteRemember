package com.nhahv.noteremember.ui.notebook

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhahv.noteremember.databinding.FragmentNotebookBinding

/**
 * Notebook Screen.
 */
class NotebookFragment : LifecycleFragment() {

    private lateinit var viewModel: NotebookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(NotebookViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentNotebookBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    companion object {
        fun newInstance(): NotebookFragment {
            return NotebookFragment()
        }
    }
}
