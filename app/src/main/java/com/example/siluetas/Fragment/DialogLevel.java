package com.example.siluetas.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.siluetas.R;
import com.example.siluetas.ui.operaciones.OperacionesFragment;
import com.example.siluetas.ui.slideshow.SlideshowFragment;


public class DialogLevel extends DialogFragment {

    ImageButton salir;
    public DialogLevel() {
        // Required empty public constructor
    }


    public static DialogLevel newInstance() {
        DialogLevel fragment = new DialogLevel();



        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root;
        root = inflater.inflate(R.layout.fragment_dialog_level, container, false);
        salir = root.findViewById(R.id.btnSalirlevel);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), OperacionesFragment.class);
                startActivity(intent);

            }
        });
        return root;
    }
}