package com.example.ardianza.selectableitem;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by msipc on 8/13/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<ListItem> listItems;
    private Context mContext;

    public MyAdapter(List<ListItem> listItems, Context mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ListItem listItem = listItems.get(position);

        holder.tvHead.setText(listItem.getHead());
        holder.tvDesc.setText(listItem.getDesc());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("haha", String.valueOf(position));
            }
        });
        /*Picasso.with(mContext).load(listItem.getImageUrl())
                .into(holder.imgUrl);*/


    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvHead;
        public TextView tvDesc;
        CardView cardView;
        //public ImageView imgUrl;

        public ViewHolder(View itemView) {
            super(itemView);
            tvHead = (TextView)itemView.findViewById(R.id.textHeader);
            tvDesc = (TextView)itemView.findViewById(R.id.textDescription);
            cardView = itemView.findViewById(R.id.cardView);
            //imgUrl = (ImageView)itemView.findViewById(R.id.imgUrl);
        }
    }
}
