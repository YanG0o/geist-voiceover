package com.kigi.commonutil

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Build

object ViewUtil {

    fun dp2px(dpValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dp(pxValue: Float): Int {
        val scale = Resources.getSystem().displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }
    fun getDisplayHeight(): Int {
        return Resources.getSystem().displayMetrics.heightPixels
    }

    fun getDisplayWidth(): Int {
        return Resources.getSystem().displayMetrics.widthPixels
    }

    /**
     * 通过反射的方式获取状态栏高度
     *
     * @return
     */
    @SuppressLint("PrivateApi", "DiscouragedApi", "InternalInsetResource")
    fun getStatusBarHeight(context: Context): Int {
        val resources = context.resources
        try {
            return if (Build.VERSION.SDK_INT < 30) {
                val c = Class.forName("com.android.internal.R\$dimen")
                val obj = c.newInstance()
                val field = c.getField("status_bar_height")
                val x = field[obj]?.toString()?.toInt()
                px2dp(resources.getDimensionPixelSize(x!!).toFloat())
            } else {
                val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
                px2dp(resources.getDimensionPixelSize(resourceId).toFloat())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return 25
    }

    /**
     * 获取底部导航栏高度
     *
     * @return
     */
    @SuppressLint("DiscouragedApi", "InternalInsetResource")
    fun getNavigationBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        //获取NavigationBar的高度
        return resources.getDimensionPixelSize(resourceId)
    }

}