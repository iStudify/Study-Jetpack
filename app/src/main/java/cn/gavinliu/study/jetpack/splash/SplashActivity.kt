package cn.gavinliu.study.jetpack.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import cn.gavinliu.study.jetpack.MainActivity
import cn.gavinliu.study.jetpack.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isFinishing) {
            startActivity(Intent(this, MainActivity::class.java))
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            finish()
        }
    }

}