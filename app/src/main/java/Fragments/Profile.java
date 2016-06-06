package Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sharan.wishdrop.OtherProfile;
import com.example.sharan.wishdrop.R;

import java.util.ArrayList;

import Adapters.OtherProfile_Images_Adapter;
import Adapters.Profile_Images_Adapter;
import HelperClasses.MyUtil;

/**
 A simple {@link Fragment} subclass. */
public class Profile extends Fragment implements View.OnClickListener
{
    Context con;
    MyUtil myUtil = new MyUtil();

    RecyclerView recycleview_otherprofile_images;

    Profile_Images_Adapter adapter;

    ArrayList<Integer> list;

    Profile profile;

    public Profile()
    {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        con = getActivity();
        profile=this;

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        setUpIds(view);

        return view;
    }

    private void setUpIds(View view)
    {
        ImageView imgv_blur = (ImageView) view.findViewById(R.id.imgv_blur);
        //        myUtil.blurImageInBackground(con, imgv_blur);

        //        view.findViewById(R.id.imgv_add).setOnClickListener(this);

        recycleview_otherprofile_images = (RecyclerView) view.findViewById(R.id.recycleview_otherprofile_images);

        LinearLayoutManager layoutManager = new LinearLayoutManager(con);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleview_otherprofile_images.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        /*list.add(R.mipmap.img_one);
        list.add(R.mipmap.img_two);
        list.add(R.mipmap.img_three);
        list.add(R.mipmap.img_one);
        list.add(R.mipmap.img_two);
        list.add(R.mipmap.img_three);*/

        adapter = new Profile_Images_Adapter(con, list,profile);
        recycleview_otherprofile_images.setAdapter(adapter);

    }

    int count = 0;

    public void addImage()
    {
        if (count == 0)
        {
            list.add(R.mipmap.img_one);
            adapter.addImage(list);
            adapter.notifyDataSetChanged();
        }
        else if (count == 1)
        {
            list.add(R.mipmap.img_two);
            adapter.addImage(list);
            adapter.notifyDataSetChanged();
        }
        else if (count == 2)
        {
            list.add(R.mipmap.img_three);
            adapter.addImage(list);
            adapter.notifyDataSetChanged();
        }
        else if (count == 3)
        {
            list.add(R.mipmap.img_two);
            adapter.addImage(list);
            adapter.notifyDataSetChanged();
        }
        else if (count == 4)
        {
            list.add(R.mipmap.img_three);
            adapter.addImage(list);
            adapter.notifyDataSetChanged();
        }
        else if (count == 5)
        {
            list.add(R.mipmap.img_two);
            adapter.addImage(list);
            adapter.notifyDataSetChanged();
        }

        recycleview_otherprofile_images.scrollToPosition(list.size());

        count++;

    }

    public void deleteImage(int position)
    {
        list.remove(position);
        adapter.addImage(list);
        adapter.notifyDataSetChanged();
    }





    @Override
    public void onClick(View v)
    {
       /* switch (v.getId())
        {
            case R.id.imgv_add:



                break;

        }*/
    }

}
