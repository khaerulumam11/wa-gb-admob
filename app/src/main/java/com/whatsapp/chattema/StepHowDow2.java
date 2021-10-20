package com.whatsapp.chattema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class StepHowDow2 extends Fragment {



    public StepHowDow2() {
        // Required empty public constructor
    }


    public static StepHowDow2 newInstance() {
        return new StepHowDow2();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_how_download, container, false);
        ImageView imageView = view.findViewById(R.id.image);
        TextView textView = view.findViewById(R.id.text);

        imageView.setImageResource(R.drawable.hd_2);
        String string = "2. Kemudian pilih tombol next";
        textView.setText(string);

        return view;
    }


}
