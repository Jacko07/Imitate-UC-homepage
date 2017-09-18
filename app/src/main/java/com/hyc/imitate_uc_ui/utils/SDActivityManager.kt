//package com.hyc.imitate_uc_ui.utils
//
//import android.app.Activity
//import java.util.Stack
//
//class SDActivityManager private constructor() {
//
//    init {
//        mStackActivity = Stack<Activity>()
//    }
//
//    // ----------------------------activity life method
//
//    fun onCreate(activity: Activity) {
//        addActivity(activity)
//    }
//
//    fun onResume(activity: Activity) {
//        addActivity(activity)
//    }
//
//    /**
//     * finish()和onDestroy()都要调用
//
//     * @param activity
//     */
//    fun onDestroy(activity: Activity) {
//        removeActivity(activity)
//    }
//
//    private fun addActivity(activity: Activity) {
//        if (!mStackActivity.contains(activity)) {
//            mStackActivity.add(activity)
//        }
//    }
//
//    private fun removeActivity(activity: Activity?) {
//        if (activity != null) {
//            mStackActivity.remove(activity)
//        }
//    }
//
//    val lastActivity: Activity?
//        get() {
//            var activity: Activity? = null
//            try {
//                activity = mStackActivity.lastElement()
//            } catch (e: Exception) {
//            }
//
//            return activity
//        }
//
//    fun isLastActivity(activity: Activity?): Boolean {
//        if (activity != null) {
//            return lastActivity === activity
//        } else {
//            return false
//        }
//    }
//
//    val isEmpty: Boolean
//        get() = mStackActivity.isEmpty()
//
//    /**
//     * 结束指定类名的Activity
//     */
//    fun finishActivity(cls: Class<*>) {
//        val it = mStackActivity.iterator()
//        while (it.hasNext()) {
//            val act = it.next()
//            if (act.javaClass == cls) {
//                it.remove()
//                act.finish()
//            }
//        }
//    }
//
//    fun finishAllClassActivityExcept(activity: Activity) {
//        val it = mStackActivity.iterator()
//        while (it.hasNext()) {
//            val act = it.next()
//            if (act.javaClass == activity.javaClass && act !== activity) {
//                it.remove()
//                act.finish()
//            }
//        }
//    }
//
//    fun finishAllActivity() {
//        val it = mStackActivity.iterator()
//        while (it.hasNext()) {
//            val act = it.next()
//            it.remove()
//            act.finish()
//        }
//    }
//
//    fun finishAllActivityExcept(cls: Class<*>) {
//        val it = mStackActivity.iterator()
//        while (it.hasNext()) {
//            val act = it.next()
//            if (act.javaClass != cls) {
//                it.remove()
//                act.finish()
//            }
//        }
//    }
//
//    fun finishAllActivityExcept(activity: Activity) {
//        val it = mStackActivity.iterator()
//        while (it.hasNext()) {
//            val act = it.next()
//            if (act !== activity) {
//                it.remove()
//                act.finish()
//            }
//        }
//    }
//
//    companion object {
//        private var mStackActivity: Stack<Activity>=null
//        private var mInstance: SDActivityManager? = null
//
//        val instance: SDActivityManager
//            get() {
//                if (mInstance == null) {
//                    synchronized(SDActivityManager::class.java) {
//                        if (mInstance == null) {
//                            mInstance = SDActivityManager()
//                        }
//                    }
//                }
//                return mInstance
//            }
//    }
//
//}