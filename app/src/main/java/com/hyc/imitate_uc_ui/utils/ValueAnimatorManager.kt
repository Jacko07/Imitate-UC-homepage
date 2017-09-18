package com.hyc.imitate_uc_ui.utils

import com.hyc.imitate_uc_ui.view.DeformationLinearLayout

/**
 * Created by hyc on 2017/8/26.
 */

class ValueAnimatorManager {
    internal var d: DeformationLinearLayout? = null

    private fun test() {
        d!!.setListener(object : DeformationLinearLayout.DeformResetListener {
            override fun restScroll(y: Float) {

            }
        })
    }
}
