package com.example.music;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HomePage extends AppCompatActivity {
    TextView hpArtist,hpAlbum,hpFolder,hpGenres;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        hpArtist = findViewById(R.id.tvartist);
        hpAlbum =  findViewById(R.id.tvalbum);
        hpFolder = findViewById(R.id.tvfolders);
        hpGenres = findViewById(R.id.tvgenres);
        hpArtist.setOnClickListener(view -> {
            hpArtist.setTextColor(Color.GREEN);
            replaceArtist(new Artists());

        });
        hpAlbum.setOnClickListener(view -> {
            hpAlbum.setTextColor(Color.GREEN);
            replaceAlbum(new Album());
        });
        hpFolder.setOnClickListener(view -> {
            hpFolder.setTextColor(Color.GREEN);
            replaceFolder(new Folders());
        });
        hpGenres.setOnClickListener(view -> {
            hpGenres.setTextColor(Color.GREEN);
            replaceGenres(new Genres());
        });

    }

    public void replaceGenres(Genres genres) {
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments,genres);
        fragmentTransaction.commit();
    }

    public void replaceFolder(Folders folders) {
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments,folders);
        fragmentTransaction.commit();
    }

    public void replaceAlbum(Album album) {
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments,album);
        fragmentTransaction.commit();
    }

    public void replaceArtist(Artists artists) {
        FragmentManager fragmentManager =getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragments,artists);
        fragmentTransaction.commit();
    }

}