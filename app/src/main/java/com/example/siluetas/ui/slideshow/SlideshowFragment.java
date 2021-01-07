package com.example.siluetas.ui.slideshow;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.siluetas.LoginActivity;
import com.example.siluetas.MainActivity;
import com.example.siluetas.R;
import com.example.siluetas.RegisterActivity;
import com.example.siluetas.databinding.ActivityLoginBinding;
import com.example.siluetas.databinding.FragmentSlideshowBinding;
import com.example.siluetas.model.Score;

import static com.example.siluetas.R.raw.gato;

public class SlideshowFragment extends Fragment implements View.OnClickListener {

    private FragmentSlideshowBinding binding;
    private String[] animales = { "leon", "gato", "oso", "mono", "puerquito", "elefante" };
    private int puntuacion = 0;
    private String high_puntuation;
    private int puntuacion_mayor_actual;
    private Score score;

    MainActivity main;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel = ViewModelProviders.of(this).get(SlideshowViewModel.class);
        binding = FragmentSlideshowBinding.inflate(getLayoutInflater());
        main = (MainActivity) getParentFragment().getActivity();
        int numero = generarAleatorio();
        final String sonido = animales[numero];

        score = new Score();

        binding.leon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( sonido == "leon" ){
                    puntuacion++;
                    binding.puntuacionActualNumero.setText("" + puntuacion);
                    Toast.makeText(main.getApplicationContext(), "¡Perfecto! :D", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(main.getApplicationContext(), "¡Error! Juego terminado", Toast.LENGTH_SHORT).show();
                    high_puntuation = binding.puntuacionActualNumero.getText().toString();
                    if( score.getScore_sounds() == null ){
                        score.setScore_sounds(high_puntuation);
                        score.insert(SlideshowFragment.this.getContext());
                    }
                }
            }
        });
        binding.gato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( sonido == "gato" ){
                    puntuacion += 1;
                    binding.puntuacionActualNumero.setText("" + puntuacion);
                    Toast.makeText(main.getApplicationContext(), "¡Perfecto! :D", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(main.getApplicationContext(), "¡Error! Juego terminado", Toast.LENGTH_SHORT).show();
                    high_puntuation = binding.puntuacionActualNumero.getText().toString();
                    if( score.getScore_sounds() == null ){
                        score.setScore_sounds(high_puntuation);
                        score.insert(SlideshowFragment.this.getContext());
                    }
                }
            }
        });
        binding.oso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( sonido == "oso" ){
                    puntuacion += 1;
                    binding.puntuacionActualNumero.setText("" + puntuacion);
                    Toast.makeText(main.getApplicationContext(), "¡Perfecto! :D", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(main.getApplicationContext(), "¡Error! Juego terminado", Toast.LENGTH_SHORT).show();
                    high_puntuation = binding.puntuacionActualNumero.getText().toString();
                    if( score.getScore_sounds() == null ){
                        score.setScore_sounds(high_puntuation);
                        score.insert(SlideshowFragment.this.getContext());
                    }
                }
            }
        });
        binding.mono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( sonido == "mono" ){
                    puntuacion += 1;
                    binding.puntuacionActualNumero.setText("" + puntuacion);
                    Toast.makeText(main.getApplicationContext(), "¡Perfecto! :D", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(main.getApplicationContext(), "¡Error! Juego terminado", Toast.LENGTH_SHORT).show();
                    high_puntuation = binding.puntuacionActualNumero.getText().toString();
                    if( score.getScore_sounds() == null ){
                        score.setScore_sounds(high_puntuation);
                        score.insert(SlideshowFragment.this.getContext());
                    }
                }
            }
        });
        binding.puerquito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( sonido == "puerquito" ){
                    puntuacion += 1;
                    binding.puntuacionActualNumero.setText("" + puntuacion);
                    Toast.makeText(main.getApplicationContext(), "¡Perfecto! :D", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(main.getApplicationContext(), "¡Error! Juego terminado", Toast.LENGTH_SHORT).show();
                    high_puntuation = binding.puntuacionActualNumero.getText().toString();
                    if( score.getScore_sounds() == null ){
                        score.setScore_sounds(high_puntuation);
                        score.insert(SlideshowFragment.this.getContext());
                    }
                }
            }
        });
        binding.elefante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( sonido == "elefante" ){
                    puntuacion += 1;
                    binding.puntuacionActualNumero.setText("" + puntuacion);
                    Toast.makeText(main.getApplicationContext(), "¡Perfecto! :D", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(main.getApplicationContext(), "¡Error! Juego terminado", Toast.LENGTH_SHORT).show();
                    high_puntuation = binding.puntuacionActualNumero.getText().toString();
                    if( score.getScore_sounds() == null ){
                        score.setScore_sounds(high_puntuation);
                        score.insert(SlideshowFragment.this.getContext());
                    }
                }
            }
        });
        binding.imageButtonSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(main.getApplicationContext(), sonido, Toast.LENGTH_SHORT).show();
                MediaPlayer mediaplayer;
                switch (sonido){
                    case "leon":
                        mediaplayer = MediaPlayer.create(main.getApplicationContext(), R.raw.leon);
                        mediaplayer.start();
                        break;
                    case "gato":
                        mediaplayer = MediaPlayer.create(main.getApplicationContext(), R.raw.gato);
                        mediaplayer.start();
                        break;
                    case "oso":
                        mediaplayer = MediaPlayer.create(main.getApplicationContext(), R.raw.oso);
                        mediaplayer.start();
                        break;
                    case "mono":
                        mediaplayer = MediaPlayer.create(main.getApplicationContext(), R.raw.mono);
                        mediaplayer.start();
                        break;
                    case "puerquito":
                        mediaplayer = MediaPlayer.create(main.getApplicationContext(), R.raw.puerquito);
                        mediaplayer.start();
                        break;
                    case "elefante":
                        mediaplayer = MediaPlayer.create(main.getApplicationContext(), R.raw.elefante);
                        mediaplayer.start();
                        break;
                }
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {

    }
    private int generarAleatorio(){
        return (int) ( Math.random() * animales.length );
    }
}