package Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.sharan.wishdrop.R;

import Adapters.Friends_Adapter;
import Adapters.InviteFriends_Adapter;

public class InviteFriends extends Fragment
{
    Context  con;
    ListView listv_invitefriends;

    public InviteFriends()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        con = getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invite_friends, container, false);

        setUpIds(view);
        setData();

        return view;
    }

    private void setUpIds(View view)
    {
        listv_invitefriends = (ListView) view.findViewById(R.id.listv_invitefriends);
    }

    private void setData()
    {
        InviteFriends_Adapter adapter = new InviteFriends_Adapter(con);

        listv_invitefriends.setAdapter(adapter);
    }

}
