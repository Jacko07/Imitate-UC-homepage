package com.hyc.imitate_uc_ui.view

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import com.hyc.imitate_uc_ui.R

/**
 * UC首页效果
 * Created by hyc on 2017/8/1.
 */
class UCHomePageView(context: Context, attributeSet: AttributeSet, defIntStyle: Int) : RelativeLayout(context, attributeSet, defIntStyle) {

    var mFlHeader: FrameLayout? = null
    var mDllContainer: DeformationLinearLayout? = null
    var mLlTabs: LinearLayout? = null
//    var mFlContainer: FrameLayout? = null
    var mFlHeadLine: FrameLayout? = null
    var mLlSuggest: LinearLayout? = null
    var mLlNewsList:LinearLayout?=null

    var mHeaderHeight: Int = 0
    var mScreenHeight: Int = 0
    var mDensity: Float = 0f
    var mTouchSlop: Int = 0
    var startX: Float = 0f
    var startY: Float = 0f
    var endX: Float = 0f
    var endY: Float = 0f
    var mValueAnimator: ValueAnimator? = null

    constructor(context: Context, attributeSet: AttributeSet) : this(context, attributeSet, 0)

    init {
        var view: View? = LayoutInflater.from(context).inflate(R.layout.view_pulltorefresh, this, true)
        mFlHeader = view!!.findViewById(R.id.fl_header) as FrameLayout
        mDllContainer = view!!.findViewById(R.id.dll_container) as DeformationLinearLayout
        mLlTabs = view!!.findViewById(R.id.ll_tabs) as LinearLayout
//        mFlContainer = view!!.findViewById(R.id.fl_content) as FrameLayout
        mFlHeadLine = view!!.findViewById(R.id.fl_headline) as FrameLayout
        mLlSuggest = view!!.findViewById(R.id.ll_suggest) as LinearLayout
        mLlNewsList=view!!.findViewById(R.id.ll_news_list) as LinearLayout

        mHeaderHeight = mFlHeadLine!!.height
        mScreenHeight = resources.displayMetrics.heightPixels
        mDensity = resources.displayMetrics.density
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop

        initRegress()
    }

    private fun initRegress() {
        if (mValueAnimator == null) {
            mValueAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(1500)
        }
        mValueAnimator!!.addUpdateListener { animation ->
            var tmpY = endY * (animation.animatedValue as Float)
            scrollTo(tmpY)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val eventAction: Int = event.action
        Log.i("onTouchEvent", "MotionEvent:" + eventAction)
        when (eventAction) {

            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
                Log.i("onTouchEvent", "startY:" + startY + ",startX" + startX + ",mHeaderHeight" + mHeaderHeight)
                if (startY < mHeaderHeight) {
                    Log.i("onTouchEvent", "ACTION_DOWN:" + false)
                    return false
                }
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val scrollX: Float = event.x
                val scrollY: Float = event.y
                var deltaY = scrollY - startY
                if (Math.abs(deltaY) > mTouchSlop) {

                    if (deltaY < 300) {
                        endY = deltaY
                        scrollTo(endY)
                    } else {
//                        endY=300+calculateRate(deltaY)
                        Log.i("padding", "paddingTop:" + calculateRate(deltaY))
                        mDllContainer!!.setPadding(0, -calculateRate(deltaY).toInt(), 0, 0)
                    }
//                    scrollTo(endY)
                }

                Log.i("onTouchEvent", "scrollY:" + scrollY + ",scrollX:" + scrollX + ",deltaY:" + deltaY)
                var tmpY = mDllContainer!!.y
                if (tmpY <= startY) {
                    mDllContainer!!.deform(scrollX, tmpY + endY)
                    mDllContainer?.setListener(object : DeformationLinearLayout.DeformResetListener {
                        override fun restScroll(y: Float) {
                            mLlNewsList!!.scrollTo(0,y.toInt())
                            mLlSuggest!!.scrollTo(0,(y*1.5).toInt())
                        }
                    })
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                endX = event.x
                Log.i("onTouchEvent", "endY:" + endY + ",endX:" + endX)
                mDllContainer!!.deform(0f, 0f)
                mDllContainer!!.setPadding(0, 0, 0, 0)
                mValueAnimator!!.start()
                return true
            }

        }
//        Log.i("onTouchEvent","super.onTouchEvent(event):"+super.onTouchEvent(event))
        return super.onTouchEvent(event)
    }

    private fun scrollTo(y: Float) {
        var tmpY = Math.max(0.toDouble(), y.toDouble())
        setPadding(0, tmpY.toInt(), 0, 0)
    }

    /**
     * 阻尼拖动效果
     */
    private fun calculateRate(deltaY: Float): Float {
        var scale = deltaY / 200 * mDensity
//        var param = Math.min(1f, scale).toDouble()
        Log.i("padding", "scale:" + scale + ",deltaY:" + deltaY)
//        var value=Math.log10(scale.toDouble())
        var value = -Math.pow(scale.toDouble(), 2.toDouble()) + scale
        return value.toFloat()
    }


}