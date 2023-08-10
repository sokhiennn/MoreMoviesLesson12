package sg.edu.rp.c346.id22013179.mymoviesapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView tvMovieID,tvMovieTitle,tvGenres,tvYears,tvRatings;
    EditText etMovieTitle, etGenres, etYears,etRatings;
    Button btnUpdate, btnDelete, btnCancel;

    Movie movie;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvMovieID = findViewById(R.id.tvMovieID);
        tvMovieTitle = findViewById(R.id.tvMovieTitle);
        tvGenres = findViewById(R.id.tvGenres);
        tvYears= findViewById(R.id.tvYears);
        tvRatings = findViewById(R.id.tvRatings);
        etMovieTitle = findViewById(R.id.etMovieTitle);
        etGenres = findViewById(R.id.etGenres);
        etYears = findViewById(R.id.etYears);
        etRatings = findViewById(R.id.etRatings);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent intent= getIntent();
        movie = (Movie) intent.getSerializableExtra("movie");
        etMovieTitle.setText(movie.getTitle());
        etGenres.setText(movie.getGenre());
        etYears.setText(String.valueOf(movie.getYear()));
        etRatings.setText(String.valueOf(movie.getRating()));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title =  etMovieTitle.getText().toString().trim();
                String genres =  etGenres.getText().toString().trim();
                int year =  Integer.parseInt(etYears.getText().toString().trim());
                String ratings =  etRatings.getText().toString().trim();
                movie.setTitle(title);
                movie.setGenre(genres);
                movie.setYear(year);
                movie.setRating(ratings);
                dbHelper.updateMovies(movie);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ThirdActivity.this);
                builder.setTitle("Confirm Deletion");
                builder.setMessage("Are you sure you want to delete this movie?");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper.deleteMovies(movie.getId());
                                finish();
                            }
                        });
                builder.setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}
