package com.kigi.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.kigi.baseview.BaseActivity
import com.kigi.baseview.viewmodel.BaseViewModel

class TestActivity:BaseActivity<BaseViewModel>() {
    override fun initData() {

    }

    @Composable
    override fun Greeting(modifier: Modifier,navController: NavHostController) {
        BaseView("TestActivity",modifier)
    }

    @Composable
    fun BaseView(name: String, modifier: Modifier = Modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Blue)
        ) {
            Text(
                text = "Hello $name!",
                color = Color.Red,
                fontSize = 28.sp,
                fontStyle = FontStyle.Italic,
                fontWeight = FontWeight.Bold,
                modifier = modifier
                    .background(Color.Black)
                    .padding(10.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }

    }
}