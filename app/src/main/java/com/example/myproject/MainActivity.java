package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //initialize variables
    EditText username, password;
    private Button depositbtn;
    private Button creditbtn;
    private TextView welcome;
    int count = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating a timer
        final TextView textView = (TextView) findViewById(R.id.timer);
        Thread t = new Thread(){
          @Override
          public void run(){
              while (!isInterrupted()){
                  try {
                      Thread.sleep(1000); //1000millis = 1sec
                      runOnUiThread(new Runnable() {
                          @Override
                          public void run() {
                              count++;
                              textView.setText(String.valueOf(count));
                          }
                      });
                  } catch (InterruptedException e){
                      e.printStackTrace();
                  }
              }
          }
        };
        t.start();


        //declaring variables
         welcome = (TextView) findViewById(R.id.welcome);
         depositbtn = findViewById(R.id.depositbtn);
        manageBlinkEffect();


        //deposit button opens second activity
        depositbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToActivity2();
            }
        });
    }
    //creating method of text animation
    public void manageBlinkEffect(){
        ObjectAnimator anim = ObjectAnimator.ofInt(welcome, "backgroundColor", Color.WHITE, Color.GREEN, Color.RED);
        anim.setDuration(800);
        anim.setEvaluator(new ArgbEvaluator());
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        anim.start();
    }
    private void moveToActivity2(){
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }

}
