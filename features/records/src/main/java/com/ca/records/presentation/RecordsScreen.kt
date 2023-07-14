package com.ca.records.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.ca.designsystem.theme.Theme
import com.ca.records.glucose.presentation.GlucoseRecordsPage
import com.ca.records.insulin.presentation.InsulinRecordsPage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordsScreen() {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val scaffoldState = rememberScaffoldState()

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
            .fillMaxSize()
    ) {
        Tabs(
            pagerState = pagerState,
            onTabClick = { scope.launch { pagerState.animateScrollToPage(it) } }
        )

        HorizontalPager(
            state = pagerState,
            pageCount = pages.size
        ) {
            when (pages[it]) {
                Page.InsulinRecords -> {
                    InsulinRecordsPage()
                }
                Page.GlucoseRecords -> {
                    GlucoseRecordsPage()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(
    pagerState: PagerState,
    onTabClick: (Int) -> Unit
) {
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier = Modifier,
        backgroundColor = Theme.colors.background
    ) {
        pages.forEachIndexed { index, item ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = { onTabClick(index) },
                text = {
                    Text(
                        text = item.text,
                        style = Theme.typography.bodyLarge
                    )
                }
            )
        }
    }
}