package edu.neu.madcourse.numad22sp_saurabhgade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


import edu.neu.madcourse.numad22sp_saurabhgade.adapter.RecyclerViewAdapter;

public class LinksActivity extends AppCompatActivity implements InputDialog.InputDialogListener, RecyclerViewAdapter.OnNoteListener {

    private FloatingActionButton addItemBtn;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;

    private List<String> urlList;
    private List<String> urlNameList;
    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);

        Intent intent = getIntent();

        addItemBtn = findViewById(R.id.addItemBtn);

        urlList = new ArrayList<>();
        urlNameList = new ArrayList<>();
        urlList.add("Hello");
        urlList.add("world");
        urlNameList.add("H");
        urlNameList.add("W");

        recyclerView = findViewById(R.id.recyclerView);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        recyclerViewAdapter = new RecyclerViewAdapter(LinksActivity.this, urlList, urlNameList,this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

    public void openDialog() {
        InputDialog inputDialog = new InputDialog();
        inputDialog.show(getSupportFragmentManager(), "input dialog");
    }

    public void goToURL(String link) {
        Uri uri = Uri.parse(link);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }


    @Override
    public void applyTexts(String url, String urlName) {
        urlList.add(url);
        urlNameList.add(urlName);

    }

    @Override
    public void onNoteClick(int position) {
        String url = urlList.get(position).toLowerCase();
        if (!url.contains("www.") && !url.startsWith("www.")) {
            url = "www." + url;
        }
        if (!url.startsWith("http") && !url.startsWith("https")) {
            url = "http://" + url;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
}