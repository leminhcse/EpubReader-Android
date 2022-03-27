package com.folioreader.android.epubreader.ui.home.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.folioreader.android.epubreader.ui.classic.ClassicFragment
import com.folioreader.android.epubreader.ui.culture.CultureFragment
import com.folioreader.android.epubreader.ui.economic.EconomicFragment
import com.folioreader.android.epubreader.ui.history.HistoryFragment
import com.folioreader.android.epubreader.ui.selfhelp.SelfHelpFragment

class HomePagerAdapter(val activity: AppCompatActivity, val itemsCount: Int):
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                SelfHelpFragment()
            }
            1 -> {
                CultureFragment()
            }
            2 -> {
                EconomicFragment()
            }
            3 -> {
                HistoryFragment()
            }
            4 -> {
                ClassicFragment()
            }
            else -> createFragment(position)
        }
    }
}