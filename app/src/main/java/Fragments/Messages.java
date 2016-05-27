package Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sharan.wishdrop.R;

import Adapters.Messages_Adapter;

public class Messages extends Fragment
{
    Context  con;
    ListView listv_messages;

    public Messages()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_messages, container, false);

        con=getActivity();

        listv_messages = (ListView) v.findViewById(R.id.listv_messages);

        setData();

        return v;
    }

    private void setData()
    {
        Messages_Adapter adapter = new Messages_Adapter(con);

        listv_messages.setAdapter(adapter);
    }

}
