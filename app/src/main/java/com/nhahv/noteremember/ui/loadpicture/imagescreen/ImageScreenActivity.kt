package com.nhahv.noteremember.ui.loadpicture.imagescreen

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import com.nhahv.noteremember.R
import com.nhahv.noteremember.databinding.ActivityImageScreenBinding
import com.nhahv.noteremember.ui.LifecycleAppcompatActivity
import com.nhahv.noteremember.ui.ViewPagerAdapter
import com.nhahv.noteremember.ui.loadpicture.album.AlbumFragment
import com.nhahv.noteremember.ui.loadpicture.photos.PhotosFragment
import kotlinx.android.synthetic.main.activity_image_screen.*
import android.support.v4.view.ViewPager
import com.nhahv.noteremember.R.id.viewPager



class ImageScreenActivity : LifecycleAppcompatActivity() {

    private lateinit var viewModel: ImageScreenViewModel
    private val fragments: ArrayList<Fragment> = ArrayList()
    private var adapter: ViewPagerAdapter = ViewPagerAdapter(fragments, supportFragmentManager)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders
                .of(this)
                .get(ImageScreenViewModel::class.java)
        val binding: ActivityImageScreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_image_screen)
        binding.viewModel = viewModel
        fragments.add(PhotosFragment.newInstance())
        fragments.add(AlbumFragment.newInstance())
        viewPager.adapter = adapter
        photos.isSelected = true
        event()
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

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }
}
