package com.example.farmmate2

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity

class Intro : AppCompatActivity()

{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        var handler = Handler()
        handler.postDelayed({var intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }, 3000)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }
}

