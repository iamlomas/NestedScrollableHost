package com.iamlomas.nestedscrollablehost.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.iamlomas.nestedscrollablehost_sample.DataSet
import com.iamlomas.nestedscrollablehost_sample.TabViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        tabLayout = findViewById(R.id.tabLayout)
        viewPager2 = findViewById(R.id.mainViewPager)

        viewPager2.adapter = TabViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = DataSet.tabList[position]
        }.attach()
    }

    override fun onBackPressed() {
        if (viewPager2.currentItem == DataSet.FIRST_TAB) {
            super.onBackPressed()
        } else {
            viewPager2.currentItem = DataSet.FIRST_TAB
        }
    }
}