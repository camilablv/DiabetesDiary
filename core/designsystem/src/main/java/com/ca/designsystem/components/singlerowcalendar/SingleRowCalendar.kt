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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ca.common.utils.formatMonthYear
import com.ca.common.utils.getPrevDates
import com.ca.common.utils.weekStartDate
import com.ca.designsystem.R
import com.ca.designsystem.components.singlerowcalendar.*
import com.ca.designsystem.theme.Theme
import kotlinx.coroutines.launch
import java.time.LocalDate
import kotlin.math.absoluteValue


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SingleRowCalendar(
    selectedDay: LocalDate,
    onSelectedDayChange: (LocalDate) -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage = Int.MAX_VALUE)
    val scope = rememberCoroutineScope()
    val visibleDate = remember { mutableStateOf(LocalDate.now()) }

    LaunchedEffect(pagerState.currentPage) {
        visibleDate.value = loadDates(pagerState.currentPage)[0]
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
                            scope.launch {
                                pagerState.animateScrollToPage(pagerState.currentPage - 1)
                            }
                        },
                    painter = painterResource(id = R.drawable.round_arrow_back),
                    contentDescription = null
                )
                Text(
                    modifier = Modifier,
                    text = visibleDate.value.formatMonthYear()
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
                    painter = painterResource(id = R.drawable.round_arrow_forward),
                    contentDescription = null,
                    tint = if (pagerState.canScrollForward) Theme.colors.onBackground else Color.Gray.copy(alpha = 0.3f)
                )
            }

            CalendarPager(
                pagerState = pagerState
            ) { currentPage ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    loadDates(currentPage).forEach {
                        Day(
                            date = it,
                            onClick = { date ->
                                onSelectedDayChange(date)
                            },
                            modifier = Modifier,
                            isSelected = it == selectedDay
                        )
                    }
                }
            }
        }
    }
}

private fun loadDates(page: Int): List<LocalDate> {
    val invertedPage = page.minus(Int.MAX_VALUE).plus(1).absoluteValue
    return LocalDate.now().weekStartDate().getPrevDates(invertedPage)
}