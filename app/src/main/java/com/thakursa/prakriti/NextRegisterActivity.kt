package com.thakursa.prakriti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class NextRegisterActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next_register)
        auth=FirebaseAuth.getInstance()
        val email=intent.getStringExtra("email").toString()
        val pswd=findViewById<EditText>(R.id.password)
        val cpswd=findViewById<EditText>(R.id.cpassword)
        val logbut=findViewById<Button>(R.id.SignUpButton)
        logbut.setOnClickListener {
            if(pswd.text.toString().isNotEmpty()&&cpswd.text.toString().isNotEmpty()){
            if(pswd.text.toString()==cpswd.text.toString()) {
                auth.createUserWithEmailAndPassword(email,pswd.text.toString())
                    .addOnSuccessListener {

//                        firestore.collection("User").document(uid).set(email)
                        var intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Some error occured in registering",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
            }
            else
            {
                Toast.makeText(this,"Passwords don't match",Toast.LENGTH_SHORT).show()
            }}
            else{
                Toast.makeText(this,"Fill all fields",Toast.LENGTH_SHORT).show()
            }
        }
    }
}





