package com.whatsapp.chattema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class StepHowDow6 extends Fragment {



    public StepHowDow6() {
        // Required empty public constructor
    }


    public static StepHowDow6 newInstance() {
        return new StepHowDow6();
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

        imageView.setImageResource(R.drawable.hd_6);
        String string = "6. Jika video promo telah Anda tonton sampai akhir, maka akan muncul dialog seperti diatas. Maka link donlot sudah otomatis tersalin, anda hanya tinggal mem-paste kannya saja. Selanjutnya buka aplikasi browser Anda kemudian paste kan linknya dan enter";
        textView.setText(string);

        return view;
    }


}
