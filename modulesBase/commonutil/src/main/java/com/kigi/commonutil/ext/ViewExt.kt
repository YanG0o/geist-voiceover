package com.kigi.commonutil.ext

import android.text.Spannable
import android.text.SpannableString
import android.view.View

fun View.quickBuildSpan(text: CharSequence, vararg spans: Any): CharSequence {
    return SpannableString(text).apply {
        spans.forEach {
            setSpan(it, 0, text.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
    }
}