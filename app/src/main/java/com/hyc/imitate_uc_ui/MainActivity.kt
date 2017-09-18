package com.hyc.imitate_uc_ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("onCreate","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
