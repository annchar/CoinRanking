package com.annchar.coinranking.ui.cryptolist

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.annchar.coinranking.R
import com.annchar.coinranking.databinding.FragmentCryptoListBinding
import com.annchar.coinranking.ui.base.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel


class CryptoListFragment : BaseFragment<FragmentCryptoListBinding, CryptoListViewModel>() {

    companion object {
        private const val START_POSITION = 0
    }

    override val viewModel: CryptoListViewModel by viewModel()

    override fun layout(): Int = R.layout.fragment_crypto_list

    override fun init() {
        initializeView()
        bindEvents()
        initObservers()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.top_app_bar, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                // TODO: Add search feature
                showToastMessage(getString(R.string.warning_available_soon))
                return true
            }
        }
        return false
    }

    private val cryptoListAdapter by lazy {
        CryptoListAdapter {
            showToastMessage(getString(R.string.item_clicked, it.symbol))
        }
    }

    private val itemDecorator by lazy {
        CryptoListMarginDecoration(
            requireContext(),
            R.dimen.spacing_small
        )
    }

    private fun initializeView() {
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)

        binding.rcCryptoList.addItemDecoration(itemDecorator)
        binding.rcCryptoList.adapter = cryptoListAdapter
    }

    private fun bindEvents() {
        cryptoListAdapter.addLoadStateListener { loadState ->
            /*
            * loadState.refresh - represents the load state for loading the PagingData for the first time.
              loadState.prepend - represents the load state for loading data at the start of the list.
              loadState.append - represents the load state for loading data at the end of the list.
            * */

            if (loadState.refresh is LoadState.Loading || loadState.append is LoadState.Loading)
                viewModel.setLoading(true)
            else {
                viewModel.setLoading(false)

                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    showToastMessage(it.error.message.toString())
                }
            }
        }

        binding.rcCryptoList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                binding.fab.isVisible = dy < START_POSITION

                val scrollPosition = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                binding.refreshLayout.isEnabled = scrollPosition == START_POSITION
            }
        })

        binding.fab.setOnClickListener {
            binding.rcCryptoList.smoothScrollToPosition(START_POSITION)
        }

        binding.refreshLayout.setOnRefreshListener {
            cryptoListAdapter.refresh()
        }
    }

    private fun initObservers() {
        viewModel.cryptoList.observe(viewLifecycleOwner, {
            cryptoListAdapter.submitData(this.lifecycle, it)
        })

        viewModel.loading.observe(viewLifecycleOwner, { isLoading ->
            handleLoading(isLoading)
        })
    }

    private fun handleLoading(loading: Boolean?) {
        binding.refreshLayout.isRefreshing = loading == true
    }
}