package com.example.siluetas.ui.operaciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.siluetas.Fragment.dialogGO;
import com.example.siluetas.MainActivity;
import com.example.siluetas.R;
import com.example.siluetas.ui.RestaActivity;
import com.example.siluetas.ui.SumaActivity;
import com.example.siluetas.ui.operaciones.OperacionesViewModel;
import com.example.siluetas.ui.operaciones.OperacionesFragment;

public class OperacionesFragment extends Fragment {
    FragmentTransaction transaction;
    Fragment fragmentGO;

    private static ImageView suma, resta;
    private OperacionesViewModel operacionesViewModel;
    MainActivity main;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        operacionesViewModel =
                ViewModelProviders.of(this).get(OperacionesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_operaciones, container, false);
        main = (MainActivity) getParentFragment().getActivity();


        imagenSetup(root);


        return root;
    }

    public void  imagenSetup(View root){
        suma= root.findViewById(R.id.ivsuma);
        resta= root.findViewById(R.id.ivresta);

        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), SumaActivity.class);
                startActivity(intent);
            }
        });

        resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), RestaActivity.class);
                startActivity(intent);
            }
        });
    }


}