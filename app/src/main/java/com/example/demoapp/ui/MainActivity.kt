package com.example.demoapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.demoapp.R
import com.example.demoapp.databinding.ActivityMainBinding
import com.example.demoapp.ui.fragments.BlankFragment
import com.example.demoapp.ui.fragments.HomeFragment
import com.example.demoapp.ui.viewPager.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(LayoutInflater.from(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setViewPager()
    }

    private fun setViewPager() {

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFrag(HomeFragment(), "HOME")
        adapter.addFrag(BlankFragment(), "BOOKMARK")
        adapter.addFrag(BlankFragment(), "TV")
        adapter.addFrag(BlankFragment(), "NOTIFICATION")
        adapter.addFrag(BlankFragment(), "USER")
        binding.viewPager.adapter = adapter

        binding.tabBottom.setupWithViewPager(binding.viewPager)

        setupTabIcons()

    }
    private fun setupTabIcons() {
        binding.tabBottom.getTabAt(0)?.customView = createTabView(R.drawable.ic_selected_feeds)
        binding.tabBottom.getTabAt(1)?.customView = createTabView(R.drawable.ic_unselected_bookmark)
        binding.tabBottom.getTabAt(2)?.customView = createTabView(R.drawable.ic_unselected_tv)
        binding.tabBottom.getTabAt(3)?.customView = createTabView(R.drawable.ic_unselected_notification)
        binding.tabBottom.getTabAt(4)?.customView = createTabView(R.drawable.ic_unselected_user)

        binding.tabBottom.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when(tab.position) {
                    0 -> binding.tabBottom.getTabAt(0)?.customView = createTabView(R.drawable.ic_selected_feeds)
                    1 -> binding.tabBottom.getTabAt(1)?.customView = createTabView(R.drawable.ic_selected_bookmark)
                    2 -> binding.tabBottom.getTabAt(2)?.customView = createTabView(R.drawable.ic_selected_tv)
                    3 -> binding.tabBottom.getTabAt(3)?.customView = createTabView(R.drawable.ic_selected_notification)
                    4 -> binding.tabBottom.getTabAt(4)?.customView = createTabView(R.drawable.ic_selected_user)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                when(tab.position) {
                    0 -> binding.tabBottom.getTabAt(0)?.customView = createTabView(R.drawable.ic_unselected_feeds)
                    1 -> binding.tabBottom.getTabAt(1)?.customView = createTabView(R.drawable.ic_unselected_bookmark)
                    2 -> binding.tabBottom.getTabAt(2)?.customView = createTabView(R.drawable.ic_unselected_tv)
                    3 -> binding.tabBottom.getTabAt(3)?.customView = createTabView(R.drawable.ic_unselected_notification)
                    4 -> binding.tabBottom.getTabAt(4)?.customView = createTabView(R.drawable.ic_unselected_user)
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab) {}

        })
    }

    private fun createTabView(iconResId: Int): ImageView {
        val tabView = layoutInflater.inflate(R.layout.custom_tab, null) as ImageView
        val tabIcon = tabView.findViewById<ImageView>(R.id.tab_icon)
        tabIcon.setImageResource(iconResId)
        return tabIcon
    }
}