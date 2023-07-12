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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.MainTopBar
import com.ca.designsystem.theme.Theme
import com.ca.records.presentation.pages.GlucoseRecordsPage
import com.ca.records.presentation.pages.InsulinRecordsPage
import com.ca.records.presentation.pages.Page
import com.ca.records.presentation.pages.pages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordsScreen(
    viewModel: RecordsViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val pagerState = rememberPagerState()
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { MainTopBar(title = "Records") }
    ) { paddingValues ->
        RecordsPager(
            modifier = Modifier
                .padding(paddingValues),
            pagerState = pagerState,
            viewState = viewState,
            scope = scope
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordsPager(
    modifier: Modifier,
    pagerState: PagerState,
    viewState: RecordsViewState,
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
                    InsulinRecordsPage(records = viewState.insulinRecords)
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