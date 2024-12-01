package com.kigi.commonutil

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates


abstract class CommonBaseApp : Application() {

    companion object{
       var context: Context by Delegates.notNull()
        //设置全局的Header构建器
        private var instance: CommonBaseApp by Delegates.notNull()
        fun instance() = instance
    }


    override fun onCreate() {
        super.onCreate()
        instance = this

        context = applicationContext

    }
}