package com.iamlomas.nestedscrollablehost_sample

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.iamlomas.nestedscrollablehost.example.FirstTabFragment

class TabViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return DataSet.NUMBER_OF_TABS
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            DataSet.FIRST_TAB -> FirstTabFragment()
            else -> SecondTabFragment()
        }
    }
}