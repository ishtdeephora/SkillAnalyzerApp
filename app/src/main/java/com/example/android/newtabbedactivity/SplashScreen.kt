package com.example.android.newtabbedactivity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler

class SplashScreen : Activity() {
    companion object {
        private val SPLASH_TIME_OUT = 1500
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }


}
