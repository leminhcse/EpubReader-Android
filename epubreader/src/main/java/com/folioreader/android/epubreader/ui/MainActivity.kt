package com.folioreader.android.epubreader.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.folioreader.android.epubreader.R
import com.folioreader.android.epubreader.base.activity.BaseActivity
import com.folioreader.android.epubreader.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import timber.log.Timber

class MainActivity: BaseActivity(), NavigationBarView.OnItemSelectedListener, View.OnClickListener,
    NavigationView.OnNavigationItemSelectedListener {

    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    private lateinit var toggle: ActionBarDrawerToggle

    private var currentPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initView()
    }

    private fun initView() {
        // Init actionBar drawer toggle
        toggle = ActionBarDrawerToggle(this, binding.drawerLayout,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Set on click item listener for menu/notification button on action bar
        binding.header.menuImageButton.setOnClickListener(this)
        binding.header.notifyImageButton.setOnClickListener(this)

        // Set on navigation item selected listener for navigation view
        binding.navView.setNavigationItemSelectedListener(this)
        binding.navView.setCheckedItem(R.id.nav_home)

        // Set on selected item listener for navigation bottom bar
        binding.navigationBar.setOnItemSelectedListener(this)

        // Init nav_view controller for home screen
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
                binding.drawerLayout.openDrawer(GravityCompat.START)
            }
            R.id.notifyImageButton -> {
                Timber.e("You just click to notify button")
            }
        }
    }
}