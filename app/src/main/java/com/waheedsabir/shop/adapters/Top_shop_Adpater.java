package com.waheedsabir.shop.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;
import com.waheedsabir.shop.ListItem;
import com.waheedsabir.shop.R;
import com.waheedsabir.shop.services.Top_shop_itemlist;

import java.util.List;

public class Top_shop_Adpater extends RecyclerView.Adapter<Top_shop_Adpater.My_holder> {
    private List<Top_shop_itemlist> listItems ;
    private Context context ;

    public Top_shop_Adpater(List<Top_shop_itemlist> listItems, Context context) {
        this.listItems =listItems ;
        this.context =context ;

    }


    @NonNull
    @Override
    public My_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_shop_items,parent,false);

        return new My_holder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull My_holder holder, int position) {


        Top_shop_itemlist listItem =listItems.get(position);
        holder.textView.setText("Shop Name");

        Picasso.get()
                .load("http://"+listItem.getThumb())
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
        public TextView range ;
        public ImageView imageView ;


        public My_holder(View itemView) {
            super(itemView);

            textView =itemView.findViewById(R.id.text) ;
            range =itemView.findViewById(R.id.range) ;
            imageView =itemView.findViewById(R.id.image) ;

            YoYo.with(Techniques.FlipInY)
                    .duration(1000)
                    .repeat(0)
                    .playOn(itemView.findViewById(R.id.linear_range));
        }
    }


}
