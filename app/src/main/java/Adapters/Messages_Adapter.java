package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.ameba.sharan.wishdrop.R;

import com.ameba.sharan.wishdrop.Chat.Chat_Activity;

/**
 Created by sharan on 27/5/16. */
public class Messages_Adapter extends BaseAdapter
{
    Context                            con;
//    ArrayList<HashMap<String, String>> list;


    public Messages_Adapter(Context con/*, ArrayList<HashMap<String, String>> list*/)
    {
        this.con = con;
//        this.list = list;
    }

    @Override
    public int getCount()
    {
        // TODO Auto-generated method stub
        return 10/*list.size()*/;
    }

    @Override
    public Object getItem(int arg0)
    {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(final int position, View row, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        row = inflater.inflate(R.layout.custom_messages, parent, false);

     /*   ImageView imgv_personal_pic = (ImageView) row.findViewById(R.id.imgv_personal_pic);
        ImageView imgv_call         = (ImageView) row.findViewById(R.id.imgv_call);
        ImageView imgv_email        = (ImageView) row.findViewById(R.id.imgv_email);
        ImageView imgv_share        = (ImageView) row.findViewById(R.id.imgv_share);

        TextView txtv_name        = (TextView) row.findViewById(R.id.txtv_name);
        TextView txtv_city        = (TextView) row.findViewById(R.id.txtv_city);
        TextView txtv_profession  = (TextView) row.findViewById(R.id.txtv_profession);
        TextView txtv_address     = (TextView) row.findViewById(R.id.txtv_address);
        TextView txtv_description = (TextView) row.findViewById(R.id.txtv_description);*/


        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                con.startActivity(new Intent(con, Chat_Activity.class));
            }
        });


        return row;
    }



}