package com.thakursa.prakriti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.ktx.Firebase
import java.util.concurrent.TimeUnit

class otp_verify : AppCompatActivity() {
    lateinit var getotp: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp_verify)

        var inp1 = findViewById<EditText>(R.id.input1)
        var inp2 = findViewById<EditText>(R.id.input2)
        var inp3 = findViewById<EditText>(R.id.input3)
        var inp4 = findViewById<EditText>(R.id.input4)
        var inp5 = findViewById<EditText>(R.id.input5)
        var inp6 = findViewById<EditText>(R.id.input6)

        getotp = intent.getStringExtra("otpcr").toString()
        val email =intent.getStringExtra("email").toString()
        val mob=intent.getStringExtra("mob").toString()


        val veri = findViewById<Button>(R.id.verify)
        veri.setOnClickListener {
//            val regAc = Intent(this, NextRegisterActivity::class.java)
//            startActivity(regAc)
            if (inp1.text.toString().isNotEmpty() && inp2.text.toString()
                    .isNotEmpty() && inp3.text.toString().isNotEmpty() && inp4.text.toString()
                    .isNotEmpty() && inp5.text.toString().isNotEmpty() && inp6.text.toString()
                    .isNotEmpty()
            ) {

                if (getotp != null) {

                    val entOTP: String =
                        inp1.text.toString() + inp2.text.toString() + inp3.text.toString() + inp4.text.toString() + inp5.text.toString() + inp6.text.toString()
                    var phoneAuthCredential: PhoneAuthCredential =
                        PhoneAuthProvider.getCredential(getotp, entOTP)
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intent =
                                    Intent(applicationContext, NextRegisterActivity::class.java)
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                intent.putExtra("email",email.toString())
                                intent.putExtra("mob",mob.toString())
                                Toast.makeText(this, "Phone number verified!"+email
                                    , Toast.LENGTH_SHORT)
                                    .show()
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Enter correct otp!",Toast.LENGTH_SHORT).show()
                            }
                        }
                }
                else {
                    Toast.makeText(
                        this,
                        "Please check your internet connection",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }


        }
        numotmove()


       val resotp=findViewById<TextView>(R.id.resendOTP)

        var auth = FirebaseAuth.getInstance()
        val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                }

                override fun onVerificationFailed(e: FirebaseException) {
                    Toast.makeText(this@otp_verify,e.message,Toast.LENGTH_SHORT).show()
                }

                override fun onCodeSent(
                    newVerificationId: String,
                    token: PhoneAuthProvider.ForceResendingToken
                ) {
                    getotp=newVerificationId

                    Toast.makeText(this@otp_verify,"otp sent again!",Toast.LENGTH_SHORT).show()
                }
            }


            resotp.setOnClickListener {
//            val regAc = Intent(this, NextRegisterActivity::class.java)
//            startActivity(regAc)
                if (!intent.getStringExtra("mob").toString().trim().isEmpty()){
                    if(intent.getStringExtra("mob").toString().trim().length==10){
                        val options = PhoneAuthOptions.newBuilder(auth)
                            .setPhoneNumber("+91"+intent.getStringExtra("mob").toString().trim().toString())       // Phone number to verify
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
                else{Toast.makeText(this,"Please enter number",Toast.LENGTH_SHORT).show()
                }
            }
        }


    private fun numotmove() {
        var inp1 = findViewById<EditText>(R.id.input1)
        var inp2 = findViewById<EditText>(R.id.input2)
        var inp3 = findViewById<EditText>(R.id.input3)
        var inp4 = findViewById<EditText>(R.id.input4)
        var inp5 = findViewById<EditText>(R.id.input5)
        var inp6 = findViewById<EditText>(R.id.input6)


        inp1.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().trim().isNotEmpty()){
                    inp2.requestFocus()
                }
            }
        })

        inp2.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().trim().isNotEmpty()){
                    inp3.requestFocus()
                }
            }
        })

        inp3.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().trim().isNotEmpty()){
                    inp4.requestFocus()
                }
            }
        })

        inp4.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().trim().isNotEmpty()){
                    inp5.requestFocus()
                }
            }
        })

        inp5.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s.toString().trim().isNotEmpty()){
                    inp6.requestFocus()
                }
            }
        })

    }}