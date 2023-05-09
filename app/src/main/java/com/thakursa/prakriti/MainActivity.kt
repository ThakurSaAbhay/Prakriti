package com.thakursa.prakriti

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var imgweatherref: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        var txtp=findViewById<TextView>(R.id.txtpoints)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationview)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.Points -> Toast.makeText(
                    applicationContext,
                    "Clicked Points",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.Score -> Toast.makeText(
                    applicationContext,
                    "Clicked Score",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.logOut -> Toast.makeText(
                    applicationContext,
                    "Clicked Log Out",
                    Toast.LENGTH_SHORT
                ).show()
            }
            true
        }

        val wpref= getSharedPreferences("count", 0)
        val wstr = wpref.getInt("count", 1)
        Toast.makeText(this,wstr.toString(),Toast.LENGTH_SHORT).show()
        if (wstr != null ) {
            txtp.text=(Integer.parseInt(txtp.text.toString())+wstr).toString()
        }


        val mpref = getSharedPreferences("steps", 0)
        val str = mpref.getInt("steps", 0)
        Toast.makeText(this,str.toString()+" "+wstr.toString(),Toast.LENGTH_SHORT).show()
        if (str != null && wstr != null) {
            txtp.text=((str/10)+wstr).toString()
        }


        val img_stepcounter=findViewById<ImageView>(R.id.imgstepcounter)
        img_stepcounter.setOnClickListener {
            var intent=Intent(this, stepmain::class.java)
            startActivity(intent)
        }
        imgweatherref=findViewById(R.id.imgweatherref)
        imgweatherref.setOnClickListener{
            val intent=Intent(this@MainActivity,WeatherActivity::class.java)
            startActivity(intent)
        }
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
