package com.whatsapp.chattema;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class StepShow1 extends Fragment {



    public StepShow1() {
        // Required empty public constructor
    }


    public static StepOpening newInstance() {
        return new StepOpening();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_step_show, container, false);
        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.s_3_1);
        return view;
    }


}
