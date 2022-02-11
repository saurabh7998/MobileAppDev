package edu.neu.madcourse.numad22sp_saurabhgade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import edu.neu.madcourse.numad22sp_saurabhgade.adapter.RecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {

    private Button aboutMeBtn;
    private Button clickyBtn;
    private  Button linkCollectorBtn;

//    private RecyclerView recyclerView;
//    private RecyclerViewAdapter recyclerViewAdapter;
//    private Map<String, String> urlMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutMeBtn = findViewById(R.id.button);
        clickyBtn = findViewById(R.id.clickyBtn);
        linkCollectorBtn = findViewById(R.id.linkBtn);
        //urlMap = new HashMap<>();

        aboutMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Context context = getApplicationContext();
                CharSequence text = "Saurabh, gade.sau@northeastern.edu";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                 */
                goToAboutMeActivity(v);
            }
        });

        clickyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGridActivity(v);
            }
        });

        linkCollectorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLinksActivity(v);
            }
        });
    }

    public void goToGridActivity(View view){
        Intent intent = new Intent(this, GridActivity.class);
        startActivity(intent);
    }

    public void goToAboutMeActivity(View view){
        Intent intent = new Intent(this, AboutMeActivity.class);
        startActivity(intent);
    }

    public void goToLinksActivity(View view){
        Intent intent = new Intent(this, LinksActivity.class);
        startActivity(intent);
    }
}