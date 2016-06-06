package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sharan.wishdrop.R;

import java.util.ArrayList;

import Fragments.Profile;

/**
 Created by sharan on 2/6/16. */
public class Profile_Images_Adapter extends RecyclerView.Adapter<Profile_Images_Adapter.MyViewHolder>
{

    Context              con;
    ArrayList<Integer>   list;
    Profile profile;

    public Profile_Images_Adapter(Context con, ArrayList<Integer> list, Profile profile)
    {
        this.con = con;
        this.list = list;
        this.profile=profile;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_otherprofile_image, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position)
    {
        if (list.size() - 1 >= position)
        {
            holder.imgv_otherprofile_image.setImageResource(list.get(position));
            holder.imgv_delete.setVisibility(View.VISIBLE);
            holder.imgv_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    profile.deleteImage(position);
                }
            });
        }
        else
        {
            holder.imgv_otherprofile_image.setImageResource(R.mipmap.add_image);
            holder.imgv_delete.setVisibility(View.GONE);
            holder.imgv_otherprofile_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    profile.addImage();
                }
            });
        }

    }

    @Override
    public int getItemCount()
    {
        return list.size() + 1;
    }

    public void addImage(ArrayList<Integer> list)
    {
        this.list = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imgv_otherprofile_image, imgv_delete;

        public MyViewHolder(View view)
        {
            super(view);
            imgv_otherprofile_image = (ImageView) view.findViewById(R.id.imgv_otherprofile_image);
            imgv_delete = (ImageView) view.findViewById(R.id.imgv_delete);
        }
    }




}
