package com.annchar.coinranking.ui.cryptolist

import com.annchar.coinranking.R
import com.annchar.coinranking.base.BaseFragment
import com.annchar.coinranking.databinding.CryptoListFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CryptoListFragment : BaseFragment<CryptoListFragmentBinding, CryptoListViewModel>() {

    override val viewModel: CryptoListViewModel by viewModel()

    override fun layout(): Int = R.layout.crypto_list_fragment

    override fun init() {

    }
}