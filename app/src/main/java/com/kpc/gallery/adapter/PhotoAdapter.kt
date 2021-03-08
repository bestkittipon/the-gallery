package com.kpc.gallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions
import com.kpc.domain.model.PhotoItemDomain
import com.kpc.gallery.BR
import com.kpc.gallery.R
import com.kpc.gallery.base.BaseAdapter
import com.kpc.gallery.base.BaseViewHolder
import com.kpc.gallery.databinding.ItemContentBinding
import com.kpc.gallery.databinding.ItemLoadmoreBinding
import com.kpc.gallery.viewmodel.MainViewModel

class CurrencyAdapter(layoutId: Int, viewModel: MainViewModel): BaseAdapter<MainViewModel, ViewDataBinding, PhotoItemDomain, PhotoViewHolder>(layoutId, viewModel) {

    private val TYPE_ITEM = 0
    private val TYPE_FOOTER = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return if (viewType == TYPE_FOOTER) {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_loadmore, parent, false)
            PhotoViewHolder(binding as ItemLoadmoreBinding, true)
        } else {
            binding = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false)
            PhotoViewHolder(binding as ItemContentBinding, false)
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        if (!isPositionFooter(position)) {
            getData().getOrNull(position)?.let {
                holder.bindView(it, viewModel, position)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (withFooter() && isPositionFooter(position)) {
            TYPE_FOOTER
        } else {
            TYPE_ITEM
        }
    }

    fun isPositionFooter(position: Int): Boolean {
        return position == itemCount - 1 && withFooter()
    }

    override fun withFooter() = true
}

class PhotoViewHolder(private val viewBinding: ViewDataBinding, private val loading: Boolean): BaseViewHolder<ViewDataBinding, PhotoItemDomain>(viewBinding) {
    override fun bindView(data: PhotoItemDomain, viewModel: ViewModel, index: Int) {
        if (!loading && viewBinding is ItemContentBinding) {
            viewBinding.setVariable(BR.obj, data)
            viewBinding.setVariable(BR.viewModel, viewModel)
            viewBinding.executePendingBindings()

            val requestOptions = RequestOptions().apply {
                this.placeholder(R.drawable.info_icon)
                this.error(R.drawable.info_icon)
            }

            val glideUrl = GlideUrl(
                data.thumbnailUrl, LazyHeaders.Builder()
                    .addHeader(
                        "User-Agent",
                        "Android"
                    )
                    .build()
            )

            Glide.with(viewBinding.root)
                .setDefaultRequestOptions(requestOptions)
                .load(glideUrl)
                .into(viewBinding.imgLogo)
        }
    }
}