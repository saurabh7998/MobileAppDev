package edu.neu.madcourse.numad22sp_saurabhgade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.neu.madcourse.numad22sp_saurabhgade.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>  {

    private Context context;
    private List<String> stringList;


    public RecyclerViewAdapter(Context context, List<String> stringList) {
        this.context  = context;
        this.stringList = stringList;
    }


    //Where to get single card as view holder object
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent,false);
        return new ViewHolder(view);
    }

    //What will happen after we create viewholder object
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String url = stringList.get(position);
        holder.urlName.setText(url);
    }

    //How many items?
    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView urlName;
        CardView layoutx;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //itemView.setOnClickListener(this);
            this.urlName = itemView.findViewById(R.id.urlTextView);
            this.layoutx = itemView.findViewById(R.id.layoutx);
        }

        @Override
        public void onClick(View v) {
            Toast toast = Toast.makeText(context.getApplicationContext(), "text", Toast.LENGTH_SHORT);
        }
    }
}
