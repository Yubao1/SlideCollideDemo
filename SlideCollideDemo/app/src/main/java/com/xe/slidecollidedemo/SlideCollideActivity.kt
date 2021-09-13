package com.xe.slidecollidedemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.ArrayAdapter
import com.xe.adapter.ViewPagerAdapter
import com.xe.views.MyListView

class SlideCollideActivity : AppCompatActivity() {
    var viewPager: ViewPager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slide_collide)
        viewPager = findViewById(R.id.viewPager)
        var viewList = java.util.ArrayList<View>()
        for (i in 0..3) {
            val listView = MyListView(this)
            val dataList = java.util.ArrayList<String>()
            for (i in 0..29) {
                dataList.add("数据 $i")
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
            listView.setAdapter(adapter)
            viewList!!.add(listView)
        }
        viewPager!!.setAdapter(ViewPagerAdapter(viewList))
    }
}
