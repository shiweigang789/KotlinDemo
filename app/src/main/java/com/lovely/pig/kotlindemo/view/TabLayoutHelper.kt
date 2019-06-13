package com.lovely.pig.kotlindemo.view

import android.support.design.widget.TabLayout
import android.widget.LinearLayout
import com.lovely.pig.kotlindemo.utils.DisplayManager
import java.lang.reflect.Field

/**
 * 作者 swg
 * 时间 2019/6/13 16:35
 * 文件 KotlinDemo
 * 描述
 */
object TabLayoutHelper {

    fun setUpIndicatorWidth(tabLayout: TabLayout) {
        val tabLayoutClass = tabLayout.javaClass
        var tabStrip: Field? = null
        try {
            tabStrip = tabLayoutClass.getDeclaredField("mTabStrip")
            tabStrip!!.isAccessible = true
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
        }

        var layout: LinearLayout? = null
        try {
            if (tabStrip != null) {
                layout = tabStrip.get(tabLayout) as LinearLayout
            }
            if (layout == null) return
            for (i in 0 until layout.childCount) {
                val child = layout.getChildAt(i)
                child.setPadding(0, 0, 0, 0)
                val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1f)
                params.marginStart = DisplayManager.dip2px(50f)!!
                params.marginEnd = DisplayManager.dip2px(50f)!!
                child.layoutParams = params
                child.invalidate()
            }
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        }
    }

}