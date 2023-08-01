package com.ca.designsystem.components.singlerowcalendar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.common.utils.date
import com.ca.designsystem.components.singlerowcalendar.*
import com.ca.designsystem.theme.Theme
import kotlinx.coroutines.launch
import java.time.LocalDate

data class CalendarState(
    val prevWeek: List<LocalDate>,
    val currentWeek: List<LocalDate>,
    val nextWeek: List<LocalDate>
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SingleRowCalendar(
    selectedDay: String = "",
    onSelectedDayChange: (LocalDate) -> Unit = {},
    loadNextWeek: (nextWeekDate: LocalDate) -> Unit,
    loadPrevWeek: (endWeekDate: LocalDate) -> Unit,
    loadedDates: Array<List<LocalDate>>
) {
    val pagerState = rememberPagerState(initialPage = 1)
    val scope = rememberCoroutineScope()

//    val dateCursor by remember {
//        mutableStateOf(LocalDate.now().weekStartDate())
//    }
//
//    var visibleDates by remember {
//        mutableStateOf(dateCursor.startDates())
//    }

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
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        },
                    painter = painterResource(id = com.ca.designsystem.R.drawable.round_arrow_back),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier,
                    text = LocalDate.now().date()
                )

                Icon(
                    modifier = Modifier
                        .clickable(
                            enabled = pagerState.canScrollForward
                        ) {
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            }
                        },
                    painter = painterResource(id = com.ca.designsystem.R.drawable.round_arrow_forward),
                    contentDescription = null,
                    tint = if (pagerState.canScrollForward) Theme.colors.onBackground else Color.Gray.copy(alpha = 0.3f)
                )
            }

            CalendarPager(
                pagerState = pagerState,
                loadedDates = loadedDates,
                loadNextDates = loadNextWeek,
                loadPrevDates = loadPrevWeek
            ) { currentPage ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    loadedDates[currentPage].forEach {
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