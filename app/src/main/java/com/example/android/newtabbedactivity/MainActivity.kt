package com.example.android.newtabbedactivity

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RatingBar
import com.nhaarman.supertooltips.ToolTip
import com.nhaarman.supertooltips.ToolTipRelativeLayout
import com.nhaarman.supertooltips.ToolTipView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * [FragmentPagerAdapter] derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    /**
     * The [ViewPager] that will host the section contents.
     */

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container!!.adapter = mSectionsPagerAdapter
        container!!.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))

        val position = container!!.currentItem
        if (container!!.currentItem != position) {
            container!!.currentItem = position
        }

        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))

        fab.setOnClickListener {
         val newIntent = Intent(this@MainActivity, ResultpdActivity::class.java)
            startActivity(newIntent)
        }

        val toolTipRelativeLayout = findViewById<View>(R.id.activity_main_tooltipRelativeLayout) as ToolTipRelativeLayout
        val toolTip = ToolTip()
                .withText("Check result")
                .withColor(Color.BLUE)

                .withoutShadow()
                .withTextColor(Color.WHITE)
                .withAnimationType(ToolTip.AnimationType.FROM_TOP)

        val myToolTipView = toolTipRelativeLayout.showToolTipForView(toolTip, findViewById(R.id.fab))
        myToolTipView!!.bringToFront()
        myToolTipView.setOnToolTipViewClickedListener(object : ToolTipView.OnToolTipViewClickedListener {
            override fun onToolTipViewClicked(toolTipView: ToolTipView) {
                myToolTipView.setOnToolTipViewClickedListener(this)
                myToolTipView.remove()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        when (id) {
            R.id.action_settings -> {

            }


        }
        return super.onOptionsItemSelected(item)
    }

    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return when (position) {
                0 -> {
                    Tab1Known()
                }

                1 -> {
                    Tab2Should()
                }

                2 -> {
                    Tab3May()
                }

                else -> null
            }

        }

        override fun getCount(): Int {
            return 3
        }
    }

}
