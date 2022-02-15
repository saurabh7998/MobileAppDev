package edu.neu.madcourse.numad22sp_saurabhgade;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);

        addItemBtn = findViewById(R.id.addItemBtn);

        urlList = new ArrayList<>();
        urlNameList = new ArrayList<>();
//        urlList.add("Hello");
//        urlList.add("world");
//        urlNameList.add("H");
//        urlNameList.add("W");

        recyclerView = findViewById(R.id.recyclerView);

        addItemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        recyclerViewAdapter = new RecyclerViewAdapter(LinksActivity.this, urlList, urlNameList, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    private void openDialog() {
        InputDialog inputDialog = new InputDialog();
        inputDialog.show(getSupportFragmentManager(), "input dialog");
    }

    @Override
    public void applyTexts(String url, String urlName) {
        urlList.add(url);
        urlNameList.add(urlName);
        Snackbar linkCreatedSnackbar = Snackbar.make(recyclerView, urlName + " link created successfully", Snackbar.LENGTH_SHORT);
        linkCreatedSnackbar.show();
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
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView
                .ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            urlList.remove(viewHolder.getAdapterPosition());
            urlNameList.remove(viewHolder.getAdapterPosition());
            recyclerViewAdapter.notifyDataSetChanged();
        }
    };
}