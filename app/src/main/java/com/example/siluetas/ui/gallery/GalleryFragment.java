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

import org.w3c.dom.Text;

public class GalleryFragment extends Fragment
        implements View.OnClickListener{

    private GalleryViewModel galleryViewModel;
    private String[] nombre_animal={"tucan","caballo","perro","gato","conejo","leon","pato","rinoceronte"};
    private String[] sombra_animal={"s_tucan","s_caballo","s_perro","s_gato","s_conejo","s_leon","s_pato","s_rinoceronte"};
    private int intentos=3;
    private Button aceptar;
    private TextView mensaje_intentos,mensaje_cuenta;
    private EditText usuario_animal;
    private int numero_generado=0;
    private ImageView miimagen;
    MainActivity main;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        Componentes(root);
        main = (MainActivity) getParentFragment().getActivity();
        numero_generado=generaraleatorio();
        establecer_sombra(numero_generado);
        mensaje_intentos.setText("Tiene " + intentos + " intentos");
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
    }

    private void ImageViewComponent(View root){
        miimagen = root.findViewById(R.id.IMVanimal);
    }


    @Override
    public void onClick(View v) {
        String nombre=usuario_animal.getText().toString().toLowerCase();
        if(nombre.equals(nombre_animal[numero_generado])){
            establecer_animal(numero_generado);
            esperar();
        }else {
            Toast.makeText(main.getApplicationContext(),"Ese no es el animal", Toast.LENGTH_SHORT).show();
            intentos=intentos-1;
            mensaje_intentos.setText("Tiene " + intentos + " intentos");
            usuario_animal.setText("");
        }if(intentos==0){
            main.finish();
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