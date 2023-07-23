package com.ca.records.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.Tabs
import com.ca.records.glucose.presentation.GlucoseRecordsPage
import com.ca.records.insulin.presentation.InsulinRecordsPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordsScreen() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    RecordsPager(
        modifier = Modifier,
        pagerState = pagerState,
        scope = scope
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RecordsPager(
    modifier: Modifier,
    pagerState: PagerState,
    scope: CoroutineScope
) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Tabs(
            pages = pages,
            modifier = Modifier
                .padding(vertical = 12.dp),
            onTabClick = { scope.launch { pagerState.animateScrollToPage(it) } },
            selectedTabIndex = pagerState.currentPage
        )

        HorizontalPager(
            state = pagerState,
            pageCount = pages.size,
            beyondBoundsPageCount = pages.size
        ) {
            when (pages[it]) {
                RecordsPage.InsulinRecords -> {
                    InsulinRecordsPage()
                }
                RecordsPage.GlucoseRecords -> {
                    GlucoseRecordsPage()
                }
            }
        }
    }
}
