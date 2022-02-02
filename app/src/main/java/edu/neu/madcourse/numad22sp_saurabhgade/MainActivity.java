package edu.neu.madcourse.numad22sp_saurabhgade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button aboutMeBtn;
    private Button clickyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutMeBtn = findViewById(R.id.button);
        clickyBtn = findViewById(R.id.clickyBtn);

        aboutMeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                CharSequence text = "Saurabh, gade.sau@northeastern.edu";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        clickyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGridActivity(v);
            }
        });
    }

    public void goToGridActivity(View view){
        Intent intent = new Intent(this, GridActivity.class);
        startActivity(intent);
    }
}