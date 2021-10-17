package com.example.ott;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ott.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private TextInputLayout nameLogLy, emailLogLy, phoneNoLogLy, passwordLogLy;
    private MaterialButton loginButton;

    public static final String SP_KEY= "notesapp_Shared_preferences_access_key";
    public static final String SP_KEY_NAME= "notesapp_name";
    public static final String SP_KEY_EMAIL= "notesapp_email";
    public static final String SP_KEY_PHONE= "notesapp_phone";
    public static final String SP_KEY_PASSWORD= "notesapp_password";
    public static final String SP_KEY_IS_LOGGED_IN = "notesapp_login";
    public FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = FirebaseFirestore.getInstance();


        nameLogLy = findViewById(R.id.nameLogLy);
        emailLogLy = findViewById(R.id.emailLogLy);
        phoneNoLogLy = findViewById(R.id.phoneLogLy);
        passwordLogLy = findViewById(R.id.passwordLogLy);
        loginButton = findViewById(R.id.LoginButton);

        loginButton.setOnClickListener(v -> {
            String name = nameLogLy.getEditText().getText().toString();
            String email = emailLogLy.getEditText().getText().toString();
            String phoneNo = phoneNoLogLy.getEditText().getText().toString();
            String password = passwordLogLy.getEditText().getText().toString();

            if(name.equals("") || email.equals("") || phoneNo.equals("") || password.equals("")){
                Toast.makeText(LoginActivity.this, "enter the details", Toast.LENGTH_SHORT).show();
                return;
            }

            Log.d("hehe",name);
            Log.d("hehe",password);
            Toast.makeText(LoginActivity.this, "LoggedIn", Toast.LENGTH_SHORT).show();
            SharedPreferences preferences = getSharedPreferences(SP_KEY, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.clear();
            editor.apply();
            editor.putString(SP_KEY_NAME, name);
            editor.putString(SP_KEY_EMAIL, email);
            editor.putString(SP_KEY_PHONE, password);
            editor.putString(SP_KEY_PASSWORD, password);
            editor.putBoolean(SP_KEY_IS_LOGGED_IN, true);;
            editor.apply();


            isUserPresent(name, phoneNo, email, password);
            // todo set login in shared preferences:


            finish();
            startActivity(new Intent(this, MainActivity.class));
        });
    }

    public void isUserPresent(String name, String phone, String email, String password){
        db.collection("USER")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        boolean isOld = false;
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String name_ = document.getString("Name");
                            String phone_ = document.getString("Phone");
                            if(name.equals(name_) && phone.equals(phone_)){
                                isOld = true;
                                break;
                            }
                        }
                        if(!isOld){
                            Map<String, Object> k = new HashMap<>();
                            k.put("Name", name);
                            k.put("Email", email);
                            k.put("Password", password);
                            k.put("Phone", phone);

                            db.collection("USER").document(name + phone).set(k);
                        }
                        Common.USER_NAME = name;
                        Common.USER_PHONE = phone;

                    } else {
                        Toast.makeText(this, "Exception!", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}