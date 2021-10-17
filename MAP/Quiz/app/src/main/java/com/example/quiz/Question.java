package com.example.quiz;

import java.util.ArrayList;

public class Question {
    ArrayList<String> options;
    String question, answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
        options = new ArrayList<>();
    }
    public void addOption(String option){
        options.add(option);
    }
}
