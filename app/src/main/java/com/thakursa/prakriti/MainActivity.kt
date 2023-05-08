package com.thakursa.prakriti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toolbar:Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navigationView: NavigationView
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar=findViewById(R.id.toolbar)
        drawerLayout=findViewById(R.id.drawerLayout)
        navigationView=findViewById(R.id.navigationview)
        toggle= ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navigationView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.Points -> Toast.makeText(applicationContext,"Clicked Points", Toast.LENGTH_SHORT).show()
                R.id.Score -> Toast.makeText(applicationContext,"Clicked Score", Toast.LENGTH_SHORT).show()
                R.id.logOut -> Toast.makeText(applicationContext,"Clicked Log Out", Toast.LENGTH_SHORT).show()
            }
            true
        }

        val img_stepcounter=findViewById<ImageView>(R.id.imgstepcounter)
        img_stepcounter.setOnClickListener {
            Toast.makeText(this,"hahahaha",Toast.LENGTH_SHORT).show()
            var intent=Intent(this, stepmain::class.java)
            startActivity(intent)
        }
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}
