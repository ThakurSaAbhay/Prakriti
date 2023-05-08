package com.thakursa.prakriti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_activity)

        FirebaseApp.initializeApp(this)
        auth=FirebaseAuth.getInstance()

        val uname=findViewById<EditText>(R.id.username)
        val pswd=findViewById<EditText>(R.id.password)

        val reg=findViewById<TextView>(R.id.signin)
        reg.setOnClickListener {
            val regAc = Intent(this, RegisterActivity::class.java)
            startActivity(regAc)
            finish()
        }

        val logbut=findViewById<Button>(R.id.LoginButton)
        logbut.setOnClickListener {
            if (uname.text.toString().isNotEmpty() && pswd.text.toString().isNotEmpty()) {
                val name: String = uname.text.toString().trim()
                val pass: String = pswd.text.toString().trim()
                auth.signInWithEmailAndPassword(name, pass)
                    .addOnSuccessListener {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this, "Login Successful!", Toast.LENGTH_SHORT).show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Login Failed recheck email id and password!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }

            else {
                Toast.makeText(this, "Fill both the fields!", Toast.LENGTH_SHORT).show()
            }
        }


        val forpswd=findViewById<TextView>(R.id.forpswd)
        forpswd.setOnClickListener {
            if (uname.text.toString().isNotEmpty()) {
                auth.sendPasswordResetEmail(uname.text.toString())
                    .addOnSuccessListener(
                        {
                            Toast.makeText(this, "Password reset mail sent", Toast.LENGTH_SHORT)
                                .show()
                        })
                    .addOnFailureListener(
                        {
                            Toast.makeText(this, "Can't send mail", Toast.LENGTH_SHORT).show()
                        }
                    )
            } else {
                Toast.makeText(this, "Enter registered email!", Toast.LENGTH_SHORT).show()
            }
        }

    }

}