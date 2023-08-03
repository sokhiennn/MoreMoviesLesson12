package sg.edu.rp.c346.id22013179.mymoviesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetMovie;
    TextView tvTitle, tvGenre, tvYear, tvRating;
    ListView lv;
    EditText editTitle, editGenre, editYear, editRating;
    ArrayList<Movie>  movieList = new ArrayList<>();
    ArrayAdapter<Movie> aaMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetMovie = findViewById(R.id.btnGetMovie);
        tvTitle = findViewById(R.id.tvTitle);
        tvGenre = findViewById(R.id.tvGenre);
        tvYear = findViewById(R.id.tvYear);
        tvRating = findViewById(R.id.tvRating);
        lv = findViewById(R.id.lv);
        editTitle = findViewById(R.id.editTitle);
        editGenre = findViewById(R.id.editGenre);
        editYear = findViewById(R.id.editYear);
        editRating = findViewById(R.id.editRating);

        movieList = new ArrayList<>();
        aaMovie = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, movieList);
        lv.setAdapter(aaMovie);

        btnInsert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                String title = editTitle.getText().toString();
                String genre = editGenre.getText().toString();
                int year = Integer.parseInt(editYear.getText().toString());
                String rating = editRating.getText().toString();

                db.insertMovie(title, genre, year, rating);

                Toast.makeText(MainActivity.this, "Movie inserted successfully!", Toast.LENGTH_SHORT).show();

                editTitle.setText("");
                editGenre.setText("");
                editYear.setText("");

            }
        });
        btnGetMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }
}