package com.example.databasetut

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class welcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val name = intent.getStringExtra(SignInActivity.KEY2)
        val mail = intent.getStringExtra(SignInActivity.KEY1)
        val userId = intent.getStringExtra(SignInActivity.KEY3)

        val welcometext = findViewById<TextView>(R.id.tvwelcome)
        val mailtext  = findViewById<TextView>(R.id.tvMail)
        val idText = findViewById<TextView>(R.id.tvUNique)
        welcometext.text = "Welcome$name"

        welcometext.text = "Welcome : $name"
        mailtext.text = "Mail : $mail"
        idText.text = "UserId : $userId"
    }
}