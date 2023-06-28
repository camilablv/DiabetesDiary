package com.ca.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.theme.*
import com.ca.onboarding.presentation.onboardingpages.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navigateToHome: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState()


    Scaffold { paddingValues ->
        OnBoardingPager(
            pages = pages,
            pagerState = pagerState,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            viewModel = viewModel,
            toHome = navigateToHome
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingPager(
    pages: List<Page>,
    pagerState: PagerState,
    modifier: Modifier,
    viewModel: OnBoardingViewModel,
    toHome: () -> Unit
) {

    val scope = rememberCoroutineScope()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        PagerIndicator(
            size = pages.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier
                .weight(1f)
        )

        HorizontalPager(
            pageCount = pages.size,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(11f)
        ) { index ->
            when(pages[index]) {
                Page.Welcome -> {
                    WelcomePage()
                }
                Page.AddInsulin -> {
                    AddInsulinPage(
                        viewState.insulins
                    ) { name, color, dose ->
                        viewModel.addInsulin(name, color, dose)
                    }
                }
                Page.GlucoseUnits -> {
                    ChooseGlucoseUnitsPage(
                        defaultUnit = viewState.units.unit,
                        select = { viewModel.updateGlucoseUnits(it) }
                    )
                }
                Page.Page4 -> {
                    Text(text = "Page 4",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                Page.Page5 -> {
                    Text(text = "Page 5",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }

        PagerButtons(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            onSkip = toHome,
            onNext = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        )
    }
}

@Composable
fun PagerButtons(
    modifier: Modifier,
    onSkip: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = onSkip
        ) {
            Text(text = "Skip")
        }

        IconButton(
            onClick = onNext
        ) {
            Icon(
                painter = painterResource(id = com.ca.designsystem.R.drawable.arrow_right),
                contentDescription = "",
                tint = Theme.colors.secondary,
                modifier = Modifier
                    .size(64.dp)
            )
        }
    }
}

@Composable
fun PagerIndicator(
    size: Int,
    currentPage: Int,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(top = 20.dp)
    ) {
        repeat(size) {
            Indicator(selected = it <= currentPage)
        }
    }
}

@Composable
fun Indicator(selected: Boolean) {
    Box(
        modifier = Modifier
            .width(64.dp)
            .padding(4.dp)
            .height(8.dp)
            .clip(CircleShape)
            .background(if (selected) Purple200 else Color.Gray.copy(0.5f))
    )
}