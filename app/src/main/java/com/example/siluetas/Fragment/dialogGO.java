package com.example.siluetas.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.siluetas.R;
import com.example.siluetas.ui.RestaActivity;
import com.example.siluetas.ui.operaciones.OperacionesFragment;
import com.example.siluetas.ui.operaciones.OperacionesViewModel;


public class dialogGO extends DialogFragment {

    ImageButton salir;
    public dialogGO() {
        // Required empty public constructor
    }



    public static dialogGO newInstance() {
        dialogGO fragment = new dialogGO();



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
        root = inflater.inflate(R.layout.fragment_dialog_go, container, false);
        salir = root.findViewById(R.id.btnSalir);

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