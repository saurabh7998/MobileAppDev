package edu.neu.madcourse.numad22sp_saurabhgade;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.neu.madcourse.numad22sp_saurabhgade.adapter.RecyclerViewAdapter;

public class LinksActivity extends AppCompatActivity implements InputDialog.InputDialogListener {

    private FloatingActionButton addItemBtn;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager rLayoutManger;
    private Map<String, String> urlMap;
    private List<String> urlList;
    private List<String> urlNameList;


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
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(LinksActivity.this);
//                alertDialogBuilder.setMessage(R.string.dialog_message)
//                        .setTitle(R.string.dialog_title);
//                AlertDialog dialog = alertDialogBuilder.create();
//                alertDialogBuilder.show();
                openDialog();

            }
        });

        recyclerViewAdapter = new RecyclerViewAdapter(LinksActivity.this, urlList,urlNameList);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public void openDialog(){
        InputDialog inputDialog = new InputDialog();
        inputDialog.show(getSupportFragmentManager(),"input dialog");
    }

    @Override
    public void applyTexts(String url, String urlName) {
        urlList.add(url);
        urlNameList.add(urlName);
    }
}