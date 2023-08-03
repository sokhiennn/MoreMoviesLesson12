package sg.edu.rp.c346.id22013179.mymoviesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    Button btnShowMovie;
    ListView lvMovieDetails;
    ArrayAdapter<Movie>adapter;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnShowMovie = findViewById(R.id.btnShowMovie);
        lvMovieDetails = findViewById(R.id.lvMovieDetails);

        dbh = new DBHelper(SecondActivity.this);
        ArrayList<Movie> movieList = dbh.getMovie();

        movieList = dbh.getMovie();
        adapter = new CustomAdapter(this, android.R.layout.simple_list_item_1, movieList);
        lvMovieDetails.setAdapter(adapter);

        lvMovieDetails.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie clickMovie = (Movie) parent.getItemAtPosition(position);
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("song", clickMovie);
                startActivity(intent);
            }
        });
        btnShowMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbh = new DBHelper(SecondActivity.this);

                adapter.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }
    public void onResume() {
        super.onResume();

        ArrayList<Movie> movieList = dbh.getMovie();
        adapter.clear();
        adapter.addAll(movieList);
        adapter.notifyDataSetChanged();
    }
}