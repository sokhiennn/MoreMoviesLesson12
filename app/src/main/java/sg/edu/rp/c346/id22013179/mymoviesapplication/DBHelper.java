package sg.edu.rp.c346.id22013179.mymoviesapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VER = 1;
    private static final String DATABASE_NAME = "movies.db";
    private static final String TABLE_MOVIE = "movie";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_GENRE = "genre";
    private static final String COLUMN_YEAR = "year";
    private static final String COLUMN_RATING = "rating";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSql = "CREATE TABLE " + TABLE_MOVIE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " TEXT," +
                COLUMN_GENRE + " TEXT," +
                COLUMN_YEAR + " INTEGER," +
                COLUMN_RATING + " TEXT)";
        db.execSQL(createTableSql);
        Log.i("DBHelper", "Created table: " + TABLE_MOVIE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        onCreate(db);
    }
    public void insertMovie(String title, String genre, int year, String rating){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, title);
        values.put(COLUMN_GENRE, genre);
        values.put(COLUMN_YEAR, year);
        values.put(COLUMN_RATING, rating);

        db.insert(TABLE_MOVIE, null, values);

        db.close();
    }
    public ArrayList<String> getMovieContent() {
        // Create an ArrayList that holds String objects
        ArrayList<String> movies = new ArrayList<String>();
        // Get the instance of database to read
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR,COLUMN_RATING};
        // Run the query and get back the Cursor object
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                movies.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        // Close connection
        cursor.close();
        db.close();

        return movies;
    }
    public ArrayList<Movie> getMovie() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR,COLUMN_RATING};
        Cursor cursor = db.query(TABLE_MOVIE, columns, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                int year = cursor.getInt(3);
                String rating = cursor.getString(4);
                Movie obj = new Movie(id, title, genre,year,rating);
                movies.add(obj);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return movies;
    }
    public ArrayList<Movie> getMovieYear(int year) {
        ArrayList<Movie> movieList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COLUMN_YEAR + " ?";
        String[] selectionArgs = {String.valueOf(year)};
        Cursor cursor = db.query(TABLE_MOVIE, selectionArgs,  selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String genre = cursor.getString(2);
                String rating = cursor.getString(4);

                Movie movie = new Movie(id, title, genre, year,rating);
                movieList.add(movie);
            } while (cursor.moveToNext());

        }
        cursor.close();
        db.close();
        return movieList;

    }
    public void updateMovies(Movie movie) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE, movie.getTitle());
        values.put(COLUMN_GENRE, movie.getGenre());
        values.put(COLUMN_YEAR, movie.getYear());
        values.put(COLUMN_RATING, movie.getRating());
        String selection = COLUMN_ID+ " = ?";
        String[] selectionArgs = {String.valueOf(movie.getId())};
        db.update(TABLE_MOVIE,  values, selection, selectionArgs);
        db.close();

    }
    public void deleteMovies(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection =  COLUMN_ID + " =?";
        String[] selectionArgs = {String.valueOf(id)};
        db.delete(TABLE_MOVIE, selection, selectionArgs);
        db.close();

    }
}
