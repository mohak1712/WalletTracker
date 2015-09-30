package com.hackdelhi.dyingtocode.wallettracker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Sarthak on 15-07-2015.
 */
public class AdapterforIshaan extends RecyclerView.Adapter<AdapterforIshaan.ViewHolder> {
    public LayoutInflater inflater;
    Context context;
    ArrayList<ClassSearch> data = new ArrayList<ClassSearch>();

    AdapterforIshaan(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;

    }

    public void adap(ArrayList<ClassSearch> data) {
        this.data = data;
        notifyItemRangeChanged(0,data.size());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.search_single, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

       // Ishaandata current = data.get(position);
       // holder.textView.setText(current.text);
       // Picasso.with(context).load(current.url).resize(240, 320).into(holder.imageView2);
       // Async async = new Async(context);
        //async.execute(current.url);
        ClassSearch search = data.get(position);
        holder.textView.setText(search.string);
        holder.imageView2.setImageResource(search.iconId);



    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView2;


        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textView3);
            imageView2 = (ImageView) itemView.findViewById(R.id.imageView2);


        }


    }
}
