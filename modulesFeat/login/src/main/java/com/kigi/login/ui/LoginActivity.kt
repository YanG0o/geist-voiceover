package com.kigi.login.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kigi.baseview.BaseActivity
import com.kigi.baseview.viewmodel.BaseViewModel
import com.kigi.login.ui.login.LoginScreen
import com.kigi.moduleres.NavConfig


class LoginActivity : BaseActivity<BaseViewModel>() {

    private var defaultDestination by mutableStateOf(NavConfig.LOGIN)

    companion object {
        fun start(context: Context, routerId: String) {
            context.startActivity(Intent(context, LoginActivity::class.java).apply {
                putExtra("routerId", routerId)
            })
        }
    }

    override fun initData() {
        if (intent.hasExtra("routerId")) {
            defaultDestination = intent.getStringExtra("routerId") ?: NavConfig.LOGIN
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isShowHorizontalDivider = false

    }

    @Composable
    override fun Greeting(modifier: Modifier, navController: NavHostController) {

        NavHost(
            navController = navController,
            startDestination = defaultDestination,
            enterTransition = {EnterTransition.None},
            exitTransition = { ExitTransition.None}
        ) {
            composable(NavConfig.LOGIN) {
                LoginScreen(modifier, navController)
            }

            composable(NavConfig.LOGIN_FORGOT_PASSWORD) {
            }

            composable(NavConfig.LOGIN_CREATE_ACCOUNT) {
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LoginScreen(modifier = Modifier, rememberNavController())
}