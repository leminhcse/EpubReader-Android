package com.folioreader.android.epubreader.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.folioreader.android.epubreader.R
import com.folioreader.android.epubreader.base.activity.BaseActivity
import com.folioreader.android.epubreader.example.ReadExampleActivity
import com.folioreader.android.epubreader.ui.MainActivity

class SplashActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}