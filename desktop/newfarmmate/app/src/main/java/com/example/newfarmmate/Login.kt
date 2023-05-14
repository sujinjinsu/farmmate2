package com.example.newfarmmate

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)
        registerNewid()
        loginButton()
    }
    fun registerNewid(){
        val button = findViewById<Button>(com.example.newfarmmate.R.id.register_diary)
        button.setOnClickListener {
            val intent = Intent(this, Diary_register::class.java)
            startActivity(intent)
        }
    }
    fun loginButton(){
        val button = findViewById<Button>(R.id.btn_login)
        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}