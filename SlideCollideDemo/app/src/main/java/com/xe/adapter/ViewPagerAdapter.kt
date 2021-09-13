package com.xe.adapter

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * Created by 86188 on 2021/9/11.
 */
class ViewPagerAdapter: PagerAdapter {
    val views: List<View>?
    constructor(list: List<View>){
        this.views = list
    }

    override fun getCount(): Int {
        return views!!.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = views!!.get(position)
        container.addView(view)
        return view
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view === obj
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}