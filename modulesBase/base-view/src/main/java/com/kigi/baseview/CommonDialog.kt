package com.kigi.baseview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.kigi.baseview.theme.color_333333
import com.kigi.baseview.theme.color_black
import com.kigi.baseview.theme.color_df
import com.kigi.baseview.theme.color_white

@Composable
fun CommonDialog(
    title: String,
    content: @Composable () -> Unit,
    leftText: String,
    rightText: String,
    onLeftClick: () -> Unit,
    onRightClick: () -> Unit,
    onDismiss: () -> Unit = {},
    cancelAble: Boolean = true
) {
    Dialog(
        onDismissRequest = { onDismiss() }, properties = DialogProperties(
            dismissOnBackPress = cancelAble, dismissOnClickOutside = cancelAble
        )
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = color_white,
            modifier = Modifier.width(266.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 15.dp, bottom = 8.dp),
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = color_black,
                    textAlign = TextAlign.Center,
                )

                content()

                HorizontalDivider(
                    thickness = 1.dp,
                    color = color_df,
                    modifier = Modifier.fillMaxWidth()
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Box(modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onLeftClick()
                    }) {
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp),
                            text = leftText,
                            fontSize = 16.sp,
                            color = color_333333,
                            textAlign = TextAlign.Center,
                        )
                    }
                    HorizontalDivider(
                        thickness = 44.dp,
                        color = color_df,
                        modifier = Modifier.width(1.dp)
                    )
                    Box(modifier = Modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onRightClick()
                    }) {
                        Text(
                            modifier = Modifier.padding(vertical = 10.dp, horizontal = 30.dp),
                            text = rightText,
                            fontSize = 16.sp,
                            color = color_333333,
                            textAlign = TextAlign.Center,
                        )
                    }

                }
            }
        }
    }
}
