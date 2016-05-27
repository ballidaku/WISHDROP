package com.example.sharan.wishdrop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener
{

    Context con;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        con = this;

        setUpIds();
    }

    private void setUpIds()
    {
        findViewById(R.id.txtv_signup).setOnClickListener(this);
        findViewById(R.id.txtv_sign_in).setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.txtv_signup:

                startActivity(new Intent(con, SignUp.class));

                break;

            case R.id.txtv_sign_in:

                startActivity(new Intent(con, MainActivity.class));

                break;

        }
    }
}
