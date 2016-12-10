package com.example.android.dtuguide;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class TimeTableFragment extends Fragment {


    public TimeTableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(R.drawable.time_table_error);
        //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

//        TextView textView = new TextView(getActivity());
//        textView.setText("TimeTable Will be updated Soon... ");
        return imageView;
    }

}
