package com.example.mangaread.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VM: BaseViewModel,VB: ViewBinding> : AppCompatActivity() {

    protected  var _binding: VB? = null
    protected val binding get() = _binding!!
    protected abstract val viewModel: VM

    protected abstract fun inflateViewBinding(inflater: LayoutInflater): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = inflateViewBinding(layoutInflater)
        setContentView(binding.root)

        initView()
        initViewModel()
        initListener()

    }

    open fun initView() {}
    open fun initListener() {}
    open fun initViewModel() {}

}
