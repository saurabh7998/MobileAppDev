package edu.neu.madcourse.numad22sp_saurabhgade;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.neu.madcourse.numad22sp_saurabhgade.adapter.RecyclerViewAdapter;

public class LinksActivity extends AppCompatActivity {

    private FloatingActionButton addItemBtn;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private Map<String, String> urlMap;
    private List<String> urlList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);

        Intent intent = getIntent();

        addItemBtn = findViewById(R.id.addItemBtn);
        urlList = new ArrayList<>();
        urlList.add("Hello");
        urlList.add("world");

        recyclerView = findViewById(R.id.recyclerView);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LinksActivity.this);
//                alertDialogBuilder.setMessage(R.string.dialog_message)
//                        .setTitle(R.string.dialog_title);
//                AlertDialog dialog = alertDialogBuilder.create();
//                alertDialogBuilder.show();
                
            }
        });

        recyclerViewAdapter = new RecyclerViewAdapter(LinksActivity.this, urlList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}