package com.folioreader.android.epubreader.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.folioreader.android.epubreader.R
import com.folioreader.android.epubreader.base.activity.BaseActivity
import com.folioreader.android.epubreader.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import timber.log.Timber

class MainActivity: BaseActivity(), NavigationBarView.OnItemSelectedListener, View.OnClickListener {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding

    private var currentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
    }

    private fun initView() {
        binding.navigationBar.setOnItemSelectedListener(this)

        binding.header.menuImageButton.setOnClickListener(this)
        binding.header.notifyImageButton.setOnClickListener(this)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHomeContainer) as NavHostFragment
        navController = navHostFragment.navController
        navController.graph = navController.navInflater.inflate(R.navigation.home_nav)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.homeFragment -> {
                if (currentPosition != 0) {
                    currentPosition = 0
                    navController.navigate(R.id.homeFragment, null)
                }
            }
            R.id.searchFragment -> {
                if (currentPosition != 1) {
                    currentPosition = 1
                    navController.navigate(R.id.searchFragment, null)
                }
            }
            R.id.favoritesFragment -> {
                if (currentPosition != 2) {
                    currentPosition = 2
                    navController.navigate(R.id.favoritesFragment, null)
                }
            }
            R.id.readingFragment -> {
                if (currentPosition != 3) {
                    currentPosition = 3
                    navController.navigate(R.id.readingFragment, null)
                }
            }
        }
        return true
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.menuImageButton -> {
                Timber.e("You just click to menu button")
            }
            R.id.notifyImageButton -> {
                Timber.e("You just click to notify button")
            }
        }
    }
}