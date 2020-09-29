package com.drak.course.login

import android.content.Intent
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.drak.base.BaseActivity
import com.drak.course.MainActivity
import com.drak.course.R
import com.gyf.immersionbar.ImmersionBar
import kotlinx.android.synthetic.main.login_activity_splash.*

class SplashActivity : BaseActivity() {

    private var alphaAnimation: AlphaAnimation? = null

    override fun attachLayoutRes(): Int = R.layout.login_activity_splash

    override fun useEventBus(): Boolean = false

    override fun enableNetworkTip(): Boolean = false

    override fun initData() {
        ImmersionBar.with(this).fullScreen(true).init()
    }

    override fun initView() {
        alphaAnimation = AlphaAnimation(0.3F, 1.0F)
        alphaAnimation?.run {
            duration = 3000
            setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {
                }

                override fun onAnimationEnd(p0: Animation?) {
                    jumpToMain()
                }

                override fun onAnimationStart(p0: Animation?) {
                }
            })
        }
        layout_splash.startAnimation(alphaAnimation)
    }

    override fun start() {
    }

    fun jumpToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

}
