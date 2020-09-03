package com.example.zadshare

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import java.security.AccessControlContext

class Main : AppCompatActivity() {
    private lateinit var tabs:TabLayout
    private lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        tabs= findViewById(R.id.tabs) as TabLayout
        viewPager= findViewById(R.id.view_pager)as ViewPager
        tabs.setupWithViewPager(viewPager)
        tabs.addOnTabSelectedListener(TabSeleListener(this))
    }
    private class TabSeleListener(val context: Context):TabLayout.OnTabSelectedListener{
        override fun onTabReselected(p0: TabLayout.Tab?) {}

        override fun onTabUnselected(p0: TabLayout.Tab?) {
            (p0?.icon)?.setColorFilter(context.resources.getColor(R.color.tabs_text_color), PorterDuff.Mode.SRC_IN)
        }

        override fun onTabSelected(p0: TabLayout.Tab?) {
            (p0?.icon)?.setColorFilter(context.resources.getColor(R.color.green), PorterDuff.Mode.SRC_IN)
        }

    }
}
