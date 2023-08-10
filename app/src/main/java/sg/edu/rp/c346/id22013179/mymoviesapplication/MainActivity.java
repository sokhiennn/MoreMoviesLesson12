package sg.edu.rp.c346.id22013179.mymoviesapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetMovie;
    EditText editTitle, editGenre, editYear;
    Spinner spinnerRating;
    DBHelper db;

    String[] movieRatings = {"G", "PG", "PG13", "NC16", "M18", "R21"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetMovie = findViewById(R.id.btnGetMovie);
        editTitle = findViewById(R.id.editTitle);
        editGenre = findViewById(R.id.editGenre);
        editYear = findViewById(R.id.editYear);
        spinnerRating = findViewById(R.id.spinnerRating);

        ArrayAdapter<String> ratingAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, movieRatings);
        spinnerRating.setAdapter(ratingAdapter);

        db = new DBHelper(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTitle.getText().toString();
                String genre = editGenre.getText().toString();
                int year = Integer.parseInt(editYear.getText().toString());
                String rating = movieRatings[spinnerRating.getSelectedItemPosition()];

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
