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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.siluetas.MainActivity;
import com.example.siluetas.R;
import com.example.siluetas.model.Score;
import com.google.firebase.auth.FirebaseAuth;
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
TextView nombre,edad,pais,mail;
    //layout
    private ListView lis;

    //firebase
    private FirebaseDatabase bd;
    private DatabaseReference tabla;

    //Adaptador
    private ArrayAdapter<CharSequence> sinDatos;
String userid;

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
        nombre = (TextView) root.findViewById(R.id.tVAreapEL);
        edad = (TextView) root.findViewById(R.id.tVDrpEL);
        pais = (TextView) root.findViewById(R.id.tVNombrepEL);
        mail = (TextView) root.findViewById(R.id.tVFechapEL);
        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
//conexion con firebase
        bd = FirebaseDatabase.getInstance();
        //Obtencion de la tabla
        //se colocala el nombre de la coleccion
        tabla = bd.getReference("users");

        //Es el texto que mostrara la lista si no hay datos
        sinDatos = ArrayAdapter.createFromResource(getActivity(),R.array.sinDatos, android.R.layout.simple_list_item_1);

        lis = root.findViewById(R.id.lVListascore);
        tabla.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //dataSnapshot es la colleccion de los datos
                if(dataSnapshot.exists()){


                    //Como son mas de un dato, hay que separarlo
                    //para ello se usa getChilderen
                    for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                        String s1 =  dataSnapshot1.child("uuid").getValue().toString();
                        //objeto donde se almacenaran los datos
                        if (s1.equals(userid)){
                            nombre.setText(dataSnapshot1.child("nombre").getValue().toString());
                            edad.setText(dataSnapshot1.child("edad").getValue().toString());
                            pais.setText(dataSnapshot1.child("pais").getValue().toString());
                            mail.setText(dataSnapshot1.child("email").getValue().toString());
                        }


                    }



                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                System.out.println("The read failed: " + databaseError.getCode());
                lis.setAdapter(sinDatos);

            }

        });



        return root;
    }


}
