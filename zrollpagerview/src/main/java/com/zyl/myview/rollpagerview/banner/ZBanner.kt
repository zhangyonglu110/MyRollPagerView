package com.zyl.myview.rollpagerview.banner

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.zyl.myview.rollpagerview.adapter.ZPagerAdapter
import com.zyl.myview.rollpagerview.util.DensityUtil

/**
 * Created by zhangyonglu on 2017/11/14 001417:18
 */

class ZBanner : FrameLayout {
    private var mcontext: Context
    internal var vp: ViewPager?=null
    internal var monBannerItemClick: OnBannerItemClick?=null
    private var dotcontiner: LinearLayout? = null
    private var mzpageadapter: ZPagerAdapter? = null

    constructor(context: Context) : super(context) {
        this.mcontext = context
        init()
    }


    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.mcontext = context

        init()

    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.mcontext = context

        init()

    }

    private fun init() {
        vp = ViewPager(mcontext)
        val layoutparams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        vp!!.layoutParams = layoutparams
        addView(vp)

        dotcontiner = LinearLayout(mcontext)
        val dotlayoutparams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        dotlayoutparams.setMargins(0, 0, 0, DensityUtil.dip2px(mcontext,5f))
        dotlayoutparams.gravity = Gravity.BOTTOM
        dotcontiner!!.gravity = Gravity.CENTER
        dotcontiner!!.orientation = LinearLayout.HORIZONTAL
        dotcontiner!!.layoutParams = dotlayoutparams
        addView(dotcontiner)

    }

    fun setadapter(adapter: ZPagerAdapter) {
        vp!!.adapter = adapter
        adapter.setpagerlistener(vp!!)
        adapter.setdotview(this)
        this.mzpageadapter = adapter

    }

    interface OnBannerItemClick {
        fun OnItemClick(position: Int)
    }

    fun setbanneritemclick(onbannerclick: OnBannerItemClick) {
        this.monBannerItemClick = onbannerclick
        if (this.mzpageadapter != null) mzpageadapter!!.setbanneritemclick(onbannerclick)

    }

    fun adddotview(dotview: ZDotView) {
        dotcontiner!!.addView(dotview)
    }

    fun setautoplay(b: Boolean) {
        if (mzpageadapter != null) mzpageadapter!!.setisautoplay(b)
    }
}
