package com.whatsapp.chattema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

public class StepShow3 extends Fragment {



    public StepShow3() {
        // Required empty public constructor
    }


    public static StepShow3 newInstance() {
        return new StepShow3();
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
        imageView.setImageResource(R.drawable.s_3_3);
        return view;
    }


}
