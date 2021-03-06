package com.nhahv.noteremember.ui.loadpicture.album

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nhahv.noteremember.databinding.FragmentAlbumBinding
import com.nhahv.noteremember.ui.ViewModelFactory
import com.nhahv.noteremember.ui.loadpicture.imagescreen.ImageScreenViewModel
import kotlinx.android.synthetic.main.fragment_album.*

/**
 * Notebook Screen.
 */
class AlbumFragment : LifecycleFragment() {

    private lateinit var viewModel: ImageScreenViewModel

    companion object {
        fun newInstance(): AlbumFragment {
            return AlbumFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity, ViewModelFactory.getInstance(activity as AppCompatActivity)).get(ImageScreenViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentAlbumBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        event()
    }

    private fun event() {
        camera.setOnClickListener {
            //                        android:onClick="@{() -> viewModel.onOpenCamera()}"

        }
    }


}
