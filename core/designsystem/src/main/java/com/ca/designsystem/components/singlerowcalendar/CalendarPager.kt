package com.ca.designsystem.components.singlerowcalendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CalendarPager(
    pagerState: PagerState,
    loadedDates: CalendarState,
    loadNextDates: () -> Unit,
    loadPrevDates: () -> Unit,
    content: @Composable (Int) -> Unit
) {
    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage == 2) {
            loadNextDates()
            pagerState.scrollToPage(1)
        }
        if (pagerState.currentPage == 0) {
            loadPrevDates()
            pagerState.scrollToPage(1)
        }
    }

    HorizontalPager(
        pageCount = Int.MAX_VALUE,
        state = pagerState,
        contentPadding = PaddingValues(bottom = 6.dp)
    ) {
        content(it)
    }
}