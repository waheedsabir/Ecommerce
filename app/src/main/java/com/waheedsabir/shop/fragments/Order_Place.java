package com.waheedsabir.shop.fragments;


import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;
import com.waheedsabir.shop.Cart;
import com.waheedsabir.shop.Cod;
import com.waheedsabir.shop.ListItem;
import com.waheedsabir.shop.My_Adpater;
import com.waheedsabir.shop.R;
import com.waheedsabir.shop.database.MyHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Order_Place extends Fragment {
    int i =1 ;
String price_prefix ;
public int price_change = 0;
TextView count ;

//database
private SQLiteDatabase mySQLDb;

    ImageView imageView ;
    ImageButton decrease, increase ;
    TextView price ,description;
    Button btn ;
 String nm ;
 String id ;
 LinearLayout product_id ;
    MyHelper helper ;

    public Order_Place() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_order__place, container, false);
        price=v.findViewById(R.id.price);
        description=v.findViewById(R.id.description);
        imageView =v.findViewById(R.id.thumb);
        decrease =v.findViewById(R.id.decrease);
        increase =v.findViewById(R.id.increase);
        product_id =v.findViewById(R.id.product_id);
        product_id =v.findViewById(R.id.product_id);
        count =v.findViewById(R.id.count);
        product_id.bringToFront();

        helper = new MyHelper(getContext());




        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               i =   i+1;


                count.setText(""+i);
                price.setText(""+Integer.parseInt(price_prefix)*i);

                //animation code

            animatioIN();

            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>1) {

                    i = i - 1;
                    count.setText("" + i);
                    price.setText("" + Integer.parseInt(price_prefix) * i);
                //animation code
             animatioDC();
                }

            }
        });

        btn= v.findViewById(R.id.order);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);


                  SharedPreferences.Editor ed =sharedPreferences.edit();
                  ed.putString("id",id);
                  ed.putString("price",nm);
                  ed.apply();


            //    startActivity(new Intent(getContext(), Cod.class));
              long result =   helper.addtocard("name" , "id" ,"1123","shop name ","http","10",nm,"working fine" ,"midina colony ","03034486786");


                Toast.makeText(getActivity(), "added", Toast.LENGTH_SHORT).show();





            }
        });



        load();


        return  v ;


    }

    private void animatioIN() {
        YoYo.with(Techniques.FadeInLeft)
                .duration(300)
                .repeat(0)
                .playOn(price);

        YoYo.with(Techniques.FadeInDown)
                .duration(400)
                .repeat(0)
                .playOn(count);

    }
    private void animatioDC() {
        YoYo.with(Techniques.FadeInRight)
                .duration(300)
                .repeat(0)
                .playOn(price);

        YoYo.with(Techniques.FadeInUp)
                .duration(400)
                .repeat(0)
                .playOn(count);

    }


    public void load (){
        Toast.makeText(getContext(), "Loading...", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);



        StringRequest stringRequest = new StringRequest("http://waheedsabir.com/shop/api_product_detail.php?id="+sharedPreferences.getString("id",null), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {

                    JSONArray jsonArray = new JSONArray(response);
                    //  JSONObject js = new JSONObject(response);

                    // JSONArray array  = js.getJSONArray("feed");


                    //  listItems.clear();
                    for (int i = 0 ; i<jsonArray.length() ; i++){
                        JSONObject jsObj = jsonArray.getJSONObject(i);
                        price_prefix= jsObj.getString("price");
                        nm = jsObj.getString("price");
                        id = jsObj.getString("id");
                        price.setText(nm);
                        description.setText(jsObj.getString("description"));



                        Picasso.get()
                                .load("http://waheedsabir.com/shop/"+jsObj.getString("thumb"))
                                .placeholder(R.drawable.logo)
                                .error(R.drawable.logo)
                                .into(imageView);



                    }



                } catch (JSONException e) {
                    e.printStackTrace();
                }




                Log.d("Responce",response);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //      Toast.makeText(getContext(), ""+error, Toast.LENGTH_LONG).show();

            }
        }


        );


        RequestQueue re = Volley.newRequestQueue(getContext());
        re.add(stringRequest);


    }



}
