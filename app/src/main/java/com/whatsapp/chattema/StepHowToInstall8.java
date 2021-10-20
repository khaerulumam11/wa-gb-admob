package com.whatsapp.chattema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class StepHowToInstall8 extends Fragment {



    public StepHowToInstall8() {
        // Required empty public constructor
    }


    public static StepHowToInstall8 newInstance() {
        return new StepHowToInstall8();
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

        String str1 = getString(R.string.s2_4);
        String str2 = getString(R.string.s2_5);

        String tot = str1+"\n"+str2+"\n"+"Masih kurang jelas? Slahkan tonton video penggunaannya di halaman berikutnya\nKlik next";

        imageView.setImageResource(R.drawable.hd_11);
        textView.setText("Cari tema sesuai keinginan mu, misalnya PINK, kemudian pilih seperti terdapat pada gambar");

        return view;
    }


}
