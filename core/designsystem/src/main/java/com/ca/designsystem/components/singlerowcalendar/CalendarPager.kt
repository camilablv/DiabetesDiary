package com.ca.designsystem.components.singlerowcalendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun CalendarPager(
    pagerState: PagerState,
    content: @Composable (Int) -> Unit
) {

    HorizontalPager(
        pageCount = Int.MAX_VALUE,
        state = pagerState,
        contentPadding = PaddingValues(bottom = 6.dp),
        beyondBoundsPageCount = 1
    ) {
        val page = it.minus(Int.MAX_VALUE).plus(1).absoluteValue
        content(page)
    }
}