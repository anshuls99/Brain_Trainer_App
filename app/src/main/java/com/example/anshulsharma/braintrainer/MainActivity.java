package com.example.anshulsharma.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView question;
    TextView correct;
    TextView timer;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView score;
    int scores=0;
    int numberOfQuestions=0;
    ArrayList<Integer>answers=new ArrayList<>();
    ArrayList<Integer>correctAnswers=new ArrayList<>();
    int locationCorrectAnswer;
    public int questionGenerate(){

        Random num1 = new Random();

        int  n1 = num1.nextInt(19) + 1;
        int n2=num1.nextInt(9)+1;
        question=findViewById(R.id.question);
        question.setText(n1+"+"+n2);
        return n1+n2;
    }

    public void optionsGenerate(){
        int n1;
        Random num1 = new Random();
        locationCorrectAnswer=num1.nextInt(4);
        answers.clear();
        correctAnswers.clear();
        for(int i=0;i<4;i++){

            if(i==locationCorrectAnswer) {
                correctAnswers.add(i);
                answers.add(questionGenerate());
            }
            else {
                n1 = num1.nextInt(29) + 1;
                answers.add(n1);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void checkAnswer(View view){

        Button option=(Button)view;
        if(Integer.parseInt(option.getText().toString())==answers.get(correctAnswers.get(0))) {
            Log.i("text",Integer.toString(correctAnswers.get(0)));
            correct.setText("Correct");
            scores++;
        }else {
            Log.i("text1",Integer.toString(correctAnswers.get(0)));
            correct.setText("Wrong");
        }
        correctAnswers.clear();
        questionGenerate();
        optionsGenerate();
        numberOfQuestions++;
        displayScore();
    }

    public void displayScore(){
        score.setText(Integer.toString(scores)+"/"+Integer.toString(numberOfQuestions));
    }

    public void start(View view){

        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        timer.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        correct.setVisibility(View.VISIBLE);
        Button startGame=findViewById(R.id.startGame);
        startGame.setVisibility(View.INVISIBLE);

        CountDownTimer countDownTimer=new CountDownTimer(32000, 1000) {
            int b=31;
            public void onTick(long millisUntilFinished) {
                b--;
                timer.setText("00" + ":" + Integer.toString(b));
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                button0.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                timer.setVisibility(View.INVISIBLE);
                score.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                correct.setVisibility(View.INVISIBLE);
                TextView finalScore=findViewById(R.id.end);
                finalScore.setText("Your Final Score Is "+Integer.toString(scores)+" Out of "+Integer.toString(numberOfQuestions));
                finalScore.setVisibility(View.VISIBLE);
            }

        }.start();
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Random Questions

        questionGenerate();

        //Timer

        timer=findViewById(R.id.timer);

        //Options
        correct=findViewById(R.id.answer);

      button0=findViewById(R.id.button0);
      button1=findViewById(R.id.button1);
      button2=findViewById(R.id.button2);
      button3=findViewById(R.id.button3);
      score=findViewById(R.id.score);

      correct.setText("All The Best");
      score.setText("0/0");
      optionsGenerate();
    }
}
