package com.zyl.myview.rollpagerview.adapter

import android.content.Context
import android.os.CountDownTimer
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zyl.myview.rollpagerview.banner.ZBanner
import com.zyl.myview.rollpagerview.banner.ZDotView
import com.zyl.myview.rollpagerview.util.DensityUtil
import java.util.*


/**
 * Created by zhangyonglu on 2017/11/15 001508:13
 */

class ZPagerAdapter : PagerAdapter {
    private var mcontext: Context
    private var mresids: IntArray?=null
    private var murllist: List<String>? = null
    private val imageViewList = ArrayList<ImageView>()
    private val zDotViewList = ArrayList<ZDotView>()
    private var nextpage: Int = 0
    private var mcount: Int = 0
    private var normalcolor: Int = 0
    private var selectedcolor: Int = 0
    private var mvp: ViewPager? = null
    private var mycountdowntimer: MyCountDownTimer? = null
    internal var monbanneritemclick: ZBanner.OnBannerItemClick?=null

    constructor(context: Context, resids: IntArray) {
        this.mcontext = context
        this.mresids =resids
        mycountdowntimer = MyCountDownTimer(3000, 1000)
        initview()

    }

    private fun initview() {

        if (mresids != null) {
            mcount = mresids!!.size
        }


        if (murllist != null) {
            mcount = murllist!!.size
        }


        for (i in 0..mcount - 1) {
            val zDotview = ZDotView(mcontext!!.applicationContext)
            val layoutParams = LinearLayout.LayoutParams(DensityUtil.dip2px(mcontext, 6f), DensityUtil.dip2px(mcontext, 6f))
            layoutParams.setMargins(DensityUtil.dip2px(mcontext, 5f), 0, 0, 0)
            zDotview.layoutParams = layoutParams
            zDotViewList.add(zDotview)
        }
    }

    constructor(context: Context, urllist: Array<String>) {
        this.mcontext = context
        this.murllist = Arrays.asList(*urllist)
        mycountdowntimer = MyCountDownTimer(3000, 1000)
        initview()

    }

    constructor(context: Context, ulist: List<String>) {
        this.mcontext = context
        this.murllist = ulist
        mycountdowntimer = MyCountDownTimer(3000, 1000)
        initview()


    }

    override fun getCount(): Int {
        if (mresids != null) {
            return mresids!!.size
        } else if (murllist != null) {
            return murllist!!.size

        }
        return 0
    }


    override fun isViewFromObject(arg0: View, arg1: Any): Boolean {
        return arg0 === arg1
    }

    override fun getItemPosition(`object`: Any?): Int {
        return super.getItemPosition(`object`)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

        container.removeView(imageViewList[position])

    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val scaleView = ImageView(mcontext!!.applicationContext)
        scaleView.scaleType = ImageView.ScaleType.FIT_XY
        imageViewList.add(scaleView)
        container.addView(scaleView)
        /**
         * set local id
         */
        if (mresids != null) {
            scaleView.setImageResource(mresids!![position])
            /**
             * set net img url
             */
        }
        if (murllist != null) {
            Glide.with(mcontext)
                    .load(murllist!![position])
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .into(scaleView)
        }
        /**
         * set click listner
         */
        scaleView.setOnClickListener {
          if(monbanneritemclick!=null)  monbanneritemclick!!.OnItemClick(position)
        }
        return scaleView
    }

    fun setbanneritemclick(onbanneritemclick: ZBanner.OnBannerItemClick) {
        monbanneritemclick = onbanneritemclick
    }

    fun setdotview(zbanner: ZBanner) {
        for (i in zDotViewList.indices) {
            zbanner.adddotview(zDotViewList[i])
        }
    }

    fun setpagerlistener(vp: ViewPager) {
        this.mvp = vp
        vp.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                setcolor(position)
                nextpage = position + 1

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
    }

    private fun setcolor(position: Int) {
        for (i in zDotViewList.indices) {
            if (position == i) {
                if(normalcolor!=0){
                    zDotViewList[position].setCircleColor(selectedcolor)

                }else {
                    zDotViewList[position].setCircleColor(android.R.color.white)
                }
            } else {
                if(selectedcolor!=0) {
                    zDotViewList[i].setCircleColor(normalcolor)
                }else{
                    zDotViewList[i].setCircleColor(android.R.color.darker_gray)

                }

            }
        }
    }

    internal inner class MyCountDownTimer(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

        //计时过程
        override fun onTick(l: Long) {
            //防止计时过程中重复点击

        }

        //计时完毕的方法
        override fun onFinish() {
            //  ZToastUtils.showShort(getActivity(),"start get data----------");
            if (nextpage > mcount - 1) nextpage = 0
            mvp!!.currentItem = nextpage
            // page++;
            mycountdowntimer!!.start()
        }
    }

    fun setisautoplay(b: Boolean) {
        if (b) {
            mycountdowntimer!!.start()
        } else {
            mycountdowntimer!!.onFinish()
        }
    }

    fun  setdotsize(dotsize:Int){
     for(dotview in zDotViewList){
         val layoutParams = LinearLayout.LayoutParams(dotsize, dotsize)
         layoutParams.setMargins(DensityUtil.dip2px(mcontext, 5f), 0, 0, 0)
         dotview.layoutParams = layoutParams
     }
    }

    fun setdoNormalColor(color:Int){
       this.normalcolor=color

    }
    fun setdotSelectedColor(color:Int){
        this.selectedcolor=color

    }
}
