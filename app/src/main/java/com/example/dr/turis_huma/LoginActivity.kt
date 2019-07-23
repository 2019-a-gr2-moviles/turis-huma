package com.example.dr.turis_huma

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import org.w3c.dom.Text

class LoginActivity : AppCompatActivity() {

    private var userEmail:EditText ?= null
    private var userPassword:EditText ?= null
    private var mAuth:FirebaseAuth?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userEmail = findViewById(R.id.login_username)
        userPassword = findViewById(R.id.login_password)

        mAuth = FirebaseAuth.getInstance()

        register_link.setOnClickListener {
           registerNewUser()
        }

        login_button.setOnClickListener{
            loginAUser()
        }

    }

    private fun loginAUser() {

        val emailUserName:String = userEmail?.text.toString()
        val passwordUser:String = userPassword?.text.toString()

        if(TextUtils.isEmpty(emailUserName)){
            Toast.makeText(this,"Ingresar el nombre de usuario/e-mail",Toast.LENGTH_SHORT).show()
        }else if (TextUtils.isEmpty(passwordUser)){
            Toast.makeText(this,"Ingrese la contraseña",Toast.LENGTH_SHORT).show()
        }else{
            mAuth!!.signInWithEmailAndPassword(emailUserName, passwordUser).addOnCompleteListener(object :
                OnCompleteListener<AuthResult> {
                override fun onComplete(task: Task<AuthResult>) {
                    if (task.isSuccessful) {

                    }else{

                    }
                }
            })
        }

    }

    fun registerNewUser(){
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }
}
