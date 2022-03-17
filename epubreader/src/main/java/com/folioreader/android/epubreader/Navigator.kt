package com.folioreader.android.epubreader

import android.app.Activity
import android.content.Intent
import com.folioreader.android.epubreader.ui.MainActivity

fun Activity.openMainActivity() {
    val intent = Intent(this, MainActivity::class.java)
    this.startActivity(intent)
    this.finish()
}