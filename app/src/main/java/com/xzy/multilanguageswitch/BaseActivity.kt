package com.xzy.multilanguageswitch

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.xzy.multilanguageswitch.language.LanguageUtil

/**
 * Author: xzy
 * Date:
 * Description:
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun attachBaseContext(newBase: Context?) {
        // 获取我们存储的语言环境 比如 "en","zh",等等
        val language = Sp.get("language")
        // attach对应语言环境下的context
        val context = LanguageUtil.attachBaseContext(newBase!!, language!!)
        super.attachBaseContext(context)
    }
}