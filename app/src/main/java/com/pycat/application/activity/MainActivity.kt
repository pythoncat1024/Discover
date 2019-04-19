package com.pycat.application.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import com.pycat.application.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        getSharedPreferences("abc", Context.MODE_PRIVATE)
                .edit {
                    putBoolean("ok", true)
                }
    }
}
