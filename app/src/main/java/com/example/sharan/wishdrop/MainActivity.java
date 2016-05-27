package com.example.sharan.wishdrop;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import Adapters.DrawerList_Adapter;
import Fragments.AboutUs;
import Fragments.FragmentDrawer;
import Fragments.Friends;
import Fragments.InviteFriends;
import Fragments.Messages;
import Fragments.MyWishes;
import Fragments.Settings;
import Fragments.ShareApp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    Toolbar           mToolbar;
    DrawerLayout      mDrawerLayout;
    FragmentDrawer    drawerFragment;
    ListView          listv_drawer;
    ArrayList<String> listDataHeader;

    DrawerList_Adapter drawer_adapter;
    Context            con;
    TextView           txt_titleTV;
    ImageView          imgv_profile;
    Fragment           fragment;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        con = this;
        //constant.ChangeStatusColor(this,R.color.Black);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        txt_titleTV = (TextView) mToolbar.findViewById(R.id.txt_titleTV);

        listv_drawer = (ListView) findViewById(R.id.listv_drawer);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        imgv_profile = (ImageView) findViewById(R.id.imgv_profile);
        imgv_profile.setOnClickListener(this);

        prepareListData();

        drawerFragment = (FragmentDrawer) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);

        drawer_adapter = new DrawerList_Adapter(con, listDataHeader,0);
        listv_drawer.setAdapter(drawer_adapter);

        listv_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                displayView(position);

                drawer_adapter.changeSelectedBackground(position);
                drawer_adapter.notifyDataSetChanged();
            }
        });

        displayView(0);

    }

    private void prepareListData()
    {
        listDataHeader = new ArrayList<>();

        listDataHeader.add("My Wishes");
        listDataHeader.add("Friends");
        listDataHeader.add("Messages");
        listDataHeader.add("Settings");
        listDataHeader.add("About us");
        listDataHeader.add("Invite Friends");
        listDataHeader.add("Share App");

    }

    public void displayView(int groupPosition)
    {

        switch (groupPosition)
        {
            case 0:
                txt_titleTV.setText(listDataHeader.get(groupPosition));
                fragment = new MyWishes();
                break;

            case 1:
                txt_titleTV.setText(listDataHeader.get(groupPosition));
                fragment = new Friends();
                break;

            case 2:
                txt_titleTV.setText(listDataHeader.get(groupPosition));
                fragment = new Messages();
                break;

            case 3:
                txt_titleTV.setText(listDataHeader.get(groupPosition));
                fragment = new Settings();
                break;

            case 4:
                txt_titleTV.setText(listDataHeader.get(groupPosition));
                fragment = new AboutUs();
                break;

            case 5:
                txt_titleTV.setText(listDataHeader.get(groupPosition));
                fragment = new InviteFriends();
                break;

            case 6:
                txt_titleTV.setText(listDataHeader.get(groupPosition));
                fragment = new ShareApp();
                break;

            default:
                break;
        }

        if (fragment != null)
        {
            FragmentManager     fragmentManager     = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            mDrawerLayout.closeDrawers();
        }
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.imgv_profile:

                //                openProfile();

                break;

            default:
                break;
        }
    }

}