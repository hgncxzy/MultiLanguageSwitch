package com.xzy.multilanguageswitch

import android.app.Application
import android.util.Log
import java.util.*

class RootApp : Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        Log.d("", "初始化 Application")
        // 获取系统当前的语言环境
        val locale = Locale.getDefault().language
        Sp.put("language",locale)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Log.e("", "onTrimMemory, level = $level")
    }

    companion object {
        lateinit var INSTANCE: RootApp
    }
}
