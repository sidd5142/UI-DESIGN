package com.example.databasetut

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signButton = findViewById<Button>(R.id.button2)
        val etName = findViewById<TextInputEditText>(R.id.edtName)
        val etMail = findViewById<TextInputEditText>(R.id.edtmail)
        val userId = findViewById<TextInputEditText>(R.id.edtUsername)
        val userPass = findViewById<TextInputEditText>(R.id.edtpass)

        signButton.setOnClickListener {

            val name = etName.text.toString()
            val mail = etMail.text.toString()
            val uniqueId = userId.text.toString()
            val password = userPass.text.toString()

            val user = User(name, mail, uniqueId, password)
            database = FirebaseDatabase.getInstance().getReference("user")
            database.child(uniqueId) .setValue(user).addOnSuccessListener {
              //  etName.text?.clear()


                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }

        }
        val signIn = findViewById<TextView>(R.id.tvSignin)
        signIn.setOnClickListener {
            val openSignInActivity = Intent(this, SignInActivity::class.java)
            startActivity(openSignInActivity)
        }
    }
}