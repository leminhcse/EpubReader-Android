package com.folioreader.android.epubreader.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.folioreader.android.epubreader.R
import com.folioreader.android.epubreader.base.activity.BaseActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity: BaseActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var navController: NavController

    private lateinit var bottomNav: BottomNavigationView

    private var currentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        bottomNav = findViewById(R.id.navigationBar)
        bottomNav.setOnItemSelectedListener(this)

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
}