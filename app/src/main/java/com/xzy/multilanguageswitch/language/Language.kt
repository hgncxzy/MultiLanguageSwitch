package com.xzy.multilanguageswitch.language

import android.annotation.TargetApi
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.LocaleList
import android.text.TextUtils

import java.util.Locale

/**
 * Created by xzy .
 */
enum class LanguageType {

    CHINESE("zh"),
    ENGLISH("en");

    var language: String?
        get() {
            return field ?: ""
        }

    constructor(language: String?) {
        this.language = language
    }
}

@Suppress("unused")
object LanguageUtil {
    private val TAG = "LanguageUtil"
    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    /**
     * @param context 上下文
     * @param newLanguage 想要切换的语言类型 比如 "en" ,"zh"
     */
    fun changeAppLanguage(context: Context, newLanguage: String) {
        if (TextUtils.isEmpty(newLanguage)) {
            return
        }
        val resources = context.resources
        val configuration = resources.configuration
        // 获取想要切换的语言类型
        val locale = getLocaleByLanguage(newLanguage)
        configuration.setLocale(locale)
        // updateConfiguration
        val dm = resources.displayMetrics
        resources.updateConfiguration(configuration, dm)
    }

    private fun getLocaleByLanguage(language: String): Locale {
        var locale = Locale.SIMPLIFIED_CHINESE
        if (language == LanguageType.CHINESE.language) {
            locale = Locale.SIMPLIFIED_CHINESE
        } else if (language == LanguageType.ENGLISH.language) {
            locale = Locale.ENGLISH
        }
        return locale
    }

    fun attachBaseContext(context: Context, language: String): Context {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else {
            context
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String): Context {
        val resources = context.resources
        val locale = getLocaleByLanguage(language)
        val configuration = resources.configuration
        configuration.setLocale(locale)
        configuration.setLocales(LocaleList(locale))
        return context.createConfigurationContext(configuration)
    }
}
