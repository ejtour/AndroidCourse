package com.drak.application

import android.annotation.TargetApi
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.multidex.MultiDex
import com.drak.Constant
import com.tencent.bugly.Bugly
import com.tencent.bugly.Bugly.applicationContext
import com.tencent.bugly.beta.Beta
import com.tencent.tinker.entry.DefaultApplicationLike
import kotlin.properties.Delegates

/**
 * @author BuMingYang
 * @des
 */
class ApplicationLike(
    application: Application?, tinkerFlags: Int,
    tinkerLoadVerifyFlag: Boolean, applicationStartElapsedTime: Long,
    applicationStartMillisTime: Long, tinkerResultIntent: Intent?
) : DefaultApplicationLike(
    application,
    tinkerFlags,
    tinkerLoadVerifyFlag,
    applicationStartElapsedTime,
    applicationStartMillisTime,
    tinkerResultIntent
) {

    companion object {

        var context: Context by Delegates.notNull()
            private set

        lateinit var instance: Application

    }

    override fun onCreate() {
        super.onCreate()
        instance = application
        context = application.applicationContext

        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        // 调试时，将第三个参数改为true
        Bugly.init(application, Constant.BUGLY_KEY, true)
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    override fun onBaseContextAttached(base: Context) {
        super.onBaseContextAttached(base)
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base)

        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this)
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    fun registerActivityLifecycleCallback(callbacks: ActivityLifecycleCallbacks?) {
        application.registerActivityLifecycleCallbacks(callbacks)
    }

}