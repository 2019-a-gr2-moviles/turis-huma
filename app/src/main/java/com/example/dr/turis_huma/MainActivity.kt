package com.example.dr.turis_huma

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {


    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var navigationView: NavigationView? = null
    private var drawerLayout: DrawerLayout? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val currentUser:FirebaseUser ?= mAuth.currentUser
        if(currentUser == null){
            sendUserToLoginActivity()
        }


        drawerLayout = findViewById<View>(R.id.drawable_layout) as DrawerLayout?
        navigationView = findViewById<View>(R.id.navigation_view) as NavigationView?


        navigationView!!.setNavigationItemSelectedListener { menuItem ->
            UserMenuSelector(menuItem)
            false
        }


    }

    fun sendUserToLoginActivity(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun UserMenuSelector(item: MenuItem) {

        when (item.itemId) {
            R.id.home -> {
                Toast.makeText(this, "Pantalla de Inicio", Toast.LENGTH_SHORT).show()

            }

            R.id.logout -> {
                mAuth.signOut()
                sendUserToLoginActivity()
                Toast.makeText(this, "Salir", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
