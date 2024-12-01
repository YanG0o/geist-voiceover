package com.kigi.baseview.cache

import com.kigi.baseview.ThemeKinds
import com.kigi.commonutil.DataSaverPreference
import com.kigi.commonutil.SharePreferenceUtils
import com.kigi.https.HttpConstant
import com.kigi.https.pojo.UserBean
import kotlinx.coroutines.flow.Flow


object UserCache {
    const val THEME_KINDS = "THEME_KINDS"

    //是否已同意隐私政策h
    const val KEY_ALLOWED_PROTOCOL = "KEY_ALLOWED_PROTOCOL"

    private var userInfo: UserBean? = null
    private var token: String by SharePreferenceUtils(HttpConstant.TOKEN_KEY, "")

//    var themeKinds: ThemeKinds by SharePreferenceUtils(THEME_KINDS, ThemeKinds.DEFAULT)
    var themeKinds: ThemeKinds by DataSaverPreference(THEME_KINDS, ThemeKinds.DEFAULT)

    fun refreshUserData(userBean: UserBean?) {
        userInfo = userBean
        token = userBean?.token ?: ""
    }

    fun getUserToken(): String {
        return token
    }

    fun clearUserToken() {
        token = ""
    }

    fun getUserData(): UserBean? {
        return userInfo
    }

}