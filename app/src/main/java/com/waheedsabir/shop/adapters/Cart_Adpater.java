package com.waheedsabir.shop.adapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;
import com.waheedsabir.shop.Cart;
import com.waheedsabir.shop.R;
import com.waheedsabir.shop.database.MyHelper;
import com.waheedsabir.shop.model.Cart_GetSet;
import com.waheedsabir.shop.services.Top_shop_itemlist;

import java.util.List;
import java.util.Random;

public class Cart_Adpater extends RecyclerView.Adapter<Cart_Adpater.My_holder> {
    private List<Cart_GetSet> listItems ;
    private Context context ;

    int total_price= 0 ;




    public Cart_Adpater(List<Cart_GetSet> listItems, Context context) {
        this.listItems =listItems ;
        this.context =context ;

    }


    @NonNull
    @Override
    public My_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items,parent,false);

        return new My_holder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull final My_holder holder, final int position) {


        final Cart_GetSet listItem =listItems.get(position);
        holder.product_id.setText(listItem.getP_id());
        holder.shop_name.setText(listItem.getP_shop_name());
        holder.title.setText(listItem.getP_name());
        holder.quantity.setText(listItem.getP_quanity());
        holder.contact.setText(listItem.getP_shop_contact());
        holder.price.setText(listItem.getP_price());

        total_price= total_price+Integer.parseInt(listItem.getP_price());

     //   Toast.makeText(context, ""+total_price, Toast.LENGTH_SHORT).show();


        Cart.grant_price(total_price);



        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Toast.makeText(context, ""+listItem.getP_id(), Toast.LENGTH_SHORT).show();


            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyHelper myHelper = new MyHelper(context);
              int resut =   myHelper.deleteEntry(listItem.get_id());


                total_price=0 ;

                if(resut>0 ){
                  remove_item(position);




                  Toast.makeText(context, "Remove from cart", Toast.LENGTH_SHORT).show();
              }else{
                  Toast.makeText(context, "Some thing wen wrong", Toast.LENGTH_SHORT).show();


              }


            //    Toast.makeText(context, ""+listItem.get_id(), Toast.LENGTH_SHORT).show();


            }
        });

        Picasso.get()
                .load(listItem.getP_image())
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo)
                .into(holder.imageView);

      //  Picasso.get().load("http://"+listItem.thumb).into(holder.imageView);
        //Toast.makeText(context, "http://"+listItem.thumb, Toast.LENGTH_SHORT).show();
    }

    private void remove_item(int position) {


        listItems.remove(position);


         notifyDataSetChanged();
        notifyItemRemoved(position);

        Cart.grant_price(total_price);





     //   Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class My_holder extends RecyclerView.ViewHolder{


        public TextView product_id , shop_name ,title , price , quantity , contact  ;
        public TextView range ;
        public ImageView edit ;
        public ImageView imageView ;
        public ImageView delete ;


        public My_holder(View itemView) {
            super(itemView);

            product_id =itemView.findViewById(R.id.product) ;
            title =itemView.findViewById(R.id.title) ;
            price =itemView.findViewById(R.id.price) ;
            quantity =itemView.findViewById(R.id.quantity) ;
            shop_name =itemView.findViewById(R.id.shop_name) ;
            contact =itemView.findViewById(R.id.contact) ;

            range =itemView.findViewById(R.id.range) ;
            edit =itemView.findViewById(R.id.edit) ;
            imageView =itemView.findViewById(R.id.image) ;
            delete =itemView.findViewById(R.id.delete) ;



            YoYo.with(Techniques.FlipInY)
                    .duration(1000)
                    .repeat(0)
                    .playOn(itemView.findViewById(R.id.product));


        }
    }



}
