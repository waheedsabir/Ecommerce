package com.waheedsabir.shop;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.waheedsabir.shop.adapters.Cart_Adpater;
import com.waheedsabir.shop.adapters.Top_shop_Adpater;
import com.waheedsabir.shop.model.Cart_GetSet;

import java.util.ArrayList;
import java.util.List;
import com.waheedsabir.shop.database.MyHelper ;
import com.waheedsabir.shop.services.Top_shop_itemlist;

public class Cart extends AppCompatActivity {
ListView listView;
List<Cart_GetSet>  cart_getSets ;
    ArrayAdapter <Cart_GetSet> arrayAdapter ;
    RecyclerView.Adapter adapter ;
    RecyclerView recyclerView ;



    public static Button proceed , total_value ;



    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor ed ;



MyHelper my_helper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        listView=findViewById(R.id.list_cart);
        my_helper  =new MyHelper(this);
        recyclerView =findViewById(R.id.cart_recycle_view);

        total_value=findViewById(R.id.total);
        proceed=findViewById(R.id.proceed);

        cart_getSets= my_helper.getAllcartitems();
      //  arrayAdapter= new ArrayAdapter<Cart_GetSet>(this, android.R.layout.simple_list_item_1,cart_getSets);
      //  listView.setAdapter(arrayAdapter);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));


        adapter= new Cart_Adpater(cart_getSets,getApplicationContext());
        recyclerView.setAdapter(adapter);



        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                startActivity(new Intent(Cart.this , Cod.class));
            }
        });



    }


    public static void grant_price(int g) {
        Log.d("grant",""+g );
        total_value.setText(""+g);

    }
}
