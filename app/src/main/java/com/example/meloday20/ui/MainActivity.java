package com.example.meloday20.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.meloday20.R;
import com.example.meloday20.ui.home.HomeFragment;
import com.example.meloday20.ui.playlist.PlaylistFragment;
import com.example.meloday20.ui.search.SearchFragment;
import com.example.meloday20.ui.profile.ProfileFragment;
import com.example.meloday20.service.SpotifyServiceSingleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.parse.ParseUser;

import kaaes.spotify.webapi.android.SpotifyService;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private String accessToken;
    public  SpotifyService spotify;
    public static BottomNavigationView bottomNavigationView;
    final FragmentManager fts = getSupportFragmentManager();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accessToken = ParseUser.getCurrentUser().getString(MainActivity.this.getString(R.string.keyAccessToken));
        spotify  = SpotifyServiceSingleton.getInstance(accessToken);
        setContentView(R.layout.activity_main);
        initBottomNav();
    }

    private void initBottomNav() {
        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_search:
                        fragment = new SearchFragment();
                        break;
                    case R.id.action_playlist:
                        fragment = new PlaylistFragment();
                        break;
                    case R.id.action_profile:
                        fragment = new ProfileFragment();
                        break;
                    case R.id.action_home:
                    default:
                        fragment = new HomeFragment();
                        break;
                }
                fts.beginTransaction().replace(R.id.flContainer, fragment).commit();
                return true;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }

}