package com.example.siluetas.ui.encuentra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.siluetas.R;

public class EncuentraFragment extends Fragment {

    private EncuentraViewModel encuentraViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        encuentraViewModel =
                ViewModelProviders.of(this).get(EncuentraViewModel.class);
        View root = inflater.inflate(R.layout.fragment_encuentra, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        encuentraViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}