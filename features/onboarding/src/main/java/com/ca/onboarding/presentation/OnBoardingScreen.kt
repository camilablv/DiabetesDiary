package com.ca.onboarding.presentation

import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ca.designsystem.R
import com.ca.designsystem.components.dialog.EditInsulinDialog
import com.ca.designsystem.theme.*
import com.ca.onboarding.presentation.onboardingpages.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navigateToHome: () -> Unit,
    viewModel: OnBoardingViewModel = hiltViewModel()
) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { pages.size }
    )

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
            .padding(horizontal = 16.dp, vertical = 16.dp),
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
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
                .weight(11f),
        ) { index ->
            when(pages[index]) {
                Page.Welcome -> {
                    WelcomePage()
                }
                Page.AddInsulin -> {
                    AddInsulinPage(
                        viewState.insulins,
                        revealedInsulin = viewState.revealedInsulin,
                        setRevealedInsulin = viewModel::setRevealedInsulin,
                        addInsulin = { viewModel.showEditInsulinDialog(true) },
                        deleteInsulin = { viewModel.deleteInsulin(it) },
                        editInsulin = {
                            viewModel.showEditInsulinDialog(true)
                            viewModel.setRevealedInsulin(it)
                        }
                    )
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
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            toHome = {
                viewModel.completeOnBoarding()
                toHome()
             },
            onNext = {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        )
    }

    EditInsulinDialog(
        show = viewState.showEditInsulinDialog,
        edit = { id, name, color, dose -> viewModel.editInsulin(id, name, color, dose) },
        onDismiss = { viewModel.showEditInsulinDialog(false) },
        editableInsulin = viewState.revealedInsulin
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerButtons(
    state: PagerState,
    modifier: Modifier,
    toHome: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            onClick = toHome
        ) {
            Text(text = stringResource(id = R.string.skip))
        }

        if (state.canScrollForward) {
            IconButton(
                modifier = Modifier
                    .size(64.dp),
                onClick = onNext
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = "",
                    tint = Theme.colors.secondary,
                    modifier = Modifier
                )
            }
        } else {
            Button(onClick = toHome) {
                Text(text = stringResource(id = R.string.get_started))
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
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        repeat(size) {
            Indicator(
                selected = it <= currentPage,
                currentPage = it == currentPage
            )
        }
    }
}

@Composable
fun Indicator(
    selected: Boolean,
    currentPage: Boolean
) {
    val animatedWidth by animateDpAsState(targetValue = if (currentPage) 24.dp else 8.dp, label = "")

    Box(
        modifier = Modifier
            .width(animatedWidth)
            .height(8.dp)
            .clip(CircleShape)
            .background(Color.Gray.copy(alpha = 0.3f))
    )
}