package com.ameba.sharan.wishdrop;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        c.start();
    }


    CountDownTimer c = new CountDownTimer(3000, 1000)
    {

        public void onTick(long millisUntilFinished)
        {

        }

        public void onFinish()
        {
            startActivity(new Intent(Splash.this, Login.class));
            finish();
        }
    };
}
