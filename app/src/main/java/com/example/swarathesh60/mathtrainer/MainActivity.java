package com.example.swarathesh60.mathtrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button beginReset,button1,button2,button3,button4;
    TextView result,header,question,score,timer;
    RelativeLayout dash;
    int resultAnswer,CorrectOption,Score=0,total=0;
    List<String> answers = new ArrayList<String>();
    boolean IsFirstlaunch = true;

    CountDownTimer counter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        beginReset = (Button) findViewById(R.id.begin);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        result = (TextView) findViewById(R.id.result);
        header = (TextView) findViewById(R.id.header);
        question = (TextView) findViewById(R.id.question);
        score = (TextView)  findViewById(R.id.score);
        timer = (TextView) findViewById(R.id.timer);
//        resultend = (TextView) findViewById(R.id.resultend);

        dash = (RelativeLayout) findViewById(R.id.dash);
//        grid =(GridLayout) findViewById(R.id.grid);

    }

    private void PopulateGrid() {
        try {
            answers.clear();
            Random ran = new Random();
            int option = ran.nextInt(4);
            final String[] symbols = {"+", "-", "*", "/"};
            Log.i("symbol", String.valueOf(symbols[option]));
            int variable1 = ran.nextInt(10) + 1;
            int variable2 = ran.nextInt(20);
            resultAnswer = option == 0 ? variable1 + variable2 : option == 1 ? variable1 - variable2 : option == 2 ? variable1 * variable2 : (int) variable1 / variable2;
            CorrectOption = ran.nextInt(4);
//        int temp = resultAnswer < 0 ? (resultAnswer*-1):resultAnswer;
////        int halfResult=resultAnswer/2;
            int temp = 100;
            for (int i = 0; i <= 3; i++) {
                if (i == CorrectOption) {
                    answers.add(String.valueOf(resultAnswer));
                } else {
                    int WrongAnswers = ran.nextInt(temp);
                    while (WrongAnswers == resultAnswer || answers.contains(String.valueOf(WrongAnswers))) {
                        WrongAnswers = ran.nextInt(temp);
                    }
                    answers.add(String.valueOf(WrongAnswers));
                }
            }

            String quesSet = variable1 + " " + String.valueOf(symbols[option]) + " " + variable2 + " = " + resultAnswer;

            question.setText(quesSet);
            button1.setText(answers.get(0));
            button2.setText(answers.get(1));
            button3.setText(answers.get(2));
            button4.setText(answers.get(3));
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void ChooseAnswer(View view){
        if (view.getTag().toString().equals(String.valueOf(CorrectOption))){
            Score = Score +1;
            result.setText("Correct!");
        }else {
            result.setText("Wrong!!");
        }
        score.setText(String.valueOf(Score));
//        try{
//        PopulateGrid();}
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        total++;
        PopulateGrid();


    }

    public void Begin(View view){

            beginReset.setVisibility(View.INVISIBLE);
            result.setVisibility(View.VISIBLE);
            dash.setVisibility(View.VISIBLE);
            header.setVisibility(View.GONE);

            InitiateTimer();

            PopulateGrid();



    }

    private void InitiateTimer() {
       counter= new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000));
            }

            @Override
            public void onFinish() {
                question.setText("your score "+String.valueOf(Score));
                timer.setText("0");
                result.setVisibility(View.INVISIBLE);
                beginReset.setText("Reset");
                Score =0;
                counter.cancel();
                beginReset.setVisibility(View.VISIBLE);

            }
        }.start();
    }
}

