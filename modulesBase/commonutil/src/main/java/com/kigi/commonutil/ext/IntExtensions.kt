@file:JvmName("IntExtensions")
package com.kigi.commonutil.ext

import android.content.res.Resources
import java.text.DecimalFormat

/**
 * 保留两位小数
 */
fun Int.twoDecimal(): String = DecimalFormat("0.00").format(this)

/**
 * 超过max 显示 max+
 * eg:99+
 */
fun Int.ceiling(max: Int): String {
    return if (this > max) "${max}+" else "$this"
}

/**
 * 小余10时,高位自动补0; eg:5 ->"05"
 */
fun Int.alignment() = if (this < 10) "0$this" else "$this"
/**
 * 转dp
 */
//val Int.dp: Int
//    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()

/**
 * 转sp
 */
//val Int.sp: Int
//    get() = (this * Resources.getSystem().displayMetrics.scaledDensity + 0.5f).toInt()

/**
 * 补零 eg. 1->01
 */
fun Int.zeroCompensation(): String {
    return if (this < 10) "0$this" else "$this"
}

/**
 * 获取安全Int值
 */
fun Int?.safe(default: Int = 0): Int {
    return this ?: default
}
