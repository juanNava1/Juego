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
import androidx.transition.Slide;

import com.example.siluetas.LoginActivity;
import com.example.siluetas.MainActivity;
import com.example.siluetas.R;
import com.example.siluetas.databinding.FragmentSlideshowBinding;
import com.example.siluetas.model.Score;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SlideshowFragment extends Fragment implements View.OnClickListener {

    private FragmentSlideshowBinding binding;
    private String[] animales = {"leon", "gato", "oso", "mono", "puerquito", "elefante"};
    private int puntuacion = 0;
    private String score_sounds, userid;
    private int puntuacion_mayor_actual;
    private Score score;
    private String sonido;
    private int numero;

    MainActivity main;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel = ViewModelProviders.of(this).get(SlideshowViewModel.class);
        binding = FragmentSlideshowBinding.inflate(getLayoutInflater());
        main = (MainActivity) getParentFragment().getActivity();
        numero = generarAleatorio();
        sonido = animales[numero];
        score = new Score();
        userid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        /**
         * Buscar score en firebase
         */
        Score.findScore(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    binding.puntuacionAlta.setText(snapshot.child("score_sounds").getValue(String.class) + "");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.leon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sonido == "leon") {
                    puntuacion++;
                    binding.puntuacionActualNumero.setText("" + puntuacion);
                    Toast.makeText(main.getApplicationContext(), "¡Perfecto! :D", Toast.LENGTH_SHORT).show();
                    animalGenerate();
                } else {
                    Toast.makeText(main.getApplicationContext(), "Error, juego terminado", Toast.LENGTH_SHORT).show();
                    score_sounds = binding.puntuacionActualNumero.getText().toString();
                    userid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                    score.setScore_sounds(score_sounds);
                    score.setUserid(userid);
                    score.insert(SlideshowFragment.this.getContext());
                }
            }
        });
        binding.gato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sonido == "gato") {
                    puntuacion += 1;
                    binding.puntuacionActualNumero.setText("" + puntuacion);
                    Toast.makeText(main.getApplicationContext(), "¡Perfecto! :D", Toast.LENGTH_SHORT).show();
                    animalGenerate();
                } else {
                    Toast.makeText(main.getApplicationContext(), "Error, juego terminado", Toast.LENGTH_SHORT).show();
                    score_sounds = binding.puntuacionActualNumero.getText().toString();
                    score.setScore_sounds(score_sounds);
                    score.setUserid(userid);
                    score.insert(SlideshowFragment.this.getContext());
                }
            }
        });
        binding.oso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sonido == "oso") {
                    puntuacion += 1;
                    binding.puntuacionActualNumero.setText("" + puntuacion);
                    Toast.makeText(main.getApplicationContext(), "¡Perfecto! :D", Toast.LENGTH_SHORT).show();
                    animalGenerate();
                } else {
                    Toast.makeText(main.getApplicationContext(), "Error, juego terminado", Toast.LENGTH_SHORT).show();
                    score_sounds = binding.puntuacionActualNumero.getText().toString();
                    score.setScore_sounds(score_sounds);
                    score.setUserid(userid);
                    score.insert(SlideshowFragment.this.getContext());
                }
            }
        });
        binding.mono.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sonido == "mono") {
                    puntuacion += 1;
                    binding.puntuacionActualNumero.setText("" + puntuacion);
                    Toast.makeText(main.getApplicationContext(), "¡Perfecto! :D", Toast.LENGTH_SHORT).show();
                    animalGenerate();
                } else {
                    Toast.makeText(main.getApplicationContext(), "Error, juego terminado", Toast.LENGTH_SHORT).show();
                    score_sounds = binding.puntuacionActualNumero.getText().toString();
                    score.setScore_sounds(score_sounds);
                    score.setUserid(userid);
                    score.insert(SlideshowFragment.this.getContext());
                }
            }
        });
        binding.puerquito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sonido == "puerquito") {
                    puntuacion += 1;
                    binding.puntuacionActualNumero.setText("" + puntuacion);
                    Toast.makeText(main.getApplicationContext(), "¡Perfecto! :D", Toast.LENGTH_SHORT).show();
                    animalGenerate();
                } else {
                    Toast.makeText(main.getApplicationContext(), "Error, juego terminado", Toast.LENGTH_SHORT).show();
                    score_sounds = binding.puntuacionActualNumero.getText().toString();
                    score.setScore_sounds(score_sounds);
                    score.setUserid(userid);
                    score.insert(SlideshowFragment.this.getContext());
                }
            }
        });
        binding.elefante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sonido == "elefante") {
                    puntuacion += 1;
                    binding.puntuacionActualNumero.setText("" + puntuacion);
                    Toast.makeText(main.getApplicationContext(), "¡Perfecto! :D", Toast.LENGTH_SHORT).show();
                    animalGenerate();
                } else {
                    Toast.makeText(main.getApplicationContext(), "Error, juego terminado", Toast.LENGTH_SHORT).show();
                    score_sounds = binding.puntuacionActualNumero.getText().toString();
                    score.setScore_sounds(score_sounds);
                    score.setUserid(userid);
                    score.insert(SlideshowFragment.this.getContext());
                }
            }
        });
        binding.imageButtonSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound(view, sonido);
            }
        });
        return binding.getRoot();
    }

    public void playSound(View v, String sonido) {
        //Toast.makeText(main.getApplicationContext(), sonido, Toast.LENGTH_SHORT).show();
        MediaPlayer mediaplayer;
        switch (sonido) {
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

    public String animalGenerate() {
        numero = generarAleatorio();
        sonido = animales[numero];
        return sonido;
    }

    private int generarAleatorio() {
        return (int) (Math.random() * animales.length);
    }

    @Override
    public void onClick(View v) {

    }
}