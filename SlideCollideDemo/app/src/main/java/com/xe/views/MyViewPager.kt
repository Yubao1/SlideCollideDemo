package com.xe.views

import android.content.Context
import android.support.annotation.Nullable
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import com.xe.slidecollidedemo.MainActivity

/**
 * Created by 86188 on 2021/9/9.
 */
class MyViewPager: ViewPager {
    companion object {

        /**
         * 1、表示制造一个滑动冲突
         * 2、表示用外部拦截法解决滑动冲突
         * 3、表示用内部拦截法解决滑动冲突
         */
        var flag: Int = 0;
    }
    var lastXIntercept: Int = 0
    var lastYIntercept: Int = 0
    constructor(context: Context): super(context) {
    }
    constructor(context: Context,@Nullable attrs: AttributeSet): super(context,attrs) {
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (flag == 1) {
            return forbidInterceptTouchEvent(ev);
        } else if (flag == 2) {
            return externalIntercept(ev)
        } else if (flag == 3) {
            return internalIntercept(ev)
        }
        return super.onInterceptTouchEvent(ev)
    }

    fun internalIntercept(ev: MotionEvent?): Boolean {
        val action = ev!!.getAction() and MotionEvent.ACTION_MASK
        var intercepted: Boolean = true;
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                intercepted = false
                super.onInterceptTouchEvent(ev)
                Log.d(MainActivity.TAG,"--MyViewPager--internalIntercept--MotionEvent.ACTION_DOWN")
            }
            MotionEvent.ACTION_MOVE -> {
                intercepted = true
                Log.d(MainActivity.TAG,"--MyViewPager--internalIntercept--MotionEvent.ACTION_MOVE")
            }
            MotionEvent.ACTION_UP -> {
                intercepted = false
                Log.d(MainActivity.TAG,"--MyViewPager--internalIntercept--MotionEvent.ACTION_UP")
            }
        }
        return intercepted
    }

    fun externalIntercept(ev: MotionEvent?): Boolean {
        var intercepted = false
        val x = ev!!.getX().toInt()
        val y = ev!!.getY().toInt()
        val action = ev.getAction() and MotionEvent.ACTION_MASK
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                intercepted = false

                //调用 ViewPager的 onInterceptTouchEvent 方法用于初始化 mActivePointerId
                super.onInterceptTouchEvent(ev)
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = x - lastXIntercept
                val deltaY = y - lastYIntercept
                intercepted = Math.abs(deltaX) > Math.abs(deltaY)
            }
            MotionEvent.ACTION_UP -> {
                intercepted = false
            }
        }
        lastXIntercept = x
        lastYIntercept = y
        return intercepted
    }

    fun forbidInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.d(MainActivity.TAG,"--forbidInterceptTouchEvent--")
        return false
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val b = super.onTouchEvent(ev)
        var s = "s"
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> s = "--MyViewPager--onTouchEvent--MotionEvent.ACTION_DOWN--$b"
            MotionEvent.ACTION_MOVE -> s = "--MyViewPager--onTouchEvent--MotionEvent.ACTION_MOVE--$b"
            MotionEvent.ACTION_UP -> s = "--MyViewPager--onTouchEvent--MotionEvent.ACTION_UP--$b"
        }
        Log.d(MainActivity.TAG, s)
        return b
    }

}