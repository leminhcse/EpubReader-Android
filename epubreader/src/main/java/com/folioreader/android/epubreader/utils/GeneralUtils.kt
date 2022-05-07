package com.folioreader.android.epubreader.utils

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources

object GeneralUtils {

    const val PORTRAIT = Configuration.ORIENTATION_PORTRAIT

    val screenWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels

    val screenHeight: Int
        get() = Resources.getSystem().displayMetrics.heightPixels

    fun getOrientation(context: Context): Int {
        return context.resources.configuration.orientation
    }

}