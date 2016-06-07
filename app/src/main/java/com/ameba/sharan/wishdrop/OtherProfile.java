package com.ameba.sharan.wishdrop;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import Fragments.Friends;
import Fragments.OtherProfileMainPage;
import Fragments.WishList;

public class OtherProfile extends AppCompatActivity implements View.OnClickListener
{
    LinearLayout lay_back;
    TextView     txtv_title;
    Context      con;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);

        con = this;
        setUpIds();



        changeFragment(0);

    }

    public void changeFragment(int position)
    {

        Fragment fragment = null;

        switch (position)
        {

            case 0:
                txtv_title.setText("Profile");
                fragment = new OtherProfileMainPage();
                break;

            case 1:
                txtv_title.setText("Wish List");
                fragment = new WishList();
                break;

            case 3:
                txtv_title.setText("Friends");
                fragment = new Friends();
                break;

        }

        if (fragment != null)
        {
            FragmentManager     fragmentManager     = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
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
                clearBackStack();
                break;

        }
    }

    @Override
    public void onBackPressed()
    {
        clearBackStack();
        //        super.onBackPressed();

    }

    private void clearBackStack()
    {
        final FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getBackStackEntryCount() == 1)
        {
            finish();
        }
        else
        {
            txtv_title.setText("Profile");
            fragmentManager.popBackStackImmediate();
        }

        Log.e("After", "" + fragmentManager.getBackStackEntryCount());

    }
}
