package com.example.newfarmmate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity



class Diary_plant_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary_plant)

        settingButton()
    }
    fun settingButton(){
        val button = findViewById<Button>(R.id.register_diary)
        button.setOnClickListener {
            val intent = Intent(this, Diary_register::class.java)
            startActivity(intent)
        }
    }
}