package com.example.databasetut

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.security.Key

class SignInActivity : AppCompatActivity() {

    private lateinit var database : DatabaseReference

    companion object{
        const val KEY1 = "com.example.database.SignInActivity.mail"
        const val KEY2 = "com.example.database.SignInActivity.name"
        const val KEY3 = "com.example.database.SignInActivity.id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        val signInButton  = findViewById<Button>(R.id.signinbtn)
        val userName = findViewById<TextInputEditText>(R.id.edtUsernametv)

        signInButton.setOnClickListener {

            val uniqueId = userName.text.toString()
            if (uniqueId.isNotEmpty()){

                readData(uniqueId)
            }
            else{
                Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun readData(uniqueId: String) {
        database = FirebaseDatabase.getInstance().getReference("user")

        database.child(uniqueId).get().addOnSuccessListener {
          // if user exist or not(it = uniqueId)
            if (it.exists()) {
                //welcome user in your app with intent and also pass
                val email = it.child("email").value
                val name = it.child("name").value
                val userId = it.child("uniqueId").value

                val intentWelcome = Intent(this, welcomeActivity::class.java)
                intentWelcome.putExtra(KEY1, email.toString())
                intentWelcome.putExtra(KEY2, name.toString())
                intentWelcome.putExtra(KEY3, userId.toString())
                startActivity(intentWelcome)

            } else{
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }

        }.addOnFailureListener {
            Toast.makeText(this, "Failed or Error in DB", Toast.LENGTH_SHORT).show()
        }
    }
}