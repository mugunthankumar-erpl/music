package com.example.music;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import java.util.List;

public class Album extends Fragment {
    View view;
    public ListView albumListView;
    public List<String> albumList;
    TextView album; // mugunthans
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_album, container, false);
        album = view.findViewById(R.id.tvalbum);
        Activity activity = getActivity();
        assert activity != null;
        album.setTextColor(ContextCompat.getColor(getActivity(),R.color.Green));
        albumListView = view.findViewById(R.id.lvalbum);
        retrieveAlbums();
        return view;

    }
    private void retrieveAlbums() {
        albumList = new ArrayList<>();
        String[] projection = {MediaStore.Audio.Albums.ALBUM};
        Uri externalUri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
        Cursor cursor = requireActivity().getContentResolver().query(
                externalUri,
                projection,
                null,
                null,
                null
        );

        if (cursor != null && cursor.moveToFirst()) {
            int albumColumn = cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM);

            do {
                String album = cursor.getString(albumColumn);
                albumList.add(album);
            } while (cursor.moveToNext());

            cursor.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                R.layout.artist_list, R.id.artistNameTextView, albumList);
        albumListView.setAdapter(adapter);
    }


}