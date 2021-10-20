package com.whatsapp.chattema;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class StepHowDow3 extends Fragment {



    public StepHowDow3() {
        // Required empty public constructor
    }


    public static StepHowDow3 newInstance() {
        return new StepHowDow3();
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

        imageView.setImageResource(R.drawable.hd_3);
        String string = "3. Anda akan masuk ke halaman preview bagaimana tampilan aplikasi keika nanti berhasil diinstall, lanjut terus kilk Next";
        textView.setText(string);

        return view;
    }


}
