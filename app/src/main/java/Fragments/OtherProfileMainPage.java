package Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sharan.wishdrop.Chat.Chat_Activity;
import com.example.sharan.wishdrop.OtherProfile;
import com.example.sharan.wishdrop.R;

import java.util.ArrayList;

import Adapters.OtherProfile_Images_Adapter;

public class OtherProfileMainPage extends Fragment implements View.OnClickListener
{
    RecyclerView recycleview_otherprofile_images;
    Context      con;

    public OtherProfileMainPage()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other_profile_main_page, container, false);

        con = getActivity();
        setUpIds(view);

        return view;
    }

    private void setUpIds(View view)
    {
        view.findViewById(R.id.textv_wishes).setOnClickListener(this);
        view.findViewById(R.id.txtv_message).setOnClickListener(this);
        view.findViewById(R.id.textv_friends).setOnClickListener(this);


        recycleview_otherprofile_images = (RecyclerView) view.findViewById(R.id.recycleview_otherprofile_images);

        LinearLayoutManager layoutManager = new LinearLayoutManager(con);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview_otherprofile_images.setLayoutManager(layoutManager);

        ArrayList<Integer> list = new ArrayList<>();
        list.add(R.mipmap.img_one);
        list.add(R.mipmap.img_two);
        list.add(R.mipmap.img_three);
        list.add(R.mipmap.img_one);
        list.add(R.mipmap.img_two);
        list.add(R.mipmap.img_three);

        OtherProfile_Images_Adapter adapter = new OtherProfile_Images_Adapter(con, list);
        recycleview_otherprofile_images.setAdapter(adapter);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.textv_wishes:

                ((OtherProfile) con).changeFragment(1);

                break;

            case R.id.textv_friends:

                ((OtherProfile) con).changeFragment(3);

                break;

            case R.id.txtv_message:

                startActivity(new Intent(con, Chat_Activity.class));

                break;

        }
    }

}
