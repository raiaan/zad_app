package com.example.zadshare

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import android.widget.TextView
import android.text.Html
import android.util.Log

class Welcome : AppCompatActivity(),ViewPager.OnPageChangeListener {

    private lateinit var prefManager:PrefManager
    private lateinit var viewPager:ViewPager
    private lateinit var dotsLayout:LinearLayout
    private lateinit var layouts:ArrayList<Int>
    private lateinit var dots: ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prefManager= PrefManager(this, Context.MODE_PRIVATE)
        if (! prefManager.isFirstTimeLaunch()) {
            launchHomeScreen();
            finish();
        }else{
            setContentView(R.layout.welcome)
            viewPager= findViewById(R.id.view_pager) as ViewPager
            dotsLayout= findViewById(R.id.layoutDots)as LinearLayout
            layouts= arrayListOf(R.layout.welcom_s1,
                R.layout.welcome_s2,
                R.layout.welcome_s3,
                R.layout.welcome_s4)
            viewPager.adapter=ViewPagerAdapter(this,layouts)
            addBottomDots(0)
            viewPager.addOnPageChangeListener(this)
        }

    }
    private fun launchHomeScreen() {
        prefManager.setFirstTimeLaunch(false)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    private fun addBottomDots(currPage:Int){
        dots = ArrayList<TextView>()
        dotsLayout.removeAllViews();
        for (i in layouts.indices) {
            dots.add(TextView(this))
            dots[i].text = Html.fromHtml("&#8226")
            dots[i].textSize=48F
            dots[i].setTextColor(resources.getColor(R.color.inactive_dots))
            dotsLayout.addView(dots[i],i)
        }
        if (dots.size > 0)
            dots[currPage].setTextColor(resources.getColor(R.color.white));
    }
    // page viewer page change listener methods
    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        if (position>= layouts.size -1)
            launchHomeScreen()
    }

    override fun onPageSelected(position: Int) {
        addBottomDots(position)
    }
    private class ViewPagerAdapter(val context:Context,val layouts:ArrayList<Int>): PagerAdapter() {
        val inflater:LayoutInflater
        init{
            inflater = LayoutInflater.from(context)
        }
        override fun isViewFromObject(view: View, `object`: Any): Boolean {
             return view==`object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val view:View = inflater.inflate(layouts[position], container, false);
            container.addView(view);
            return view;
        }

        override fun getCount(): Int {
             return layouts.size
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            container.removeView(`object` as View)
        }
    }
}
