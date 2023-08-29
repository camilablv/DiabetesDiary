package com.ca.reminders.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.components.ReminderFloatingActionButton
import com.ca.designsystem.components.Tabs
import com.ca.domain.model.ListItem
import com.ca.domain.model.RecordGlucoseReminder
import com.ca.domain.model.RecordInsulinReminder
import com.ca.reminders.presentation.pages.GlucoseRemindersPage
import com.ca.reminders.presentation.pages.InsulinRemindersPage
import com.ca.reminders.presentation.pages.RemindersPage
import com.ca.reminders.presentation.pages.pages
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RemindersScreen(
    viewModel: RemindersViewModel = hiltViewModel(),
    navigateToAddInsulinReminder: () -> Unit,
    navigateToAddGlucoseReminder: () -> Unit,
    openInsulinReminderMenuBottomSheet: (Int) -> Unit,
    openGlucoseReminderMenuBottomSheet: (Int) -> Unit
) {

    val viewState by viewModel.viewState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState()

    Scaffold(
        floatingActionButton = {
           ReminderFloatingActionButton(
               pagerState = pagerState,
               navigateToAddInsulinReminder = navigateToAddInsulinReminder,
               navigateToAddGlucoseReminder = navigateToAddGlucoseReminder
           )
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        RemindersPager(
            modifier = Modifier
                .padding(paddingValues),
            pagerState = pagerState,
            scope = scope,
            viewState = viewState,
            insulinReminderEnabledChange = { reminder, enabled ->
                viewModel.setInsulinReminderEnabled(reminder, enabled)
            },
            glucoseReminderEnabledChange = { reminder, enabled ->
                viewModel.setGlucoseReminderEnabled(reminder, enabled)
            },
            onItemClick = {
                when(it) {
                    is RecordInsulinReminder -> { openInsulinReminderMenuBottomSheet(it.id) }
                    is RecordGlucoseReminder -> { openGlucoseReminderMenuBottomSheet(it.id) }
                }
            }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun RemindersPager(
    modifier: Modifier,
    pagerState: PagerState,
    scope: CoroutineScope,
    viewState: RemindersViewState,
    insulinReminderEnabledChange: (RecordInsulinReminder, Boolean) -> Unit,
    glucoseReminderEnabledChange: (RecordGlucoseReminder, Boolean) -> Unit,
    onItemClick: (ListItem) -> Unit
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
        ) { page ->
            when(pages[page]) {
                RemindersPage.InsulinRecords -> {
                    InsulinRemindersPage(
                        reminders = viewState.insulinReminders,
                        onEnabledChange = { reminder, enabled ->
                            insulinReminderEnabledChange(reminder, enabled)
                        },
                        onClick = { onItemClick(it) }
                    )
                }
                RemindersPage.GlucoseRecords -> {
                    GlucoseRemindersPage(
                        reminders = viewState.glucoseReminders,
                        onEnabledChange = { reminder, enabled ->
                            glucoseReminderEnabledChange(reminder, enabled)
                        },
                        onClick = { onItemClick(it) }
                    )
                }
            }
        }
    }
}
