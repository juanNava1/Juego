package com.example.siluetas.ui.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.siluetas.R;
import com.example.siluetas.model.Score;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PerfilFragment extends Fragment {

    private PerfilViewModel perfilViewModel;

    //layout
    private ListView lis;

    //firebase
    private FirebaseDatabase bd;
    private DatabaseReference tabla;

    //Adaptador
    private ArrayAdapter<CharSequence> sinDatos;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        perfilViewModel =
                ViewModelProviders.of(this).get(PerfilViewModel.class);
        View root = inflater.inflate(R.layout.fragment_perfil, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        perfilViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void buscar(String nombreCampo, String valor){

        //para buscar un dato especifico deberemos especificar

        Query consulta = tabla.orderByChild(nombreCampo).equalTo(valor);

        //Obtenemos todos los datos dentro de scores, pues es lo que se definio en tabla
        tabla.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //dataSnapshot es la colleccion de los datos
                if(dataSnapshot.exists()){

                    List<Score> scores = new ArrayList<>();
                    //Como son mas de un dato, hay que separarlo
                    //para ello se usa getChilderen
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                        //objeto donde se almacenaran los datos
                        Score score = dataSnapshot.getValue(Score.class);
                        scores.add(score);

                    }

                    //Verificamos si existenm datos
                    if(!scores.isEmpty()){

                        //convertimos la lista para poder agregarlo al list view
                        ArrayAdapter<Score> adaptadorScore = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,scores);
                        lis.setAdapter(adaptadorScore);

                    }
                    else{

                        lis.setAdapter(sinDatos);

                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                System.out.println("The read failed: " + databaseError.getCode());
                lis.setAdapter(sinDatos);

            }

        });

    }
}
