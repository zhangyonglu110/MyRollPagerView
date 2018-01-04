package com.zyl.rollpager.main

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.zyl.myview.rollpagerview.adapter.ZPagerAdapter
import com.zyl.myview.rollpagerview.banner.ZBanner
import com.zyl.myview.rollpagerview.banner.ZDotView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : Activity() {
   var idlists:Array<Int>?=null
    var zpagerAdapter:ZPagerAdapter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        idlists= arrayOf(R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.mipmap.ic_launcher)
        zpagerAdapter=ZPagerAdapter(this,idlists!!)
        zpagerAdapter!!.setdoNormalColor(android.R.color.darker_gray)
        zpagerAdapter!!.setdotSelectedColor(android.R.color.holo_red_light)
        zpagerAdapter!!.setisautoplay(true)
        zbanner.setadapter(zpagerAdapter!!)
        zbanner.setbanneritemclick(object :ZBanner.OnBannerItemClick{
            override fun OnItemClick(position: Int) {
                Toast.makeText(this@MainActivity,position.toString(),Toast.LENGTH_SHORT).show()
            }
        }
        )
    }
}
