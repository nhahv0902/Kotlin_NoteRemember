package com.nhahv.noteremember.ui.loadpicture.preview

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.nhahv.noteremember.R
import com.nhahv.noteremember.databinding.ActivityPreviewPictureBinding
import com.nhahv.noteremember.ui.loadpicture.model.PicturePicker

class PreviewPictureActivity : AppCompatActivity() {

    private lateinit var viewModel: PreviewPictureViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val position = intent.extras.getInt("position")
        val items: ArrayList<String> = intent.extras.getStringArrayList("items")
        viewModel = ViewModelProviders
                .of(this)
                .get(PreviewPictureViewModel::class.java)
        val binding: ActivityPreviewPictureBinding = DataBindingUtil.setContentView(this, R.layout.activity_preview_picture)
        binding.viewModel = viewModel
        viewModel.update(items, position)
    }
}
