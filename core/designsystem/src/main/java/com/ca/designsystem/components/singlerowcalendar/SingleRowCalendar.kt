package com.ca.designsystem.components.singlerowcalendar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.designsystem.components.singlerowcalendar.*
import com.ca.designsystem.theme.Theme
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SingleRowCalendar(
    selectedDay: String = "",
    onSelectedDayChange: (LocalDate) -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage = Int.MAX_VALUE)
    val scope = rememberCoroutineScope()

    val dateCursor by remember {
        mutableStateOf(LocalDate.now().weekStartDate())
    }

    var visibleDates by remember {
        mutableStateOf(dateCursor.startDates())
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = Theme.shapes.large,
        elevation = Theme.elevations.default
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            visibleDates = dateCursor.prevWeek(visibleDates[0])
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        },
                    painter = painterResource(id = com.ca.designsystem.R.drawable.round_arrow_back),
                    contentDescription = null
                )
                Text(text = LocalDate.now().toString())

                if (pagerState.canScrollForward) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                                }
                            },
                        painter = painterResource(id = com.ca.designsystem.R.drawable.round_arrow_forward),
                        contentDescription = null
                    )
                }
            }
            LaunchedEffect(pagerState.currentPage) {
                if (pagerState.targetPage < pagerState.currentPage) {
                    visibleDates = dateCursor.prevWeek(visibleDates[0])
                }
            }

            CalendarPager(
                pagerState = pagerState
            ) {
//                LaunchedEffect(pagerState.currentPage) {
//                    if (pagerState.currentPage == 2) {
//                        visibleDates = dateCursor.prevWeek(visibleDates[0])
//                    }
//                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    visibleDates.forEach {
                        Day(
                            date = it,
                            onClick = { date ->
                                onSelectedDayChange(date)
                            },
                            modifier = Modifier,
                            isSelected = false
                        )
                    }
                }
            }
        }
    }
}