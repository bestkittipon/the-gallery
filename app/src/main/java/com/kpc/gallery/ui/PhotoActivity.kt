package com.kpc.gallery.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.kpc.gallery.R
import com.kpc.gallery.base.DataBindingActivity
import com.kpc.gallery.databinding.ActivityPhotoBinding
import com.kpc.gallery.model.PhotoItemPresentation
import com.kpc.gallery.viewmodel.MainViewModel

internal class PhotoActivity: DataBindingActivity<ActivityPhotoBinding, MainViewModel>(MainViewModel::class) {

    private val photoItem by lazy { intent.extras?.getParcelable<PhotoItemPresentation>(KEY_PHOTO) }

    companion object {
        private const val KEY_PHOTO = "keyPhoto"

        fun newIntent(context: Context,photo: PhotoItemPresentation): Intent {
            return Intent(context, PhotoActivity::class.java).also {
                it.putExtra(KEY_PHOTO, photo)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,
            R.color.colorPrimaryDark
        )
    }

    override fun getLayoutResId() = R.layout.activity_photo

    override fun initialView() {
        binding.obj = photoItem
        binding.view = this

        val requestOptions = RequestOptions().apply {
            this.placeholder(R.drawable.curves)
            this.error(R.drawable.curves)
        }

        val glideUrl = GlideUrl(
            photoItem?.url, LazyHeaders.Builder()
                .addHeader(
                    "User-Agent",
                    "Android"
                )
                .build()
        )

        Glide.with(binding.root)
            .setDefaultRequestOptions(requestOptions)
            .load(glideUrl)
            .into(binding.ivPhoto)
    }

    override fun onClickBack(view: View) {
        this.finish()
    }

}