package com.ca.designsystem.components.pickers

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ca.designsystem.theme.Theme
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import dev.chrisbanes.snapper.SnapperLayoutInfo
import dev.chrisbanes.snapper.rememberLazyListSnapperLayoutInfo
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class, ExperimentalSnapperApi::class)
@Composable
fun NumberWheelPicker(
    modifier: Modifier,
    startItem: Int,
    count: Int,
    visibleItemsCount: Int,
    size: DpSize
) {

    val lazyListState = rememberLazyListState(startItem)
    val snippetLayoutInfo = rememberLazyListSnapperLayoutInfo(lazyListState = lazyListState)

    Box(
        modifier = modifier
            .width(size.width)
    ) {
        LazyRow(
            modifier = Modifier,
            state = lazyListState,
            flingBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
        ) {
            items(count) {

                Text(
                    modifier = Modifier
                        .width(size.width / visibleItemsCount)
                        .alpha(
                            calculateAlpha(
                                lazyListState = lazyListState,
                                index = it,
                                snapperLayoutInfo = snippetLayoutInfo,
                                visibleItemsCount = visibleItemsCount
                            )
                        )
                        .animateContentSize(),
                    text = it.toString(),
                    style = Theme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun calculateAlpha(
    lazyListState: LazyListState,
    index: Int,
    snapperLayoutInfo: SnapperLayoutInfo,
    visibleItemsCount: Int
): Float {

    val distanceToIndexSnap = snapperLayoutInfo.distanceToIndexSnap(index).absoluteValue
    val layoutInfo = remember { derivedStateOf { lazyListState.layoutInfo } }.value
    val viewPortHeight = layoutInfo.viewportSize.height.toFloat()
    val singleViewPortHeight = viewPortHeight / visibleItemsCount

    return if(distanceToIndexSnap in 0..singleViewPortHeight.toInt()) {
        1.2f - (distanceToIndexSnap / singleViewPortHeight)
    } else {
        0.2f
    }
}

@OptIn(ExperimentalSnapperApi::class)
@Composable
fun calculateItemSize(
    lazyListState: LazyListState,
    index: Int,
    snapperLayoutInfo: SnapperLayoutInfo,
    visibleItemsCount: Int
): TextUnit {

    val distanceToIndexSnap = snapperLayoutInfo.distanceToIndexSnap(index).absoluteValue
    val layoutInfo = remember { derivedStateOf { lazyListState.layoutInfo } }.value
    val viewPortHeight = layoutInfo.viewportSize.height.toFloat()
    val singleViewPortHeight = viewPortHeight / visibleItemsCount

    return if(distanceToIndexSnap in 0..singleViewPortHeight.toInt()) {
        32.sp
    } else {
        16.sp
    }
}

