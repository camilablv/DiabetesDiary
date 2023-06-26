package com.ca.onboarding.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            pages = List(5) {},
            pagerState = pagerState,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            viewModel
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingPager(
    pages: List<Unit>,
    pagerState: PagerState,
    modifier: Modifier,
    viewModel: OnBoardingViewModel
) {

    val scope = rememberCoroutineScope()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        PagerIndicator(
            size = pages.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier
                .weight(1f)
        )

        HorizontalPager(
            pageCount = 5,
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(11f)
        ) { page ->
            when(page) {
                0 -> {
                    AddInsulinPage(
                        viewState.insulins
                    ) {
                        viewModel.addInsulin(it)
                    }
                }
                1 -> {
                    ChooseGlucoseUnitsPage(
                        defaultUnit = viewState.units.unit,
                        select = { viewModel.updateGlucoseUnits(it) }
                    )
                }
                2 -> {
                    Text(
                        text = "Page 3",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                3 -> {
                    Text(text = "Page 4",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
                4 -> {
                    Text(text = "Page 5",
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }
            ) {
                Text(text = "Previous")
            }

            Button(
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            ) {
                Text(
                    text = "Next"
                )
            }
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
//    val width = animateDpAsState(targetValue = if (selected) 54.dp else 10.dp)

    Box(
        modifier = Modifier
            .width(64.dp)
            .padding(4.dp)
            .height(8.dp)
            .clip(CircleShape)
            .background(if (selected) Purple200 else Color.Gray.copy(0.5f))
    )
}