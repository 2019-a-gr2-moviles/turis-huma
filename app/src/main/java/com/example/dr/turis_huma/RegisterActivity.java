package com.example.dr.turis_huma;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private EditText userEmail,userPassword,userConfirmPassword,userUsername,userCountry;
    private ImageButton createAccountButton;
    private ProgressDialog loadingBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userEmail = findViewById(R.id.txtEmail);
        userUsername = findViewById(R.id.txtNombre);
        userPassword = findViewById(R.id.txtPassword);
        userConfirmPassword = findViewById(R.id.txtPasswordConfirm);
        userCountry = findViewById(R.id.txtCountry);
        createAccountButton = findViewById(R.id.register_next_button);
        loadingBar = new ProgressDialog(this);

        mAuth= FirebaseAuth.getInstance();

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewAccount();
            }
        });

    }

    private void CreateNewAccount() {
        String email = userEmail.getText().toString().trim();
        String username = userUsername.getText().toString().trim();
        String password = userPassword.getText().toString().trim();
        String passwordConfirm =userConfirmPassword.getText().toString().trim();
        String country = userCountry.getText().toString().trim().toUpperCase();

        if(TextUtils.isEmpty(username)){
            Toast.makeText(this,"Debe ingresar un nombre usuario",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Debe ingresar un correo electrónico",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Debe ingresar una contraseña",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(passwordConfirm)){
            Toast.makeText(this,"Debe confirmar la contraseña",Toast.LENGTH_SHORT).show();
        }else if(!password.equals(passwordConfirm)){
            Toast.makeText(this,"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(country)){
            Toast.makeText(this,"Debe ingresar un país",Toast.LENGTH_SHORT).show();
        }else {

            loadingBar.setTitle("Creando cuenta");
            loadingBar.setMessage("Espere mientras creamos su cuenta");
            loadingBar.show();
            loadingBar.setCanceledOnTouchOutside(true);

            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        SendUserToLogin();
                        Toast.makeText(RegisterActivity.this, "Se ha autenticado correctamente", Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }else{
                        String message =  task.getException().getMessage();
                        Toast.makeText(RegisterActivity.this,"Error: "+message,Toast.LENGTH_SHORT).show();
                        loadingBar.dismiss();
                    }
                }
            });



        }
    }

    private void SendUserToLogin() {
        Intent loginIntent = new Intent(RegisterActivity.this,LoginActivity.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        finish();
    }
}
