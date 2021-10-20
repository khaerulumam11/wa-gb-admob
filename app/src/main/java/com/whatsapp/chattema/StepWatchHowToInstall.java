package com.whatsapp.chattema;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class StepWatchHowToInstall extends Fragment {



    public StepWatchHowToInstall() {
        // Required empty public constructor
    }


    public static StepWatchHowToInstall newInstance() {
        return new StepWatchHowToInstall();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_watch, container, false);
        TextView textView = view.findViewById(R.id.text);
        AppCompatButton compatButton = view.findViewById(R.id.button);

        String string = "Untuk melihat tutorial cara install WA-Tema, silahkan tonton video tersebut di youtube dengan menekan tombol dibawah ini";
        textView.setText(string);
        compatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://youtu.be/8saD3QxewVo";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        return view;
    }


}
