package com.annchar.coinranking.ui.cryptolist

import com.annchar.coinranking.R
import com.annchar.coinranking.base.BaseFragment
import com.annchar.coinranking.databinding.FragmentCryptoListBinding
import org.koin.android.viewmodel.ext.android.viewModel

class CryptoListFragment : BaseFragment<FragmentCryptoListBinding, CryptoListViewModel>() {

    override val viewModel: CryptoListViewModel by viewModel()

    override fun layout(): Int = R.layout.fragment_crypto_list

    override fun init() {

    }
}