package com.xzy.multilanguageswitch


import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.xzy.multilanguageswitch.language.LanguageType
import com.xzy.multilanguageswitch.language.LanguageUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }


    private fun initView() {
        // 根据系统首选语言确定刚进入时需要显示的界面
        when (Sp.get("language")) {
            LanguageType.ENGLISH.language -> {
                tv_test.text = getString(R.string.test)
            }
            LanguageType.CHINESE.language -> {
                tv_test.text = getString(R.string.test)
            }
        }
        // 切换为中文
        btn_zh.setOnClickListener {
            if (Sp.get("language") == "zh") {
                // 如果当前已经是中文，则不做任何操作
                return@setOnClickListener
            }
            changeLanguage(LanguageType.CHINESE.language)
        }

        // 切换为英文
        btn_en.setOnClickListener {
            if (Sp.get("language") == "en") {
                // 如果当前已经是英文，则不做任何操作
                return@setOnClickListener
            }
            changeLanguage(LanguageType.ENGLISH.language)
        }
    }

    /**
     * 经过测试：android 8.0 以下的版本需要更新 configuration 和 resources，
     * android 8.0 以上只需要将当前的语言环境写入 Sp 文件即可。
     * 测试机型 android4.4、android6.0、android7.0、android7.1、android8.1
     * 然后，重新创建当前页面。
     * @param language
     */
    private fun changeLanguage(language: String?) {
        // 版本低于 android 8.0 不执行该方法
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // 注意，这里的 context 不能传 Application 的 context
            LanguageUtil.changeAppLanguage(this, language!!)
        }
        Sp.put("language", language!!)
        // 不同的版本，使用不同的重启方式，达到最好的效果
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            // 6.0 以及以下版本，使用这种方式，并给 activity 添加启动动画效果，可以规避黑屏和闪烁问题
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        } else {
            // 6.0 以上系统直接调用重新创建函数，可以达到无缝切换的效果
            recreate()
        }
    }
}
