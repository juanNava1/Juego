package com.example.siluetas.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siluetas.Fragment.DialogLevel;
import com.example.siluetas.Fragment.dialogGO;
import com.example.siluetas.R;
import com.example.siluetas.ui.slideshow.SlideshowFragment;

public class SumaActivity extends AppCompatActivity {
public Integer num1, num2, resultado, vidas,RONDA,puntos;
public TextView pts, marcador1, marcador2,marcavidas;
public ImageView numero1, numero2,vida1,vida2,vida3;
public Button btncom;
public EditText input;

FragmentTransaction transaction;
Fragment fragmentGO, fragmentL;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suma);
        pts = findViewById(R.id.tvpuntos);
        marcavidas = findViewById(R.id.tvvidas);
        marcador1 = findViewById(R.id.tvnum1);
        marcador2 = findViewById(R.id.tvnum2);
        numero1 = findViewById(R.id.ivnum1);
        numero2 = findViewById(R.id.ivnum2);
        vida1 = findViewById(R.id.imageViewvida1);
        vida2 = findViewById(R.id.imageViewvida2);
        vida3 = findViewById(R.id.imageViewvida3);

        btncom = findViewById(R.id.btncom);
        input = findViewById(R.id.etNumber);
        RONDA = 10;
        vidas = 3;
        puntos=0;

        marcavidas.setText(vidas+"");

        juego();
        btncom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ronda();
            }
        });


    }

    public static int crearnum1() {
        Integer num1;

        num1 = (int) (Math.random() * 10) + 1;;

        return num1;
    }

    public static int crearnum2() {
        Integer num2;
        num2 = (int) (Math.random() * 10) + 1;;

        return num2;
    }

    public static int suma(Integer num1, Integer num2) {
       Integer res;

       res = num1 + num2;

        return res;
    }

    public void ronda(){
        String respuesta;
        respuesta="";
        Integer res;
        //res = String.valueOf(resultado);
        respuesta = input.getText().toString();
        //int numEntero = 4;
        //String numCadena= String.valueOf(numEntero);
        //String numCadena = "1";
        //int numEntero = Integer.parseInt(numCadena);
        res = Integer.parseInt(respuesta);
        if (respuesta.isEmpty()){
            Toast.makeText(this, "Ingresa tu resultado",Toast.LENGTH_SHORT).show();
        }else{
            if (resultado == res){

                puntos=puntos + 10;
                pts.setText(puntos+"");
                RONDA = RONDA - 1;
                Toast.makeText(this, "Bien hecho",Toast.LENGTH_LONG).show();
                limpiar();
                juego();

            }else{
                vidas = vidas - 1;
                marcavidas.setText(vidas+"");
                RONDA = RONDA - 1;
                puntos=puntos - 5;
                pts.setText(puntos+"");
                Toast.makeText(this, "Vida perdida",Toast.LENGTH_LONG).show();
                pierdeVida(vidas);
                limpiar();
                juego();

            }

        }




    }

    public  void limpiar() {

        input.setText("");
    }


    public void juego(){
        String txtnum1, txtnum2;

        if (vidas==0){
            Toast.makeText(this, "Juego Terminado",Toast.LENGTH_SHORT).show();
        }else {
            if (RONDA == 0){
                Toast.makeText(this, "Felicidades Pasaste el nivel de SUMAS",Toast.LENGTH_SHORT).show();
                transaction = getSupportFragmentManager().beginTransaction();
                fragmentL = getSupportFragmentManager().findFragmentByTag("dialogganar");
                transaction.addToBackStack(null);
                win();
            }else{
                if (vidas > 0 && RONDA > 0){
                    num1 = crearnum1();
                    txtnum1 = num1 + "";
                    num2 = crearnum2();
                    txtnum2 = num2 + "";
                    resultado = suma(num1,num2);
                    marcador1.setText(txtnum1);
                    marcador2.setText(txtnum2);
                    asignaImagen1(num1);
                    asignaImagen2(num2);
                }
            }
        }

    }

    private void win() {
        DialogFragment ganar = DialogLevel.newInstance();
        ganar.show(transaction, "dialogganar");

    }

    public void asignaImagen1(Integer txtnum1){

        if(txtnum1==1){

            numero1.setImageDrawable(getResources().getDrawable(R.drawable.uno));
        }

        if (txtnum1==2){
            numero1.setImageDrawable(getResources().getDrawable(R.drawable.dos));
        }


        if(txtnum1==3){

            numero1.setImageDrawable(getResources().getDrawable(R.drawable.tres));
        }

        if (txtnum1==4){
            numero1.setImageDrawable(getResources().getDrawable(R.drawable.cuatro));
        }


            if(txtnum1==5){

                numero1.setImageDrawable(getResources().getDrawable(R.drawable.cinco));
            }

            if (txtnum1==6){
                numero1.setImageDrawable(getResources().getDrawable(R.drawable.seis));
            }

        if(txtnum1==7){

            numero1.setImageDrawable(getResources().getDrawable(R.drawable.siete));
        }

        if (txtnum1==8){
            numero1.setImageDrawable(getResources().getDrawable(R.drawable.ocho));
        }

        if(txtnum1==9){

            numero1.setImageDrawable(getResources().getDrawable(R.drawable.nueve));
        }

        if (txtnum1==10){
            numero1.setImageDrawable(getResources().getDrawable(R.drawable.diez));
        }

    }


    public void asignaImagen2(Integer txtnum2){

        if(txtnum2==1){

            numero2.setImageDrawable(getResources().getDrawable(R.drawable.uno));
        }

        if (txtnum2==2){
            numero2.setImageDrawable(getResources().getDrawable(R.drawable.dos));
        }


        if(txtnum2==3){

            numero2.setImageDrawable(getResources().getDrawable(R.drawable.tres));
        }

        if (txtnum2==4){
            numero2.setImageDrawable(getResources().getDrawable(R.drawable.cuatro));
        }


        if(txtnum2==5){

            numero2.setImageDrawable(getResources().getDrawable(R.drawable.cinco));
        }

        if (txtnum2==6){
            numero2.setImageDrawable(getResources().getDrawable(R.drawable.seis));
        }

        if(txtnum2==7){

            numero2.setImageDrawable(getResources().getDrawable(R.drawable.siete));
        }

        if (txtnum2==8){
            numero2.setImageDrawable(getResources().getDrawable(R.drawable.ocho));
        }

        if(txtnum2==9){

            numero2.setImageDrawable(getResources().getDrawable(R.drawable.nueve));
        }

        if (txtnum2==10){
            numero2.setImageDrawable(getResources().getDrawable(R.drawable.diez));
        }

    }

    public void pierdeVida(Integer vida){
        if (vida==2){
            vida1.setVisibility(View.INVISIBLE);
        }

        if (vida==1){
            vida2.setVisibility(View.INVISIBLE);
        }

        if (vida==0){
            vida3.setVisibility(View.INVISIBLE);

            transaction = getSupportFragmentManager().beginTransaction();
            fragmentGO = getSupportFragmentManager().findFragmentByTag("dialog");
            transaction.addToBackStack(null);
            cerrar();



        }

    }

    public void cerrar(){
        DialogFragment gameover = dialogGO.newInstance();
        gameover.show(transaction, "dialog");
        /*Intent intent = new Intent(this, SlideshowFragment.class);
        startActivity(intent);*/




    }
}