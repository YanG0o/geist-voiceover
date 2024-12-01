package com.kigi.login.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Badge
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kigi.baseview.theme.color_999999
import com.kigi.baseview.theme.color_aa000000
import com.kigi.baseview.theme.color_black
import com.kigi.baseview.theme.color_df
import com.kigi.baseview.theme.color_white
import com.kigi.moduleres.NavConfig


@Composable
fun LoginScreen(modifier: Modifier = Modifier, navController: NavHostController) {

    var hasError by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }

    var passwordVisualTransformation by remember {
        mutableStateOf<VisualTransformation>(
            PasswordVisualTransformation()
        )
    }
    val emailInteractionState = remember { MutableInteractionSource() }
    val passwordInteractionState = remember { MutableInteractionSource() }

    val outlinedTextFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        backgroundColor = Color.Transparent, // 输入框背景颜色
        focusedBorderColor = color_black, // 输入框聚焦时的边框颜色
        disabledBorderColor = color_df, // 输入框禁用时的边框颜色
        cursorColor = color_black // 输入框光标颜色
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(com.kigi.moduleres.R.string.GW_LoginBtn),
            color = color_black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 14.dp, bottom = 36.dp)
                .background(Color.White)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            modifier = Modifier
                .height(19.dp)
                .background(color = Color.Transparent),
            text = stringResource(com.kigi.moduleres.R.string.GW_LoginPageEmailLbl),
            fontSize = 16.sp,
            color = color_black
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = email,
            maxLines = 1,
            isError = hasError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            colors = outlinedTextFieldColors,
//            label = { androidx.compose.material3.Text(text = "Email") },
//            placeholder = { androidx.compose.material3.Text(text = "input your email", color = color_999999) },
            onValueChange = {
                email = it
            },
            interactionSource = emailInteractionState,
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            modifier = Modifier
                .height(19.dp)
                .background(color = Color.Transparent),
            text = stringResource(com.kigi.moduleres.R.string.GW_LoginPagePasswordLbl),
            fontSize = 16.sp,
            color = color_black
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            trailingIcon = {
                IconButton(onClick = {
                    passwordVisualTransformation =
                        if (passwordVisualTransformation != VisualTransformation.None) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        }
                }) {
                    Icon(
                        painter = if (passwordVisualTransformation != VisualTransformation.None) {
                            painterResource(com.kigi.moduleres.R.mipmap.icon_basefunction_eye)
                        } else {
                            painterResource(com.kigi.moduleres.R.mipmap.icon_basefunction_no_eye)
                        },
                        null,
                    )
                }
            },
            colors = outlinedTextFieldColors,
            maxLines = 1,
            isError = hasError,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
//            label = { androidx.compose.material3.Text(text = "Password") },
//            placeholder = { androidx.compose.material3.Text(text = "input your pwd", color = color_999999) },
            onValueChange = {
                password = it
            },
            interactionSource = passwordInteractionState,
            visualTransformation = passwordVisualTransformation,
        )
        Spacer(modifier = Modifier.height(44.dp))
        Button(
            onClick = {
//                if (invalidInput(email.text, password.text)) {
//                    hasError = true
//                } else {
//                    hasError = false
//
//                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Text(
                text = stringResource(com.kigi.moduleres.R.string.GW_LoginBtn),
                color = color_white,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(com.kigi.moduleres.R.string.GW_ForgotYourPassword),
            color = color_black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    navController.navigate(NavConfig.LOGIN_FORGOT_PASSWORD)
                }
        )
        Spacer(modifier = Modifier.weight(1f))
        OutlinedButton(
            onClick = { }, modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Text(
                text = stringResource(com.kigi.moduleres.R.string.StartupPageCreateBtn),
                color = color_black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(54.dp))
    }
}

fun invalidInput(email: String, password: String) =
    email.isBlank() || password.isBlank()