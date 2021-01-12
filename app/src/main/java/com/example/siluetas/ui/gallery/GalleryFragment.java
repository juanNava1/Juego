package com.example.siluetas.ui.gallery;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.siluetas.MainActivity;
import com.example.siluetas.R;
import com.example.siluetas.model.Score;
import com.example.siluetas.ui.slideshow.SlideshowFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class GalleryFragment extends Fragment implements View.OnClickListener{

    private GalleryViewModel galleryViewModel;
    private String[] nombre_animal={"tucan","caballo","perro","gato","conejo","leon","pato","rinoceronte"};
    private String[] sombra_animal={"s_tucan","s_caballo","s_perro","s_gato","s_conejo","s_leon","s_pato","s_rinoceronte"};
    private int intentos=3;
    private Button aceptar;
    private TextView mensaje_intentos,mensaje_cuenta, puntuacion_actual_numero, puntuacion_alta;
    private EditText usuario_animal;
    private int numero_generado=0;
    private ImageView miimagen;
    // puntajes
    private Score score;
    private int puntuacion = 0;
    private String score_shadows, userid;



    MainActivity main;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        Componentes(root);
        main = (MainActivity) getParentFragment().getActivity();
        numero_generado=generaraleatorio();
        establecer_sombra(numero_generado);
        mensaje_intentos.setText("Tiene " + intentos + " intentos");


        score = new Score();
        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        puntuacion_alta = root.findViewById(R.id.puntuacion_alta);
        /**
         * Buscar score en firebase
         */
        Score.findScore(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    puntuacion_alta.setText(snapshot.child("score_shadows").getValue(String.class) + "");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return (root);
    }

    private void Componentes(View root){

        EditTextComponent(root);
        Botones(root);
        TextViewComponent(root);
        ImageViewComponent(root);
    }

    private  void  EditTextComponent(View root){
        usuario_animal = root.findViewById(R.id.txtAnimal);
    }

    private void Botones(View root){
        aceptar = root.findViewById(R.id.btnAceptar);

        aceptar.setOnClickListener(this);
    }

    private void TextViewComponent(View root){
        mensaje_intentos = root.findViewById(R.id.lblIntentos);
        mensaje_cuenta = root.findViewById(R.id.lblcuenta);
        puntuacion_actual_numero = root.findViewById(R.id.puntuacion_actual_numero);
    }

    private void ImageViewComponent(View root){
        miimagen = root.findViewById(R.id.IMVanimal);
    }


    @Override
    public void onClick(View v) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference();
        String nombre=usuario_animal.getText().toString().toLowerCase();
        if(nombre.equals(nombre_animal[numero_generado])){
            establecer_animal(numero_generado);
            //      Aumentar puntuación en 1 y situarlo en la etiqueta
            puntuacion++;
            puntuacion_actual_numero.setText( "" + puntuacion);

            esperar();
        }else {
            Toast.makeText(main.getApplicationContext(),"Ese no es el animal :c", Toast.LENGTH_SHORT).show();
            intentos=intentos-1;
            mensaje_intentos.setText("Tiene " + intentos + " intentos");
            usuario_animal.setText("");
        }if(intentos==0){
            //      Guardar en firebase
            score_shadows = puntuacion_actual_numero.getText().toString();
            //score.setScore_shadows(score_shadows);
            //score.setUserid(userid);
            //score.update(GalleryFragment.this.getContext());
            myref.child("scores").child(userid).child("score_shadows").setValue(score_shadows);

            //main.finish();
        }
    }

    public void esperar(){
      new CountDownTimer(5000,1000){

          @Override
          public void onTick(long millisUntilFinished) {
              mensaje_cuenta.setText("Generando en " + (millisUntilFinished/1000));
          }

          @Override
          public void onFinish() {
              numero_generado=generaraleatorio();
              establecer_sombra(numero_generado);
              mensaje_cuenta.setText("");
              usuario_animal.setText("");
          }
      }.start();
    }

    private void establecer_sombra(int numero){
        int id = getResources().getIdentifier(sombra_animal[numero], "drawable", main.getPackageName());
        miimagen.setImageResource(id);
    }

    private void establecer_animal(int numero){
        int id = getResources().getIdentifier(nombre_animal[numero], "drawable", main.getPackageName());
        miimagen.setImageResource(id);
    }


    private int generaraleatorio(){
        return (int)(Math.random()*nombre_animal.length);
    }

}