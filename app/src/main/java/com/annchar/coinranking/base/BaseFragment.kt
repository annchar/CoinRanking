package com.annchar.coinranking.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.annchar.coinranking.BR
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel

abstract class BaseFragment<Binding : ViewDataBinding, ViewModel : BaseViewModel> : Fragment() {
    protected abstract val viewModel: ViewModel
    protected lateinit var binding: Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout(), container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.setVariable(
            BR.viewModel,
            viewModel
        )
        return binding.root
    }

    /**
     * Get layout resource id which inflate in onCreateView.
     */
    @LayoutRes
    abstract fun layout(): Int

    abstract fun init()

    override fun onStart() {
        super.onStart()
        init()
        // TODO: hideSoftKeyboard()
    }
}