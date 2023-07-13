package com.example.music;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public class Folders extends Fragment {
    View view;
    TextView folders;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_folders, container, false);
        folders = view.findViewById(R.id.tvfolders);
        folders.setTextColor(ContextCompat.getColor(requireActivity(),R.color.Green));
        return  view;
    }
}