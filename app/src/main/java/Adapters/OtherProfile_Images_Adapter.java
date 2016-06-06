package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.sharan.wishdrop.R;

import java.util.ArrayList;

/**
 Created by sharan on 2/6/16. */
public class OtherProfile_Images_Adapter extends RecyclerView.Adapter<OtherProfile_Images_Adapter.MyViewHolder>
{

    Context            con;
    ArrayList<Integer> list;

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imgv_otherprofile_image;

        public MyViewHolder(View view)
        {
            super(view);
            imgv_otherprofile_image = (ImageView) view.findViewById(R.id.imgv_otherprofile_image);
        }
    }

    public OtherProfile_Images_Adapter(Context con, ArrayList<Integer> list)
    {
        this.con = con;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_otherprofile_image, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position)
    {
        ;
        holder.imgv_otherprofile_image.setImageResource(list.get(position));

    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }
}
