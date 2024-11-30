package com.higo.voiceover

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ContentView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.higo.voiceover.ui.theme.AppTheme
import com.kigi.baseview.BaseActivity
import com.kigi.baseview.viewmodel.BaseViewModel

class MainActivity : BaseActivity<BaseViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContent {
//            AppTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
    }

    @Composable
    override fun Greeting(modifier: Modifier) {
        BaseView(name = "Android", modifier = modifier)
    }
}


@Composable
fun BaseView(name: String, modifier: Modifier = Modifier) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .background(Color.Blue)) {
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AppTheme {
        BaseView("Android")
    }
}