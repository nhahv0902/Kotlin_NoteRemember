package com.nhahv.noteremember.ui.create.photoview

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import com.nhahv.noteremember.R
import com.nhahv.noteremember.databinding.ActivityPhotoViewerBinding
import com.nhahv.noteremember.ui.ViewModelFactory
import java.util.*

class PhotoViewerActivity : AppCompatActivity() {

    private lateinit var viewModel: PhotoViewerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders
                .of(this, ViewModelFactory.getInstance(this))
                .get(PhotoViewerViewModel::class.java)
        val binding: ActivityPhotoViewerBinding = DataBindingUtil.setContentView(this, R.layout.activity_photo_viewer)
        binding.viewModel = viewModel

        val items: ArrayList<String> = intent.extras.getStringArrayList("items")
        viewModel.updatePictures(items)
    }

    override fun onBackPressed() {
        if (viewModel.pictureSize != viewModel.pictures.size) {
            AlertDialog.Builder(this)
                    .setMessage(R.string.msg_delete_image)
                    .setPositiveButton(R.string.action_agree) { dialog, _ ->
                        viewModel.pictures.let {
                            val intent = Intent()
                            val bundle = Bundle()
                            bundle.putStringArrayList("items", viewModel.pictures)
                            intent.putExtras(bundle)
                            setResult(RESULT_OK, intent)
                            dialog.dismiss()
                            finish()
                        }
                    }
                    .setNegativeButton(R.string.action_disagree) { dialog, _ ->
                        dialog.dismiss()
                        finish()
                    }
                    .show()
        } else {
            super.onBackPressed()
        }
    }
}
