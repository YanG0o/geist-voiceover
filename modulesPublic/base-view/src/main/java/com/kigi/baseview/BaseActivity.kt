package com.kigi.baseview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.kigi.baseview.viewmodel.BaseViewModel
import com.kigi.baseview.theme.GTheme
import com.kigi.commonutil.ViewUtil
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<out ViewModel : BaseViewModel> : ComponentActivity(),
    IViewModel<ViewModel> {

    final override val viewModel: ViewModel

    init {
        viewModel = findViewModelClass().getDeclaredConstructor().newInstance()
    }

    var activityResultLauncher = ActivityResultLauncher()


    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        activityResultLauncher.onActivityCreate(this)
        observe(viewModel)
        setContent {
            val statusBarHeight by remember { mutableIntStateOf(ViewUtil.getStatusBarHeight(this)) }
            GTheme(color = Color.White) {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Transparent)
                        .padding(top = statusBarHeight.dp)
                ) { innerPadding ->
                    Greeting(Modifier.padding())
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
    abstract fun Greeting(modifier: Modifier)

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


}
