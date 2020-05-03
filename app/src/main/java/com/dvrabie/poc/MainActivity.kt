package com.dvrabie.poc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dvrabie.shieldoid.isDebugMode
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDebugMode.setOnClickListener { checkDebugMode() }
    }

    private fun checkDebugMode() {
        tvStatus.text = getString(R.string.debug_mode, isDebugMode())
    }
}
