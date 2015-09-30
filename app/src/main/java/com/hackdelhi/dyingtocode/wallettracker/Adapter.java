package com.hackdelhi.dyingtocode.wallettracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Collections;
import java.util.List;

/**
 * Created by Sarthak on 02-07-2015.
 */                                               //classname.MyViewHolder
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    List<SingleRow> data = Collections.emptyList();
    LayoutInflater inflater;
    //List<SingleRow> refers to list of objects of SingleRow

    public Adapter(Context context, List<SingleRow> data)
    {
        inflater=LayoutInflater.from(context);
        this.data = data;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //Whenever new row is to be displayed this fn is called
        View view = inflater.inflate(R.layout.single_row, viewGroup , false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        //used to set data to be displayed int the recycler view and update it as per the position (i)

            SingleRow current = data.get(position);
            viewHolder.textView.setText(current.text);
            viewHolder.icon.setImageResource(current.Iconid);



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
        //This class needs to be a sub class of the Adapter class
    {
        TextView textView;
        ImageView icon;
        ImageButton button;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.listtext);
            icon = (ImageView) itemView.findViewById(R.id.image);

        }
    }
}
