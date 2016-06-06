package com.example.sharan.wishdrop;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddWish extends AppCompatActivity implements View.OnClickListener
{

    Context      con;
    LinearLayout lay_back;
    TextView     txtv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wish);

        con = this;
        setUpIds();

        txtv_title.setText("Add Wish");
    }

    private void setUpIds()
    {

        (lay_back = (LinearLayout) findViewById(R.id.lay_back)).setOnClickListener(this);
        txtv_title = (TextView) findViewById(R.id.txtv_title);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.lay_back:
                finish();
                break;

        }
    }
}
