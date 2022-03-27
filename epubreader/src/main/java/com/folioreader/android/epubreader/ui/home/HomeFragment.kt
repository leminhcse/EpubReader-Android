package com.folioreader.android.epubreader.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.folioreader.android.epubreader.R
import com.folioreader.android.epubreader.base.BaseFragment
import com.folioreader.android.epubreader.databinding.FragmentHomeBinding
import com.folioreader.android.epubreader.extensions.viewBinding
import com.folioreader.android.epubreader.ui.home.adapter.HomePagerAdapter
import com.google.android.material.tabs.TabLayout

class HomeFragment: BaseFragment(), TabLayout.OnTabSelectedListener {

    private val binding by viewBinding(FragmentHomeBinding::bind)
    override val layoutId: Int get() = R.layout.fragment_home

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewPager()
    }

    private fun initViewPager() {
        binding.tabLayout.addOnTabSelectedListener(this)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    // you are on the first page
                }
                else if (position == 1) {
                    // you are on the second page
                }
                else if (position == 2){
                    // you are on the third page
                }
                else if (position == 3){
                    // you are on the third page
                }
                super.onPageSelected(position)
            }
        })

        binding.apply {
            tabLayout.addTab(tabLayout.newTab().setText(context?.getString(R.string.selfhelp)))
            tabLayout.addTab(tabLayout.newTab().setText(context?.getString(R.string.culture)))
            tabLayout.addTab(tabLayout.newTab().setText(context?.getString(R.string.economic)))
            tabLayout.addTab(tabLayout.newTab().setText(context?.getString(R.string.history)))
            tabLayout.addTab(tabLayout.newTab().setText(context?.getString(R.string.classic)))
        }

        val adapter = HomePagerAdapter(activity as AppCompatActivity, binding.tabLayout.tabCount)
        binding.viewPager.adapter = adapter
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab != null) {
            binding.viewPager.currentItem = tab.position
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}
}