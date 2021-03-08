package com.kpc.gallery.ui

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.RecyclerView
import com.kpc.gallery.R
import com.kpc.gallery.base.DataBindingActivity
import com.kpc.gallery.databinding.ActivityMainBinding
import com.kpc.gallery.dialog.MessageDialog
import com.kpc.gallery.mappers.toPresentation
import com.kpc.gallery.viewmodel.MainViewModel

internal class MainActivity : DataBindingActivity<ActivityMainBinding, MainViewModel>(MainViewModel::class) {

    override fun getLayoutResId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val window: Window = this.window
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this,
            R.color.colorPrimaryDark
        )
    }

    override fun initialView() {
        binding.viewModel = viewModel
        viewModel.getPhoto()

        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.rvList.layoutManager = gridLayoutManager
        binding.rvList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) {
                    val visibleItemCount = gridLayoutManager.childCount
                    val totalItemCount = gridLayoutManager.itemCount
                    val pastVisiblesItems = gridLayoutManager.findFirstVisibleItemPosition()
                    if (viewModel.loadMore.value == true) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            viewModel.loadMorePhoto()
                        }
                    }
                }
            }
        })

        gridLayoutManager.spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (viewModel.adapter.isPositionFooter(position)) gridLayoutManager.spanCount else 1
            }
        }

        viewModel.selectedPhoto.observe(this, Observer { photo ->
            startActivity(PhotoActivity.newIntent(this, photo.toPresentation()))
        })

        viewModel.error.observe(this, Observer {
            if (it.isNotEmpty()) {
                val dialog = MessageDialog.newDialog(getString(R.string.error_title), it)
                dialog.isCancelable = false
                dialog.show(supportFragmentManager, this::javaClass.toString())
            }
        })
    }
}
