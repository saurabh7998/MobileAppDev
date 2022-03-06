package edu.neu.madcourse.numad22sp_saurabhgade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//import org.json.simple.*;
//import org.json.simple.parser.*;


public class ApiActivity extends AppCompatActivity {

    private Button submitBtn;
    private EditText editTextMovie;
    private TextView responseView1;
    private HttpURLConnection conn;
    private BufferedReader bufferedReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);

        Intent intent = getIntent();

        submitBtn = findViewById(R.id.submitMovieBtn);
        editTextMovie = findViewById(R.id.editTextMovieName);
        responseView1 = findViewById(R.id.responseView1);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //connectToApi();
                connectToApiOnNewThread();
            }
        });
    }


    private void connectToApiOnNewThread() {
        Handler handler = new Handler();

        Runnable objRunnable = new Runnable() {
            @Override
            public void run() {
                String line = "";
                StringBuilder response = new StringBuilder();
                try {
                    URL url = new URL("https://www.omdbapi.com/?apikey=497dcded&s=the%20avengers");
                    conn = (HttpURLConnection) url.openConnection();

                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(4000);
                    conn.setReadTimeout(4000);


                    int status = conn.getResponseCode();

                    if (status > 299) {
                        bufferedReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
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
                            try {
                                JSONObject obj = new JSONObject(response.toString());
                                JSONArray arr = obj.getJSONArray("Search");
                                JSONObject arrObj = arr.getJSONObject(0);
                                String title = arrObj.getString("Title");
                                System.out.println("Title of movie is " + title);
                            } catch (JSONException e) {
                                e.printStackTrace();
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


    private void connectToApi() {


        String line = "";
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL("https://www.omdbapi.com/?apikey=497dcded&s=the%20avengers");
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setConnectTimeout(4000);
            conn.setReadTimeout(4000);


            int status = conn.getResponseCode();

            if (status > 299) {
                bufferedReader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            } else {
                bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            }
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();
            System.out.println(response.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }

    }


}


