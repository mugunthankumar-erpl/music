package com.example.music;
import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Artists extends Fragment  {
    public static final String TAG = "Artists";
    private View view;
    private TextView artistsCountTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_artists, container, false);
        artistsCountTextView =view.findViewById(R.id.fragartist);
        TextView artists = view.findViewById(R.id.tvartist);
        Activity activity = getActivity();
        assert activity != null;
        artists.setTextColor(ContextCompat.getColor(getActivity(), R.color.Green));

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    0);
        } else {
            retrieveArtistName();
        }

        return view;
    }

    public void retrieveArtistName() {
        ContentResolver contentResolver = view.getContext().getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.DATA+" LIKE ?";
        String[] selectionArgs = new String[]{"%Music%"};
        Cursor cursor = contentResolver.query(
                uri,
                new String[]{MediaStore.Audio.Media.ARTIST},
                selection,
                selectionArgs,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            HashMap<String, Integer> artistMap = new HashMap<>();
            int artistColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            do {
                String artist = cursor.getString(artistColumn);
                if (artistMap.containsKey(artist)) {
                    Integer count = artistMap.get(artist);
                    if(count!=null){
                        artistMap.put(artist, count + 1);
                    }
                } else {
                    artistMap.put(artist, 1);
                }
            } while (cursor.moveToNext());
            cursor.close();

            ArrayList<String> artistList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : artistMap.entrySet()) {
                String artist = entry.getKey();
                int count = entry.getValue();
                String formattedArtist = artist + " (" + count + ")";
                artistList.add(formattedArtist);
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                    R.layout.artist_list, R.id.artistNameTextView, artistList);
            ListView artistListView = view.findViewById(R.id.lvartist);
            if (artistListView != null) {
                artistListView.setAdapter(adapter);
            }
                String artistsCountText = artistList.size() +" "+ "artists";
                artistsCountTextView.setText(artistsCountText);

        } else {
            Log.d(TAG, "No artist found");
        }
    }


}



