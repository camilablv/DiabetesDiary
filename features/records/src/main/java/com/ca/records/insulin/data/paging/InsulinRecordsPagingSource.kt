package com.ca.records.insulin.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ca.domain.model.InsulinRecord
import com.ca.network.api.NetworkClient
import com.ca.network.utils.records

class InsulinRecordsPagingSource(
    private val networkClient: NetworkClient
) : PagingSource<String, InsulinRecord>() {

    override fun getRefreshKey(state: PagingState<String, InsulinRecord>): String? {
        return state.lastItemOrNull()?.cursor
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, InsulinRecord> {

        return networkClient.insulinRecords(params.key, params.loadSize).fold(
            onSuccess = {
                val result = it.records()
                LoadResult.Page(
                    data = result,
                    prevKey = params.key,
                    nextKey = result.lastOrNull()?.cursor
                )
            },
            onFailure = { exception ->
                LoadResult.Error(exception)
            }
        )
    }
}