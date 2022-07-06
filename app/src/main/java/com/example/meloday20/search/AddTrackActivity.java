package com.example.meloday20.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.meloday20.MainActivity;
import com.example.meloday20.R;
import com.example.meloday20.playlist.PlaylistViewModel;
import com.example.meloday20.utils.SpotifyServiceSingleton;
import com.example.meloday20.playlist.ParsePlaylist;
import com.example.meloday20.home.Post;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Pager;
import kaaes.spotify.webapi.android.models.PlaylistTrack;
import kaaes.spotify.webapi.android.models.Track;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class AddTrackActivity extends AppCompatActivity {
    private static final String TAG = AddTrackActivity.class.getSimpleName();
    private AddTrackViewModel viewModel;
    private static Track track;
    private static String artistsString;
    TextView tvAddTrackTitle;
    TextView tvAddTrackArtist;
    ImageView ivAddTrackCover;
    Button btnAddTrackToPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);
        viewModel = new ViewModelProvider(this).get(AddTrackViewModel.class);
        track = Parcels.unwrap(getIntent().getParcelableExtra("track"));
        initViews();
        setViewValues();
    }

    private void setViewValues() {
        tvAddTrackTitle.setText(track.name);
        artistsString = track.artists.get(0).name;
        if (track.artists.size() > 1) {
            for (int i = 1; i < track.artists.size(); i++) {
                artistsString = artistsString + ", " + track.artists.get(i).name;
            }
        }
        tvAddTrackArtist.setText(artistsString);
        Image coverImage = track.album.images.get(0);
        if (coverImage != null) {
            Glide.with(this)
                    .load(coverImage.url)
                    .into(ivAddTrackCover);
        }
    }

    private void initViews() {
        tvAddTrackTitle = findViewById(R.id.tvAddTrackTitle);
        tvAddTrackArtist = findViewById(R.id.tvAddTrackArtist);
        ivAddTrackCover = findViewById(R.id.ivAddTrackCover);
        btnAddTrackToPlaylist = findViewById(R.id.btnAddTrackToPlaylist);
        btnAddTrackToPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.addTrackActions(track);
                goToMainActivity();
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(AddTrackActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}