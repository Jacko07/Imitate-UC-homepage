package com.hyc.imitate_uc_ui.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout

/**
 * 边缘随拖动而形成类似贝塞尔曲线的弯曲
 * Created by hyc on 2017/7/31.
 */
class DeformationLinearLayout(context: Context, attributeSet: AttributeSet, defStyleInt: Int) : LinearLayout(context, attributeSet) {

    constructor(context: Context, attributeSet: AttributeSet) : this(context, attributeSet, 0)

    private var mPaint: Paint? = null
    private var mPath: Path? = null

    private var mMaxX: Int = 0
    private val DEFORM_FACTER: Int = 85
    private var density: Float = 0f
    private var controlY: Float = 0f
    private var isTouchUp: Boolean = false
    private var initY: Float = 0f//该view初始时在父view中的y坐标
    private var mValueAnimator: ValueAnimator? = null
    private var listener:DeformResetListener?=null

    init {

        mMaxX = resources.displayMetrics.widthPixels
        mPaint = Paint()
        mPaint!!.color = Color.parseColor("#FF4081")
        mPaint!!.isAntiAlias = true
        mPaint!!.style = Paint.Style.FILL
        mPath = Path()
        density = resources.displayMetrics.density
        initRegress()
    }

    fun deform(x: Float, y: Float) {
        if (initY <= 0) {
            initY = y
        }
        if (x.equals(0) && y.equals(0)) {
            isTouchUp = true
        }

        var x1 = x
        var y1 = y
        Log.i("y", "y: " + y + ",initY: " + initY)
//        if (y - initY <= 50) {
//            if (!mValueAnimator!!.isStarted&& !isTouchUp) {
//                mValueAnimator?.start()
//            } else {
//                return
//            }
//        } else {
            controlY = Math.pow(y1 / DEFORM_FACTER.toDouble(), 2.toDouble()).toFloat()  //二次函数 曲线变化因快于手指拖动的y变化率
//        }

        mPath!!.reset()
        mPath!!.moveTo(0f, 0f)
        mPath!!.quadTo(x1, controlY, mMaxX.toFloat(), 0f)
        if (y <= initY) {
            if (listener != null) {
                listener?.restScroll(y)
            }
        }
        invalidate()
    }

    public fun setListener(listener: DeformResetListener) {
        this.listener=listener
    }

    private fun initRegress() {
        if (mValueAnimator == null) {
            mValueAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(1500)
        }
        mValueAnimator!!.addUpdateListener { animation ->
            controlY = 50 * (animation.animatedValue as Float)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(mPath, mPaint)
    }

    interface DeformResetListener {
        fun restScroll(y: Float)
    }

}