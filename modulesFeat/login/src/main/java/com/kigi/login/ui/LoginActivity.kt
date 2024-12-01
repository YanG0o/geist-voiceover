package com.kigi.login.ui

import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kigi.baseview.BaseActivity
import com.kigi.baseview.theme.color_black
import com.kigi.baseview.viewmodel.BaseViewModel
import com.kigi.login.ui.login.LoginScreen
import com.kigi.moduleres.NavConfig

class LoginActivity : BaseActivity<BaseViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isShowHorizontalDivider = false

    }

    @Composable
    override fun Greeting(modifier: Modifier, navController: NavHostController) {

        NavScreen(modifier, navController)
    }
}


@Composable
fun NavScreen(modifier: Modifier, navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = NavConfig.LOGIN
    ) {
        composable(NavConfig.LOGIN) {
            LoginScreen(modifier, navController)
        }

        composable(NavConfig.LOGIN_FORGOT_PASSWORD) {
//            MessageDetailScreen(navController = navController)
        }

        composable(NavConfig.LOGIN_CREATE_ACCOUNT) {
//            CreateMessageScreen(navController = navController)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginScreen(modifier = Modifier, rememberNavController())
}