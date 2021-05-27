package com.annchar.coinranking.ui.cryptolist

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

    override val viewModel: CryptoListViewModel by viewModel()

    override fun layout(): Int = R.layout.fragment_crypto_list

    override fun init() {
        initView()
        initAdapter()
        initObservers()
        bindEvents()
    }

    private fun initView() {
        // initial toolbar
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)

        // initial recyclerView
        binding.rcCryptoList.addItemDecoration(itemDecorator)
        binding.rcCryptoList.adapter = cryptoListAdapter
    }

    private fun initAdapter() {
        cryptoListAdapter.addLoadStateListener { loadState ->
            // show empty list
            val isListEmpty = loadState.refresh is LoadState.NotLoading && cryptoListAdapter.itemCount == 0
            binding.tvNoResults.isVisible = isListEmpty

            // Only show the list if refresh succeeds.
            binding.rcCryptoList.isVisible = loadState.source.refresh is LoadState.NotLoading

            // Show loading spinner during initial load or refresh.
            handleLoading(loadState.source.refresh is LoadState.Loading)

            // Show the retry state if initial load or refresh fails.
            binding.btnRetry.isVisible = loadState.source.refresh is LoadState.Error

            /**
             * loadState.refresh - represents the load state for loading the PagingData for the first time.
             * loadState.prepend - represents the load state for loading data at the start of the list.
             * loadState.append - represents the load state for loading data at the end of the list.
             * */
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

    private fun initObservers() {
        viewModel.cryptoList.observe(viewLifecycleOwner, {
            cryptoListAdapter.submitData(this.lifecycle, it)
        })
    }

    private fun bindEvents() {
        with(binding) {
            rcCryptoList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    fab.isVisible = dy < START_POSITION

                    val scrollPosition =
                        (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    refreshLayout.isEnabled = scrollPosition == START_POSITION
                }
            })

            fab.setOnClickListener {
                rcCryptoList.smoothScrollToPosition(START_POSITION)
            }

            refreshLayout.setOnRefreshListener {
                cryptoListAdapter.refresh()
            }

            btnRetry.setOnClickListener {
                cryptoListAdapter.retry()
            }
        }
    }

    private fun handleLoading(loading: Boolean) {
        with(binding) {
            refreshLayout.isRefreshing = loading == true
        }
    }
}