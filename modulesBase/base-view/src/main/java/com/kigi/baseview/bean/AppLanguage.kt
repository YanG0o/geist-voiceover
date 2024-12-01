package com.kigi.baseview.bean

import com.kigi.baseview.R
import com.kigi.commonutil.CommonBaseApp
import java.util.Locale

enum class AppLanguage(private val descriptionId: Int) {
    FOLLOW_SYSTEM(R.string.follow_system),
    ENGLISH(R.string.language_english),
    CHINESE(R.string.language_chinese);

    val description = CommonBaseApp.context.getString(descriptionId)

    fun toLocale(): Locale = when (this) {
        FOLLOW_SYSTEM -> Locale.getDefault()
        ENGLISH -> Locale.ENGLISH
        CHINESE -> Locale.CHINESE
    }

    override fun toString(): String {
        return CommonBaseApp.context.resources.getString(descriptionId)
    }

}