package com.kigi.login.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kigi.baseview.theme.color_66b32e
import com.kigi.baseview.theme.color_black
import com.kigi.baseview.theme.color_white
import com.kigi.moduleres.NavConfig


@Composable
fun LoginGuideScreen(onClick: (routerId: String) -> Unit) {
    Box(Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(com.kigi.moduleres.R.mipmap.icon_basefunction_login_bg),
            null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(com.kigi.moduleres.R.mipmap.icon_basefunction_login_name),
            null,
            Modifier
                .width(225.dp)
                .align(Alignment.Center)
        )
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(Modifier.weight(1f))

            Button(
                onClick = { onClick.invoke(NavConfig.LOGIN) },
                colors = ButtonDefaults.buttonColors(backgroundColor = color_66b32e),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = stringResource(com.kigi.moduleres.R.string.GW_LoginBtn),
                    color = color_white,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { onClick.invoke(NavConfig.LOGIN_CREATE_ACCOUNT) },
                colors = ButtonDefaults.buttonColors(backgroundColor = color_white),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
            ) {
                Text(
                    text = stringResource(com.kigi.moduleres.R.string.StartupPageCreateBtn),
                    color = color_black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(56.dp))
        }
    }
}