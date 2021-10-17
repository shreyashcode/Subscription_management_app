package com.example.tv_subscription_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        CheckBox[] categories = new CheckBox[4];
        String[] names = new String[4];
        categories[0] = findViewById(R.id.et);
        names[0] = "Entertainment";
        categories[1] = findViewById(R.id.nw);
        names[1] = "News";
        categories[2] = findViewById(R.id.cmd);
        names[2] = "Comedy";
        categories[3] = findViewById(R.id.ift);
        names[3] = "Infotainment";

        TextView call = findViewById(R.id.place);
        Button pro = findViewById(R.id.proceed);

        call.setOnClickListener(v->{
            dialContactPhone("+919657138721");
        });

        pro.setOnClickListener(v->{
            int index = 0;
            ArrayList<String> choices = new ArrayList<>();
            for(CheckBox checkBox: categories){
                if(checkBox.isChecked()){
                    choices.add(names[index]);
                }
                index++;
            }
            if(choices.isEmpty()){
                Toast.makeText(this, "Make at least one choice", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("DEBUGGING:::", choices.toString());
                Intent toNext = new Intent(HomeActivity.this, SelectChannelActivity.class);
                toNext.putStringArrayListExtra("Selected", choices);
                startActivity(toNext);
            }
        });
    }

    private void dialContactPhone(final String phoneNumber) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phoneNumber, null)));
    }

}