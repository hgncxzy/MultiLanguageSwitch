package com.xzy.multilanguageswitch

import android.content.SharedPreferences
import android.content.Context.MODE_PRIVATE
import com.xzy.multilanguageswitch.RootApp.Companion.INSTANCE


object Sp {
    private var sp: SharedPreferences? = null

    /**
     * 存入
     */
    fun put(key: String, language: String) {
        if (sp == null) {
            sp = INSTANCE.applicationContext.getSharedPreferences("config", MODE_PRIVATE)
        }
        val editor = sp?.edit()
        editor?.putString(key, language)
        editor?.commit()
    }

    /**
     * 读取
     */
    fun get(key: String): String? {
        if (sp == null) {
            sp = INSTANCE.applicationContext.getSharedPreferences("config", MODE_PRIVATE)
        }
        return sp?.getString(key, "")
    }
}