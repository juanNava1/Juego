package com.example.siluetas.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.siluetas.Fragment.DialogLevel;
import com.example.siluetas.Fragment.dialogGO;
import com.example.siluetas.R;
import com.example.siluetas.model.Score;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RestaActivity extends AppCompatActivity {
    public Integer num1, num2, resultado, vidas,RONDA,puntos;
    public TextView pts, puntuacion_alta;
    public ImageView numero1, numero2,vida1,vida2,vida3;
    public Button btncom;
    public EditText input;
    // puntajes
    private Score score;
    private String score_add, userid;
    FragmentTransaction transaction;
    Fragment fragmentGO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resta);
        pts = findViewById(R.id.tvpuntosMul);


        numero1 = findViewById(R.id.ivnum1mul);
        numero2 = findViewById(R.id.ivnum2mul);
        vida1 = findViewById(R.id.imageViewvida1mul);
        vida2 = findViewById(R.id.imageViewvida2mul);
        vida3 = findViewById(R.id.imageViewvida3mul);

        btncom = findViewById(R.id.btncommul);
        input = findViewById(R.id.etNumberMul);
        RONDA = 10;
        vidas = 3;
        puntos=0;

        score = new Score();
        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        puntuacion_alta = findViewById(R.id.puntuacion_alta);
        /**
         * Buscar score en firebase
         */
        Score.findScore(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    puntuacion_alta.setText(snapshot.child("score_res").getValue(String.class) + "");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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

    public static int multiplicar(Integer num1, Integer num2) {
        Integer res;

        res = num1 * num2;

        return res;
    }

    public void ronda(){
        String respuesta;

        Integer res;

        respuesta = input.getText().toString();
            try {
                res = Integer.parseInt(respuesta);

            }catch (Exception e){
                res=-1;
            }

        if ((respuesta ==null) || respuesta.isEmpty()){
            Toast.makeText(this, "Ingresa tu resultado",Toast.LENGTH_SHORT).show();
        }else{
            if (resultado == res){

                puntos=puntos + 1;
                pts.setText(puntos+"");
                RONDA = RONDA - 1;
                Toast.makeText(this, "Bien hecho",Toast.LENGTH_LONG).show();
                limpiar();
                juego();
            }else{
                vidas = vidas - 1;

                RONDA = RONDA - 1;
                pts.setText(puntos+"");
                Toast.makeText(this, "Vida perdida",Toast.LENGTH_LONG).show();
                pierdeVida(vidas);
                limpiar();
                juego();
            }

        }




    }

    private void limpiar() {
        input.setText("");
    }


    public void juego(){
        String txtnum1, txtnum2;

        if (vidas==0){
            Toast.makeText(this, "Juego Terminado",Toast.LENGTH_SHORT).show();
        }else {
            if (RONDA == 0){
                Toast.makeText(this, "Felicidades Pasaste el nivel de MULTIPLICAR",Toast.LENGTH_SHORT).show();
                transaction = getSupportFragmentManager().beginTransaction();
                fragmentGO = getSupportFragmentManager().findFragmentByTag("dialog");
                transaction.addToBackStack(null);
                win();

            }else{
                if (vidas > 0 && RONDA > 0){
                    num1 = crearnum1();

                    num2 = crearnum2();

                    resultado = multiplicar(num1,num2);

                    asignaImagen1(num1);
                    asignaImagen2(num2);
                }
            }
        }

    }

    private void win() {
        DialogFragment ganar = DialogLevel.newInstance();
        ganar.show(transaction, "dialog");


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
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myref = database.getReference();
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

            score_add = puntos.toString();
            myref.child("scores").child(userid).child("score_res").setValue(score_add);
            cerrar();



        }

    }

    public void cerrar(){
        DialogFragment gameover = dialogGO.newInstance();
        gameover.show(transaction, "dialog");





    }

}