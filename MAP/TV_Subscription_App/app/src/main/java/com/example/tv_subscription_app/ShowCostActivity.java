package com.example.tv_subscription_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ShowCostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cost);
        TextView cost = findViewById(R.id.cost_bill);
        cost.setText("Rs. " + Common.cost + " /-");
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(v->{
            finish();
        });
    }
}