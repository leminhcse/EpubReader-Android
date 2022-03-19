package com.folioreader.android.epubreader.ui

import android.os.Bundle
import com.folioreader.android.epubreader.R
import com.folioreader.android.epubreader.base.activity.BaseActivity

class MainActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}