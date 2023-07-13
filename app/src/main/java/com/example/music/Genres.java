package com.example.music;

import android.app.Activity;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Genres extends Fragment {
    View view;
    TextView genres;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_genres, container, false);
        Activity activity = getActivity();
        assert activity != null;
        genres = view.findViewById(R.id.tvgenres);
        genres.setTextColor(ContextCompat.getColor(getActivity(),R.color.Green));
        return  view;
    }
}