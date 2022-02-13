package edu.neu.madcourse.numad22sp_saurabhgade.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.neu.madcourse.numad22sp_saurabhgade.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<String> urlList;
    private List<String> urlNameList;
    private TextView tv;
    private OnNoteListener mOnNoteListener;


    public RecyclerViewAdapter(Context context, List<String> urlList, List<String> urlNameList, OnNoteListener mOnNoteListener) {
        this.context = context;
        this.urlList = urlList;
        this.urlNameList = urlNameList;
        this.mOnNoteListener = mOnNoteListener;
    }


    //Where to get single card as view holder object
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        tv = view.findViewById(R.id.urlTextView);

        return new ViewHolder(view,mOnNoteListener);
    }

    //What will happen after we create viewholder object
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = urlList.get(position);
        String urlName = urlNameList.get(position);
        holder.link.setText(url);
        holder.urlName.setText(urlName);
    }

    //How many items?
    @Override
    public int getItemCount() {
        return urlList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnNoteListener onNoteListener;
        public TextView link;
        public TextView urlName;
        CardView layoutx;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            itemView.setOnClickListener(this);
            this.onNoteListener = onNoteListener;
            this.link = itemView.findViewById(R.id.urlTextView);
            this.urlName = itemView.findViewById(R.id.urlNameTextView);
            this.layoutx = itemView.findViewById(R.id.layoutx);
        }

        @Override
        public void onClick(View v) {
            //goToURL(link.toString());
            onNoteListener.onNoteClick(getAdapterPosition());
            Toast toast = Toast.makeText(context.getApplicationContext(), "text", Toast.LENGTH_SHORT);
        }


        public void goToURL(String link) {
            Uri uri = Uri.parse(link);
            itemView.getContext().startActivity(new Intent(Intent.ACTION_VIEW, uri));
        }

    }

    public interface OnNoteListener {
        void onNoteClick(int position);
    }
}
