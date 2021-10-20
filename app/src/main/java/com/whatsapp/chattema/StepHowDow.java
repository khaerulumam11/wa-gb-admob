package com.whatsapp.chattema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class StepHowDow extends Fragment {



    public StepHowDow() {
        // Required empty public constructor
    }


    public static StepHowDow newInstance() {
        return new StepHowDow();
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

        imageView.setImageResource(R.drawable.hd_5);
        String string = "5. Untuk mendapatkan link Donlot, anda akan diminta untuk memutar video promo selama beberapa detik. Klik OK kemudian tonton video tersebut sampai selesai untuk mendapatkan link donlot. Ingat, harus sampai selesai";
        textView.setText(string);

        return view;
    }


}
