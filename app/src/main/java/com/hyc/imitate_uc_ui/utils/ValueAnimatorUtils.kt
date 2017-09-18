//package com.hyc.imitate_uc_ui.utils
//
//import android.animation.ValueAnimator
//
///**
// *
// * Created by hyc on 2017/8/28.
// */
// class ValueAnimatorUtils private constructor(){
//
//
//    init {
//        mListeners = hashMapOf()
//        defValueAnimator.setFloatValues(1f,0f)
//        defValueAnimator.duration=1000
//    }
//
//    companion object {
//        private var mListeners:HashMap<String, ValueAnimator.AnimatorUpdateListener>?=null
//        val defValueAnimator: ValueAnimator by lazy { ValueAnimator() }
//
//        fun putListener(key:String,listener: ValueAnimator.AnimatorUpdateListener) {
//
//            mListeners!!?.put(key,listener)
//
//        }
//
//        fun getListener(key: String) :ValueAnimator.AnimatorUpdateListener?{
//            return mListeners?.get(key)
//        }
//
//    }
//
//
//}