package com.folioreader.android.epubreader.example

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.folioreader.Config
import com.folioreader.FolioReader
import com.folioreader.FolioReader.OnClosedListener
import com.folioreader.FolioReader.get
import com.folioreader.android.epubreader.R
import com.folioreader.android.epubreader.base.activity.BaseActivity
import com.folioreader.model.HighLight
import com.folioreader.model.locators.ReadLocator
import com.folioreader.model.locators.ReadLocator.Companion.fromJson
import com.folioreader.util.AppUtil
import com.folioreader.util.OnHighlightListener
import com.folioreader.util.ReadLocatorListener
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

open class ReadExampleActivity: BaseActivity(), OnHighlightListener, ReadLocatorListener,
    OnClosedListener, View.OnClickListener {

    private var folioReader: FolioReader? = null

    private lateinit var btnRaw: Button
    private lateinit var btnAsset: Button

    private val LOG_TAG = ReadExampleActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_example)

        setupControls()
        setupFolioReader()
    }

    override fun onFolioReaderClosed() {
        Log.v(LOG_TAG, "-> onFolioReaderClosed")
    }

    override fun onHighlight(highlight: HighLight?, type: HighLight.HighLightAction?) {
        TODO("Not yet implemented")
    }

    override fun saveReadLocator(readLocator: ReadLocator?) {
        Log.i(LOG_TAG, "-> saveReadLocator -> " + readLocator!!.toJson())
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when(v.id) {
                R.id.btn_raw -> {
                    var config: Config? = AppUtil.getSavedConfig(applicationContext)
                    if(config == null) {
                        config = Config()
                    }
                    config.setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL)

                    folioReader?.setConfig(config, true)?.openBook(R.raw.accessible_epub_3)
                }
                R.id.btn_assest -> {
                    val readLocator: ReadLocator? = getLastReadLocator()

                    var config: Config? = AppUtil.getSavedConfig(applicationContext)
                    if(config == null) {
                        config = Config()
                    }
                    config.setAllowedDirection(Config.AllowedDirection.VERTICAL_AND_HORIZONTAL)

                    folioReader?.setReadLocator(readLocator)
                    folioReader?.setConfig(config, true)?.openBook("file:///android_asset/TheSilverChair.epub")
                }
            }
        }
    }

    private fun setupControls() {
        btnRaw = findViewById(R.id.btn_raw)
        btnAsset = findViewById(R.id.btn_assest)

        btnRaw.setOnClickListener(this)
        btnAsset.setOnClickListener(this)
    }

    private fun setupFolioReader() {
        folioReader = get()
            .setOnHighlightListener(this)
            .setReadLocatorListener(this)
            .setOnClosedListener(this)
    }

    private fun getLastReadLocator(): ReadLocator? {
        val jsonString = loadAssetTextAsString("Locators/LastReadLocators/last_read_locator_1.json")
        return fromJson(jsonString)
    }

    private fun loadAssetTextAsString(name: String): String? {
        var `in`: BufferedReader? = null
        try {
            val buf = StringBuilder()
            val `is` = assets.open(name)
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
}