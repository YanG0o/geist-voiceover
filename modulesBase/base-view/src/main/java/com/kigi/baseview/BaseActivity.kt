package com.kigi.baseview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kigi.baseview.viewmodel.BaseViewModel
import com.kigi.baseview.theme.GTheme
import com.kigi.baseview.theme.color_black
import com.kigi.baseview.theme.color_df
import com.kigi.commonutil.ViewUtil
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<out ViewModel : BaseViewModel> : ComponentActivity(),
    IViewModel<ViewModel> {

    final override val viewModel: ViewModel

    init {
        viewModel = findViewModelClass().getDeclaredConstructor().newInstance()
    }

    var activityResultLauncher = ActivityResultLauncher()

    var title by mutableStateOf("")
    var isShowHorizontalDivider by mutableStateOf(true)
//    var appBarHeight by mutableIntStateOf(44)

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        activityResultLauncher.onActivityCreate(this)
        observe(viewModel)
        setContent {
            val statusBarHeight by remember { mutableIntStateOf(ViewUtil.getStatusBarHeight(this)) }
            val navController = rememberNavController()
            GTheme(color = Color.White) {
                Scaffold(
                    topBar = {
                        AppBar(title,navController)
                    },
                    bottomBar = {},
                    floatingActionButton = {},
                    modifier = Modifier
                        .fillMaxSize()
//                        .background(color = Color.Blue)
                        .padding(top = statusBarHeight.dp)
                ) { innerPadding ->
                    Greeting(Modifier.padding(innerPadding),navController )
                    if (isShowHorizontalDivider) {
                        HorizontalDivider(
                            modifier = Modifier.padding(innerPadding),
                            color = color_df
                        )
                    }
                    LoadingDialog(viewModel)
                }
            }
        }

    }

    @Suppress("UNCHECKED_CAST")
    private fun findViewModelClass(): Class<ViewModel> {
        var thisClass: Class<*> = this.javaClass
        while (true) {
            (thisClass.genericSuperclass as? ParameterizedType)?.actualTypeArguments?.firstOrNull()
                ?.let {
                    return it as Class<ViewModel>
                }
                ?: run {
                    thisClass = thisClass.superclass ?: throw IllegalArgumentException()
                }
        }
    }

    @Composable
    abstract fun Greeting(modifier: Modifier, navController: NavHostController)

    fun show(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun <VM : BaseViewModel> observe(viewmodel: VM) {
        viewmodel.toastMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
        viewmodel.toastMessageRes.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }

    fun <T> observe(livedata: LiveData<T>, call: (data: T) -> Unit) {
        livedata.observe(this) {
            call(it)
        }
    }


    @Composable
    fun AppBar(title: String,navController: NavHostController) {

        Row(
            Modifier
                .height(44.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = {
                onBackPressed()
//                navController.popBackStack()
            }) {
                Icon(Icons.Default.ArrowBack, null)
            }
            Text(
                text = title,
                color = color_black,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
            IconButton(onClick = { }) {}
        }

//        TopAppBar(
////            colors = TopAppBarColors(
////                titleContentColor = Color.Red,
////                containerColor = Color.Blue,
////                scrolledContainerColor = Color.Blue,
////                navigationIconContentColor = Color.White,
////                actionIconContentColor = Color.Green
////            ),
//            modifier = Modifier.height(76.dp),
//            navigationIcon = {
//                IconButton(onClick = { finish() }) { Icon(Icons.Default.ArrowBack, null) }
//            },
//            title = {
//                Box(modifier = Modifier.fillMaxSize()) {
//                    Text(
//                        text = "Hello $title!",
//                        color = color_black,
//                        fontSize = 18.sp,
//                        fontWeight = FontWeight.Bold,
//                        modifier = Modifier
//                            .align(Alignment.Center)
//                    )
//                }
//            },
//            actions = {
//                InitActions()
//            }
//        )
    }

    @Composable
    protected fun InitActions() {
        IconButton(onClick = { }) { }
    }

}
