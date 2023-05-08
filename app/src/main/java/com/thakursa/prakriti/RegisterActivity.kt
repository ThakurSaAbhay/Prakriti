package com.thakursa.prakriti

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.thakursa.prakriti.R.layout.activity_register
import java.util.concurrent.TimeUnit

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_register)



        val entnum=findViewById<EditText>(R.id.Mob)
        val entem=findViewById<EditText>(R.id.Email)
        val regnex=findViewById<Button>(R.id.NextButton)
        val loginAgain=findViewById<TextView>(R.id.LoginAgain)
        auth= FirebaseAuth.getInstance()
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(this@RegisterActivity,e.message,Toast.LENGTH_SHORT).show()
            }

            override fun onCodeSent(
                otpcr: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                Toast.makeText(this@RegisterActivity,"otp sent!",Toast.LENGTH_SHORT).show()

                val regAc = Intent(this@RegisterActivity, otp_verify::class.java)
                regAc.putExtra("otpcr",otpcr)
                regAc.putExtra("email",entem.text.toString())
                regAc.putExtra("mob",entnum.text.toString())
                startActivity(regAc)
                finish()

            }
        }


        regnex.setOnClickListener {
//            val regAc = Intent(this, NextRegisterActivity::class.java)
//            startActivity(regAc)
            if (!entnum.text.toString().trim().isEmpty()&&!entem.text.toString().trim().isEmpty()){
                if(entnum.text.toString().trim().length==10){
                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber("+91"+entnum.text.toString())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)
//                    val regAc = Intent(this, otp_verify::class.java)
//                    startActivity(regAc)
                }else{
                    Toast.makeText(this,"Please enter correct number",Toast.LENGTH_SHORT).show()
                }
            }
            else{Toast.makeText(this,"Please enter number and email ID",Toast.LENGTH_SHORT).show()
            }
        }

        loginAgain.setOnClickListener({
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        })
    }
}