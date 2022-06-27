package com.example.meloday20;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.meloday20.fragments.PostFragment;

import org.parceler.Parcels;

import java.util.List;

import kaaes.spotify.webapi.android.models.ArtistSimple;
import kaaes.spotify.webapi.android.models.Image;
import kaaes.spotify.webapi.android.models.Track;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private static final String TAG = "SearchAdapter";
    private final Context context;
    private final List<Track> tracks;

    public SearchAdapter(Context context, List<Track> tracks) {
        this.context = context;
        this.tracks = tracks;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate view
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_track, parent, false); // pass it a “blueprint” of the view (reference to XML layout file)
        return new ViewHolder(view); // wrap view in viewholder
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Track track = tracks.get(position);
        holder.bind(track);
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public void goToPostFragment() {
    }

    // viewholder class
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        TextView tvArtist;
        ImageView ivCoverImage;
        String artistsString;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvArtist = itemView.findViewById(R.id.tvArtist);
            ivCoverImage = itemView.findViewById(R.id.ivCoverImage);
            artistsString = "";
        }

        public void bind(Track track) {
            tvTitle.setText(track.name);
            artistsString = track.artists.get(0).name;
            if (track.artists.size() > 1) {
                for (int i = 1; i < track.artists.size(); i++) {
                    artistsString = artistsString + ", " + track.artists.get(i).name;
                }
            }
            Log.i(TAG, track.name + " by: " + artistsString);
            tvArtist.setText(artistsString);

            Image coverImage = track.album.images.get(0);
            if (coverImage != null) {
                Glide.with(context)
                        .load(coverImage.url)
                        .into(ivCoverImage);
            }
        }

        @Override
        public void onClick(View v) {
            Log.i(TAG, "Clicked searched track!");
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) { //if position valid, get that post
                Track track = tracks.get(position);
                Log.i(TAG,"Search tracks position: " + position);

//                AppCompatActivity activity = MainActivity;
//                PostFragment myFragment = new PostFragment();
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("track", Parcels.wrap(track));
//                myFragment.setArguments(bundle);
//                activity.getChildFragmentManager().beginTransaction().replace(R.id.flContainer, myFragment).addToBackStack(null).commit();
//                ((SearchTracksActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, myFragment).addToBackStack(null).commit();
//                activity.getChildFragmentManager().beginTransaction().replace(R.id.flContainer, myFragment).addToBackStack(null).commit();
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("track", Parcels.wrap(track));
                context.startActivity(intent);
            }
        }
    }
}
