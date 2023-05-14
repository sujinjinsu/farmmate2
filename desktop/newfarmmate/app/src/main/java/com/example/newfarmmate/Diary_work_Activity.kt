package com.example.newfarmmate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Diary_work_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_work)
        settingButton()
    }
    fun settingButton(){
        val button = findViewById<Button>(com.example.newfarmmate.R.id.register_diary)
        button.setOnClickListener {
            val intent = Intent(this, Login_register::class.java)
            startActivity(intent)
        }
    }
}