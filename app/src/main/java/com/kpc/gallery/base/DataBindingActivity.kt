package com.kpc.gallery.base

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.kpc.gallery.viewmodel.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

internal abstract class DataBindingActivity<Binding : ViewDataBinding, out VM : BaseViewModel>(classViewModel: KClass<VM>) :
    BaseActivity() {

    protected lateinit var binding: Binding

    protected val viewModel: VM by viewModel(classViewModel)

    protected abstract fun getLayoutResId(): Int

    protected abstract fun initialView()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        binding.lifecycleOwner = this
        initialView()
    }

    override fun onClickBack(view: View) {}

}
