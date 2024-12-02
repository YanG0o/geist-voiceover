package com.kigi.login.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.Color
import com.kigi.baseview.theme.GTheme
import com.kigi.login.ui.login.LoginGuideScreen
import com.kigi.moduleres.NavConfig

class LoginGuideActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)


        setContent {
            GTheme(color = Color.White) {
                LoginGuideScreen {
                    LoginActivity.start(this, it)
                    if (it == NavConfig.LOGIN) {
                        finish()
                    }
                }
            }
        }

    }

}