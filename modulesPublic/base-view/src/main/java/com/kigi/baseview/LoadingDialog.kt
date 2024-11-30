package com.kigi.baseview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.kigi.baseview.theme.color_white
import com.kigi.baseview.viewmodel.BaseViewModel

/**
 * @Author : HeYan
 * @Time : 2022/12/30 11:17
 * @Description : ⽂件描述
 */
@Composable
fun LoadingDialog(
    viewModel: BaseViewModel,
    cancelAble: Boolean = true
) {

    val speed by remember { mutableFloatStateOf(1f) }
    val isPlaying by remember { mutableStateOf(viewModel.isShowLoading) }

    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.corver),
        imageAssetsFolder = "loading/"
//        spec = LottieCompositionSpec.Asset("sound.json")
    )

    val lottieAnimationState by animateLottieCompositionAsState(
        composition = lottieComposition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = true,
    )

    if (viewModel.isShowLoading)
        Dialog(
            onDismissRequest = { viewModel.isShowLoading = false },
            properties = DialogProperties(
                dismissOnBackPress = cancelAble,
                dismissOnClickOutside = cancelAble
            ),
        ) {
            Box(Modifier.background(Color.Transparent)) {
                LottieAnimation(
                    composition = lottieComposition,
                    lottieAnimationState,
                    modifier = Modifier.width(150.dp)
                )
            }
        }

}
