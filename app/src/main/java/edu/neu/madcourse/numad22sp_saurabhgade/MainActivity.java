package edu.neu.madcourse.numad22sp_saurabhgade;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.location.LocationRequest;


public class MainActivity extends AppCompatActivity {

    private Button aboutMeBtn;
    private Button clickyBtn;
    private Button linkCollectorBtn;
    private Button locationBtn;
    private LocationRequest locationRequest;
    private Button apiBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutMeBtn = findViewById(R.id.button);
        clickyBtn = findViewById(R.id.clickyBtn);
        linkCollectorBtn = findViewById(R.id.linkBtn);
        locationBtn = findViewById(R.id.locationBtn);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);

        apiBtn = findViewById(R.id.apiBtn);


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

        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getCurrentLocation();
                goToLocationActivity(v);
            }
        });

        apiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToApiActivity(v);
            }
        });
    }


    public void setActivityBackgroundColor(int color) {
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    public void goToGridActivity(View view) {
        Intent intent = new Intent(this, GridActivity.class);
        startActivity(intent);
    }

    public void goToAboutMeActivity(View view) {
        Intent intent = new Intent(this, AboutMeActivity.class);
        startActivity(intent);
    }

    public void goToLinksActivity(View view) {
        Intent intent = new Intent(this, LinksActivity.class);
        startActivity(intent);
    }

    public void goToLocationActivity(View view) {
        Intent intent = new Intent(this, LocationActivity.class);
        startActivity(intent);
    }

    public void goToApiActivity(View view){
        Intent intent = new Intent(this, ApiActivity.class);
        startActivity(intent);
    }
}