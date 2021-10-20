package com.whatsapp.chattema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class StepHowToInstall3 extends Fragment {



    public StepHowToInstall3() {
        // Required empty public constructor
    }


    public static StepHowToInstall3 newInstance() {
        return new StepHowToInstall3();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_how_download_2, container, false);
        ImageView imageView = view.findViewById(R.id.image);
        ImageView imageView1 = view.findViewById(R.id.image2);

        TextView textView = view.findViewById(R.id.text);

        imageView.setImageResource(R.drawable.s_3);
        imageView1.setImageResource(R.drawable.s_4);

        textView.setText(R.string.s2_3);

        return view;
    }


}
