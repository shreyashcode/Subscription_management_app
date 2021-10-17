package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuizQuestions extends AppCompatActivity {

    RadioGroup radioGroup;
    Question question;
    String answerSelected;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_questions);
        question = Common.questions.get(Common.index);
        Log.d("Debug_Statement_01", question.question);
        Common.index++;
        radioGroup = findViewById(R.id.options);
        submit = findViewById(R.id.submit);

        setUp();

        radioGroup.setOnCheckedChangeListener((radioGroup, i)->{
            if(i > 0){
                RadioButton r = findViewById(i);
                setUp();
                r.setBackgroundColor(Color.parseColor("#D37354"));
            }
        });

        submit.setOnClickListener(v->{
            int rId = radioGroup.getCheckedRadioButtonId();
            if(rId < 0){
                Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
            } else {
                RadioButton rb = findViewById(rId);
                if(rb.getText().equals(question.answer)){
                    Common.score++;
                }
                if(Common.index <  Common.questions.size()){
                    finish();
                    startActivity(getIntent());
                } else {
                    Toast.makeText(this,  Common.index + " " + Common.score, Toast.LENGTH_SHORT).show();
                    Common.finishTime = System.currentTimeMillis();
                    Log.d("Debug_Statement_01", "B4 finish");
                    finish();
                    Log.d("Debug_Statement_01", "After finish");
                    startActivity(new Intent(this, MainActivity.class));
                }
            }
        });
    }

    public void setUp(){
        RadioButton rb1 = findViewById(R.id.rb1);
        rb1.setText(question.options.get(0));
        rb1.setBackgroundColor(Color.WHITE);

        rb1 = findViewById(R.id.rb2);
        rb1.setText(question.options.get(1));
        rb1.setBackgroundColor(Color.WHITE);

        rb1 = findViewById(R.id.rb3);
        rb1.setText(question.options.get(2));
        rb1.setBackgroundColor(Color.WHITE);

        rb1 = findViewById(R.id.rb4);
        rb1.setText(question.options.get(3));
        rb1.setBackgroundColor(Color.WHITE);

        TextView ques = findViewById(R.id.question);
        ques.setText(question.question);
    }
}