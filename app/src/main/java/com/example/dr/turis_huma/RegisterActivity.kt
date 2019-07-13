package com.example.dr.turis_huma

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.view.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {

    // private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()
    // private val loadingBar:ProgressDialog = ProgressDialog.show(this,"Creando nueva cuenta","Espere mientras creamos su cuenta")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val userName:EditText = txtNombre
        val userEmail:EditText = findViewById(R.id.txtEmail)
        val userPassword :EditText= findViewById(R.id.txtPassword)
        val userConfirmPassword :EditText= findViewById(R.id.txtPasswordConfirm)
        val userCountry :EditText= findViewById(R.id.txtCountry)
        //val createAccountButton : ImageButton = findViewById(R.id.btnNext2)



       // createAccountButton.setOnClickListener(){
           // createNewAccount(userName, userEmail, userPassword, userConfirmPassword,userCountry)
        //}
    }


   /* fun createNewAccount(username:EditText, email:EditText, pass:EditText, confPass:EditText, country:EditText){
        val userValue = username.text.toString()
        val userEmail = email.text.toString()
        val userPassword = pass.text.toString()
        val userConfPass = confPass.text.toString()
        val userCountry = country.text.toString()

        if(TextUtils.isEmpty(userValue)){
            Toast.makeText(this,"Debe ingresar un nombre usuario",Toast.LENGTH_SHORT).show()
        }else if(TextUtils.isEmpty(userEmail)){
            Toast.makeText(this,"Debe ingresar un correo electrónico",Toast.LENGTH_SHORT).show()
        }else if(TextUtils.isEmpty(userPassword)){
            Toast.makeText(this,"Debe ingresar una contraseña",Toast.LENGTH_SHORT).show()
        }else if(TextUtils.isEmpty(userConfPass)){
            Toast.makeText(this,"Debe confirmar la contraseña",Toast.LENGTH_SHORT).show()
        }else if(!userPassword.equals(userConfPass)){
            Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show()
        }else if (TextUtils.isEmpty(userCountry)){
            Toast.makeText(this,"Debe ingresar un correo electrónico",Toast.LENGTH_SHORT).show()
        }else{

            loadingBar
            loadingBar.setCanceledOnTouchOutside(true)
            mAuth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener{
                task ->
                if (task.isSuccessful){
                    Toast.makeText(this,"El usuario ha sido creado exitosamente",Toast.LENGTH_SHORT).show()
                    loadingBar.dismiss()
                }else{
                    val message:String = task.exception?.message.toString()
                    Toast.makeText(this,"Error: ${message}",Toast.LENGTH_SHORT).show()
                    loadingBar.dismiss()
                }
            }
        }
    }
*/

}
