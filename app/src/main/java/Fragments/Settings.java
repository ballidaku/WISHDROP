package Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.sharan.wishdrop.R;

/**
 A simple {@link Fragment} subclass. */
public class Settings extends Fragment
{

    SeekBar  seekbar_range;
    TextView txtv_seekbarvalue;

    public Settings()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        setUpIds(view);

        seekbar_range.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            int progress = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser)
            {
                progress = progresValue;
                txtv_seekbarvalue.setText(""+progress+" km");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                txtv_seekbarvalue.setText(""+progress+" km");
            }
        });


        return view;
    }

    private void setUpIds(View view)
    {
        seekbar_range = (SeekBar) view.findViewById(R.id.seekbar_range);
        txtv_seekbarvalue = (TextView) view.findViewById(R.id.txtv_seekbarvalue);

    }

}
