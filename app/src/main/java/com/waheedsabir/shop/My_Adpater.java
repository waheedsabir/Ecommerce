package com.waheedsabir.shop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.waheedsabir.shop.model.Cart_GetSet;

import java.util.ArrayList;
import java.util.List;

public class My_Adpater extends RecyclerView.Adapter<My_Adpater.My_holder> {
    private List<ListItem> listItems ;
    private Context context ;

    public My_Adpater(List<ListItem> listItems, Context context) {
        this.listItems =listItems ;
        this.context =context ;

    }

    public My_Adpater(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public My_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item,parent,false);

        return new My_holder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull My_holder holder, int position) {


        ListItem listItem =listItems.get(position);
        holder.textView.setText("RS. "+listItem.price);

        Picasso.get()
                .load("http://"+listItem.thumb)
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(holder.imageView);

      //  Picasso.get().load("http://"+listItem.thumb).into(holder.imageView);
        //Toast.makeText(context, "http://"+listItem.thumb, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }



    public class My_holder extends RecyclerView.ViewHolder{


        public TextView textView ;
        public ImageView imageView ;

        public My_holder(View itemView) {
            super(itemView);

            textView =itemView.findViewById(R.id.text) ;
            imageView =itemView.findViewById(R.id.image) ;
        }
    }


}
