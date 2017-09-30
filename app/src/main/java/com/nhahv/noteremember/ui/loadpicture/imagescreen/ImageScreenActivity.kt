package com.nhahv.noteremember.ui.loadpicture.imagescreen

import android.Manifest
import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import com.nhahv.noteremember.R
import com.nhahv.noteremember.databinding.ActivityImageScreenBinding
import com.nhahv.noteremember.ui.LifecycleAppcompatActivity
import com.nhahv.noteremember.ui.ViewModelFactory
import com.nhahv.noteremember.ui.ViewPagerAdapter
import com.nhahv.noteremember.ui.loadpicture.album.AlbumFragment
import com.nhahv.noteremember.ui.loadpicture.photos.PhotosFragment
import com.nhahv.noteremember.util.mHashPermission
import com.nhahv.noteremember.util.readStoragePermission
import kotlinx.android.synthetic.main.activity_image_screen.*
import org.jetbrains.anko.toast


class ImageScreenActivity : LifecycleAppcompatActivity() {

    private lateinit var viewModel: ImageScreenViewModel
    private val fragments: ArrayList<Fragment> = ArrayList()
    private var adapter: ViewPagerAdapter = ViewPagerAdapter(fragments, supportFragmentManager)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders
                .of(this, ViewModelFactory.getInstance(this))
                .get(ImageScreenViewModel::class.java)
        val binding: ActivityImageScreenBinding = DataBindingUtil.setContentView(this,
                R.layout.activity_image_screen)
        binding.viewModel = viewModel
        fragments.add(PhotosFragment.newInstance())
        fragments.add(AlbumFragment.newInstance())
        viewPager.adapter = adapter
        photos.isSelected = true
        event()

        if (readStoragePermission(applicationContext,
                R.string.msg_permission_request_read_storage_external, this)) {
            viewModel.onLoadImageFromSDCard()
        }
    }

    private fun event() {
        with(photos) {
            setOnClickListener {
                isSelected = true
                viewPager.currentItem = 0
                albums.isSelected = false
            }
        }
        with(albums) {
            setOnClickListener {
                isSelected = true
                viewPager.currentItem = 1
                photos.isSelected = false
            }
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        photos.isSelected = true
                        albums.isSelected = false
                    }
                    1 -> {
                        photos.isSelected = false
                        albums.isSelected = true
                    }
                }

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        cancelPicked.setOnClickListener {
            viewModel.clickCancel()

        }
        donePicked.setOnClickListener {
            val intent = Intent()
            val bundle = Bundle()
            bundle.putStringArrayList("items", viewModel.picturePicked)
            intent.putExtras(bundle)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            mHashPermission[Manifest.permission.READ_EXTERNAL_STORAGE] -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    viewModel.onLoadImageFromSDCard()
                } else {
                    this.toast(R.string.msg_denied_read_storage_external)
                }
                return
            }
//            mHashPermission[Manifest.permission.CAMERA], mHashPermission[Manifest.permission.WRITE_EXTERNAL_STORAGE] -> {
//                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    viewModel.openCamera()
//                } else {
//                    toast(applicationContext, R.string.msg_denied_camera)
//                }
//                return
//            }
        }
    }
}
