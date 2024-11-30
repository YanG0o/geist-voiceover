package com.kigi.baseview
import android.content.Intent
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment

/**
 * @Author : HeYan
 * @Time : 2023/01/07 16:46
 * @Description : BaseViewModel
 */
class ActivityResultLauncher {
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private var onResult: ((result: ActivityResult) -> Unit)? = {}

    fun onCreate(fragment: Fragment) {
        launcher =
            fragment.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                onActivityResult(it)
            }
    }

    fun onActivityCreate(activity: ComponentActivity) {
        Log.d("ActivityResultLauncher", "onActivityCreate() called1")
        launcher =
            activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                Log.d("ActivityResultLauncher", "onActivityCreate() called2")
                onActivityResult(it)
            }
    }


    fun launch(
        intent: Intent,
        options: ActivityOptionsCompat? = null,
        onResult: ((result: ActivityResult) -> Unit)? = {},
    ) {
        this.onResult = onResult
        launcher.launch(intent, options)
    }


    private fun onActivityResult(result: ActivityResult) {
        onResult?.invoke(result)
    }

}