package com.xe.slidecollidedemo

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import com.xe.views.MyListView
import com.xe.views.MyViewPager

/**
 * Created by 86188 on 2021/9/9.
 */
class MainActivity: AppCompatActivity() {

    companion object {
        var TAG: String = "MainActivity";
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(v: View?) {
        when(v!!.id) {
            R.id.btn_1 -> {
                startSlideCollideActivity(1)
            }
            R.id.btn_2 -> {
                startSlideCollideActivity(2)
            }
            R.id.btn_3 -> {
                startSlideCollideActivity(3)
            }
        }
    }

    fun startSlideCollideActivity(flag: Int) {
        MyViewPager.flag = flag
        var intent: Intent = Intent(this,SlideCollideActivity::class.java)
        startActivity(intent)
    }
}