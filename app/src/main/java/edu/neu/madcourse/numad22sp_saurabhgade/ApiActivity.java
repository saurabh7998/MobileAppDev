package edu.neu.madcourse.numad22sp_saurabhgade;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class ApiActivity extends AppCompatActivity {

    private Button submitBtn;
    private EditText editTextMovie;
    private TextView genreView;
    private TextView yearView;
    private TextView imdbView;
    private TextView errorView;
    private RadioButton movieRadioBtn;
    private RadioButton seriesRadioBtn;
    private RadioButton epRadioBtn;
    private ImageView imageView;
    private HttpURLConnection conn;
    private BufferedReader bufferedReader;

    private ProgressDialog progressBar;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        Intent intent = getIntent();

        submitBtn = findViewById(R.id.submitMovieBtn);
        editTextMovie = findViewById(R.id.editTextMovieName);
        genreView = findViewById(R.id.genreView);
        yearView = findViewById(R.id.yearView);
        imdbView = findViewById(R.id.imdbView);
        errorView = findViewById(R.id.errorView);
        movieRadioBtn = findViewById(R.id.movieRadioBtn);
        seriesRadioBtn = findViewById(R.id.seriesRadioBtn);
        epRadioBtn = findViewById(R.id.epRadioBtn);
        imageView = findViewById(R.id.imageView2);


        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //connectToApi();
                connectToApiOnNewThread();
            }
        });
    }


    private void connectToApiOnNewThread() {
        errorView.setText("");

        if (editTextMovie.getText() == null || editTextMovie.getText().toString().equals("")) {
            errorView.setText("Please select valid options for the request");
            return;
        }
        String movieName = editTextMovie.getText().toString();

//        if (!epRadioBtn.isActivated() && !movieRadioBtn.isActivated() && !seriesRadioBtn.isActivated()) {
//            responseView1.setText("Please select valid options for the request");
//            return;
//        }
        String type = "";
        if (epRadioBtn.isActivated()) {
            type = "episode";
        }
        if (movieRadioBtn.isActivated()) {
            type = "movie";
        }
        if (seriesRadioBtn.isActivated()) {
            type = "series";
        }


        String finalType = type;
        Runnable objRunnable = new Runnable() {
            @Override
            public void run() {
                String line = "";
                StringBuilder response = new StringBuilder();
                try {
                    URL url = new URL("https://www.omdbapi.com/?apikey=497dcded&t=" + movieName + "&type=" + finalType);
                    conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(4000);
                    conn.setReadTimeout(4000);


                    int status = conn.getResponseCode();

                    if (status > 299) {
                        bufferedReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                        errorView.setText("No Response found");
                    } else {
                        bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        System.out.println();
                    }
                    while ((line = bufferedReader.readLine()) != null) {
                        response.append(line);
                    }
                    bufferedReader.close();


                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            System.out.println(response);
                            String data = response.toString();

                            try {

                                JSONObject obj = new JSONObject(response.toString());
                                String title = obj.getString("Title");
                                String year = obj.getString("Year");
                                String genre = obj.getString("Genre");
                                String imdb = obj.getString("imdbRating");

                                genreView.setText("Genre: " + genre);
                                yearView.setText("Year: " + year);
                                imdbView.setText("IMDB: " + imdb);

                                String poster = obj.getString("Poster");
                                new FetchImage(poster).start();

//                                JSONArray arr = obj.getJSONArray("Search");
//                                JSONObject arrObj = arr.getJSONObject(0);
//                                String title = arrObj.getString("Title");
//
//                                String year = arrObj.getString("Year");
//                                System.out.println("Title of movie is " + title);
//                                responseView1.setText(year);


                            } catch (JSONException e) {
                                e.printStackTrace();
                                errorView.setText("No response found! Please check input");
                                editTextMovie.setText("");
                                imdbView.setText("");
                                yearView.setText("");
                                genreView.setText("");
                                imageView.setImageBitmap(null);
                            }
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    conn.disconnect();
                }
            }
        };

        Thread bgThread = new Thread(objRunnable);
        bgThread.start();

    }

    class FetchImage extends Thread {
        String url;
        Bitmap bitmap;

        FetchImage(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar = new ProgressDialog(ApiActivity.this);
                    progressBar.setMessage("Loading response");
                    progressBar.setCancelable(false);
                    progressBar.show();

                }
            });

            InputStream inputStream = null;
            try {
                inputStream = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }

            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (progressBar.isShowing()) {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        progressBar.dismiss();
                    }

                    imageView.setImageBitmap(bitmap);
                }
            });


        }
    }

}


