package com.ca.designsystem.components.singlerowcalendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CalendarPager(
    pagerState: PagerState,
    content: @Composable (Int) -> Unit
) {

    HorizontalPager(
        state = pagerState,
        beyondViewportPageCount = 1
    ) { page ->
        content(page)
    }
}