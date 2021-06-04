package com.annchar.coinranking.data.pagingsources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.annchar.coinranking.data.api.APIService
import com.annchar.coinranking.data.models.mapper.CryptoListMapper
import com.annchar.coinranking.ui.models.CryptoItemResponse

const val NETWORK_PAGE_SIZE = 500
private const val INITIAL_LOAD_SIZE = 1

class CryptoListPagingSource(
    private val service: APIService,
    private val mapper: CryptoListMapper
) : PagingSource<Int, CryptoItemResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CryptoItemResponse> {
        // Start refresh at position 1 if undefined.
        val position = params.key ?: INITIAL_LOAD_SIZE
        val offset = if (params.key != null) ((position - 1) * NETWORK_PAGE_SIZE) + 1 else INITIAL_LOAD_SIZE
        return try {
            val jsonResponse = service.getCryptoList(start = offset, limit = params.loadSize).data
            val response = mapper.toCryptoListResponse(jsonResponse)
            val nextKey = if (response.isEmpty()) {
                null
            } else {
                // initial load size = 3 * NETWORK_PAGE_SIZE
                // ensure we're not requesting duplicating items, at the 2nd request
                position + (params.loadSize / NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                // assume that if a full page is not loaded, that means the end of the data
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CryptoItemResponse>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return null
    }
}