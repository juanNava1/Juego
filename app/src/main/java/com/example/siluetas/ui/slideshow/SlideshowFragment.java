package com.example.siluetas.ui.slideshow;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import static com.example.siluetas.R.raw.gato;

public class SlideshowFragment extends Fragment implements View.OnClickListener {

    private FragmentSlideshowBinding binding;
    private SlideshowViewModel slideshowViewModel;
    private String[] animales = { "leon", "gato", "oso", "mono", "puerquito", "elefante" };
    private MediaPlayer mediaPlayer;
    MainActivity main;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        binding = FragmentSlideshowBinding.inflate(getLayoutInflater());
        //View view = binding.getRoot();
        main = (MainActivity) getParentFragment().getActivity();

        binding.imageButtonSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Toast.makeText(main.getApplicationContext(),"Ese no es el animal", Toast.LENGTH_SHORT).show();
            }
        });

        return (root);
    }

    @Override
    public void onClick(View v) {

        //MediaPlayer mediaplayer = MediaPlayer.create(main.getApplicationContext(), R.raw.leon);
        //mediaplayer.start();
        //Toast.makeText(getActivity().getContext(),"Sound on", Toast.LENGTH_LONG).show();
    }
}