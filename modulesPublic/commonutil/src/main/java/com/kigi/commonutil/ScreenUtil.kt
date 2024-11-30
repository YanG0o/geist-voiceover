package com.kigi.commonutil

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import com.kigi.commonutil.CommonBaseApp

object ScreenUtil {
    fun getAppScreenWidth(): Int {
        val wm = CommonBaseApp.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            ?: return -1
        val point = Point()
        wm.defaultDisplay.getSize(point)
        return point.x
    }
}