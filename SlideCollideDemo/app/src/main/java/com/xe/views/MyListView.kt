package com.xe.views

import android.content.Context
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.ListView
import com.xe.slidecollidedemo.MainActivity

/**
 * Created by 86188 on 2021/9/10.
 */
class MyListView: ListView {

    var lastX: Int = 0
    var lastY: Int = 0

    constructor(context: Context): super(context) {

    }
    constructor(context: Context, @Nullable attrs: AttributeSet): super(context,attrs) {

    }
    constructor(context: Context, @Nullable attrs: AttributeSet,defStyleAttr: Int): super(context,attrs,defStyleAttr) {

    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (MyViewPager.flag == 3) {
            internalIntercept(ev)
        }
        return super.dispatchTouchEvent(ev)
    }

    fun internalIntercept(ev: MotionEvent) {
        Log.d(MainActivity.TAG, "--internalIntercept--")
        val x = ev.x.toInt()
        val y = ev.y.toInt()
        val action = ev.action and MotionEvent.ACTION_MASK
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = x - lastX
                val deltaY = y - lastY
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    parent.requestDisallowInterceptTouchEvent(false)
                }
            }
        }
        lastX = x
        lastY = y
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val b = super.onTouchEvent(ev)
        var s = "s"
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> s = "--MyListView--onTouchEvent--MotionEvent.ACTION_DOWN--$b"
            MotionEvent.ACTION_MOVE -> s = "--MyListView--onTouchEvent--MotionEvent.ACTION_MOVE--$b"
            MotionEvent.ACTION_UP -> s = "--MyListView--onTouchEvent--MotionEvent.ACTION_UP--$b"
        }
        Log.d(MainActivity.TAG, s)
        return b
    }
}