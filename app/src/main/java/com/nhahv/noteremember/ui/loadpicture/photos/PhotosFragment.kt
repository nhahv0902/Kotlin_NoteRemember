package com.nhahv.noteremember.ui.loadpicture.photos

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhahv.noteremember.databinding.FragmentPhotosBinding
import com.nhahv.noteremember.ui.ViewModelFactory
import com.nhahv.noteremember.ui.loadpicture.imagescreen.ImageScreenViewModel

/**
 * Notebook Screen.
 */
class PhotosFragment : LifecycleFragment() {

    private lateinit var viewModel: ImageScreenViewModel

    companion object {
        fun newInstance(): PhotosFragment {
            return PhotosFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity, ViewModelFactory.getInstance(activity as AppCompatActivity)).get(ImageScreenViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentPhotosBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

}