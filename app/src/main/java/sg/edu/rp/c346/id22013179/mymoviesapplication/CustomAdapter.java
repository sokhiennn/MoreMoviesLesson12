package sg.edu.rp.c346.id22013179.mymoviesapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> versionList;

    public CustomAdapter(@NonNull Context context, int resource,
                         ArrayList<Movie> objects) {
        super(context, resource);
        this.parent_context = context;
        this.layout_id = resource;
        this.versionList = objects;
    }
    public View getView(int index, View view, ViewGroup pcView) {
        View viewMovie = view;

        if (viewMovie == null) {
            LayoutInflater inflater = (LayoutInflater) parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            viewMovie = inflater.inflate(R.layout.row, pcView, false);
        }
        TextView tvMovieTitle = viewMovie.findViewById(R.id.tvMovieTitle);
        TextView tvYear = viewMovie.findViewById(R.id.tvYear);
        TextView tvGenre = viewMovie.findViewById(R.id.tvGenres);
        TextView tvRatings = viewMovie.findViewById(R.id.tvRatings);
        Movie moviePosition = versionList.get(index);

        tvMovieTitle.setText(moviePosition.getTitle());
        tvYear.setText(String.valueOf(moviePosition.getYear()));
        tvGenre.setText(moviePosition.getGenre());
        tvRatings.setText(moviePosition.getRating());

        return viewMovie;
    }
}
