package com.kigi.baseview

import android.annotation.SuppressLint
import android.provider.Settings
import android.util.Log
import androidx.annotation.Keep
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import com.funny.data_saver.core.mutableDataSaverStateOf
import com.kigi.baseview.bean.TranslationConfig
import com.kigi.baseview.theme.ThemeConfig
import com.kigi.baseview.theme.ThemeType
import com.kigi.baseview.translate.Language
import com.kigi.commonutil.CommonBaseApp
import com.kigi.commonutil.DefaultDataSaverPreferences
import com.kigi.https.pojo.UserBean
import java.math.BigDecimal

private const val TAG = "AppConfig"

@SuppressLint("HardwareIds")
@Keep
object AppConfig {

    var SCREEN_WIDTH = 0
    var SCREEN_HEIGHT = 0

    var userInfo = mutableDataSaverStateOf(DefaultDataSaverPreferences, Consts.KEY_USER_INFO, UserBean())


    // 隐私合规，延迟获取
    val androidId: String by lazy {
        Log.d(TAG, "get Android_ID")
        Settings.Secure.getString(CommonBaseApp.context.contentResolver, Settings.Secure.ANDROID_ID) ?: ""
    }

    // 下面为可设置的状态
    val sTextMenuFloatingWindow = mutableDataSaverStateOf(DefaultDataSaverPreferences, "KEY_TEXT_MENU_FLOATING_WINDOW", false)
    val sSpringFestivalTheme = mutableDataSaverStateOf(DefaultDataSaverPreferences, Consts.KEY_SPRING_THEME, true)
    val sEnterToTranslate = mutableDataSaverStateOf(DefaultDataSaverPreferences, Consts.KEY_ENTER_TO_TRANSLATE, false)
    val sHideBottomNavBar = mutableDataSaverStateOf(DefaultDataSaverPreferences, Consts.KEY_CRASH_MSG, false)
    val sAutoFocus = mutableDataSaverStateOf(DefaultDataSaverPreferences, "KEY_AUTO_FOCUS", false)
    val sShowFloatWindow = mutableDataSaverStateOf(DefaultDataSaverPreferences, Consts.KEY_SHOW_FLOAT_WINDOW, false)
    val sDefaultSourceLanguage = mutableDataSaverStateOf(DefaultDataSaverPreferences, "KEY_DEFAULT_SOURCE_LANGUAGE", Language.AUTO)
    val sDefaultTargetLanguage = mutableDataSaverStateOf(DefaultDataSaverPreferences, "KEY_DEFAULT_TARGET_LANGUAGE", Language.CHINESE)

    // 以下为Pro专享
    val sParallelTrans = mutableDataSaverStateOf(DefaultDataSaverPreferences, "KEY_PARALLEL_TRANS", false)
    val sShowDetailResult = mutableDataSaverStateOf(DefaultDataSaverPreferences, "KEY_SHOW_DETAIL_RESULT", false)
    val sExpandDetailByDefault = mutableDataSaverStateOf(DefaultDataSaverPreferences, "KEY_EXPAND_DETAIL_BY_DEFAULT", false)

    // 以下为开发者专享
    val developerMode = mutableDataSaverStateOf(DefaultDataSaverPreferences, "KEY_DEVELOPER_MODE", false)


    // 开启 VIP 的一些功能，供体验
    fun enableVipFeatures(){
        sParallelTrans.value = true
        sShowDetailResult.value = true
    }

    private fun disableVipFeatures(){
        sParallelTrans.value = false
        sShowDetailResult.value = false
        ThemeConfig.updateThemeType(ThemeType.Default)
    }

    fun logout(){
        userInfo.value = UserBean()
        disableVipFeatures()
    }
}

val GlobalTranslationConfig = TranslationConfig()
// 外部 intent 导致，表示待会儿需要做翻译
// 不用 DeepLink
var NeedToTransConfig = TranslationConfig()