package com.folioreader.android.epubreader.utils

import android.content.Context
import android.util.Log
import com.folioreader.Config
import com.folioreader.FolioReader
import com.folioreader.android.epubreader.R
import com.folioreader.model.HighLight
import com.folioreader.model.locators.ReadLocator
import com.folioreader.util.AppUtil
import com.folioreader.util.OnHighlightListener
import com.folioreader.util.ReadLocatorListener
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object FolioReaderUtil: FolioReader.OnClosedListener, ReadLocatorListener, OnHighlightListener {

    private var folioReader: FolioReader? = null
    private var context: Context? = null

    init {
        println("Folio Reader class invoked.")
    }

    fun setupFolioReader(context: Context) {
        this.context = context
        folioReader = FolioReader.get()
            .setOnHighlightListener(this)
            .setReadLocatorListener(this)
            .setOnClosedListener(this)
    }

    fun openBookByRaw() {
        var config: Config? = AppUtil.getSavedConfig(context)
        if(config == null) {
            config = Config()
        }
        config.setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL)

        folioReader?.setConfig(config, true)?.openBook(R.raw.accessible_epub_3)
    }

    fun openBookByAssest() {
        val readLocator: ReadLocator? = getLastReadLocator()

        var config: Config? = AppUtil.getSavedConfig(context)
        if(config == null) {
            config = Config()
        }
        config.setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL)

        folioReader?.setReadLocator(readLocator)
        folioReader?.setConfig(config, true)
            ?.openBook("file:///android_asset/TheSilverChair.epub")
    }

    private fun getLastReadLocator(): ReadLocator? {
        val jsonString = loadAssetTextAsString("Locators/LastReadLocators/last_read_locator_1.json")
        return ReadLocator.fromJson(jsonString)
    }

    private fun loadAssetTextAsString(name: String): String? {
        var `in`: BufferedReader? = null
        try {
            val buf = StringBuilder()
            val `is` = context?.assets?.open(name)
            `in` = BufferedReader(InputStreamReader(`is`))
            var str: String?
            var isFirst = true
            while (`in`.readLine().also { str = it } != null) {
                if (isFirst) isFirst = false else buf.append('\n')
                buf.append(str)
            }
            return buf.toString()
        } catch (e: IOException) {
            Log.e("HomeActivity", "Error opening asset $name")
        } finally {
            if (`in` != null) {
                try {
                    `in`.close()
                } catch (e: IOException) {
                    Log.e("HomeActivity", "Error closing asset $name")
                }
            }
        }
        return null
    }

    override fun onFolioReaderClosed() {
        TODO("Not yet implemented")
    }

    override fun onHighlight(highlight: HighLight?, type: HighLight.HighLightAction?) {
        TODO("Not yet implemented")
    }

    override fun saveReadLocator(readLocator: ReadLocator?) {
        TODO("Not yet implemented")
    }
}