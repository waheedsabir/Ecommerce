package com.waheedsabir.shop.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chootdev.recycleclick.RecycleClick;
import com.waheedsabir.shop.ListItem;
import com.waheedsabir.shop.Main;
import com.waheedsabir.shop.My_Adpater;
import com.waheedsabir.shop.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Category extends Fragment {

    RecyclerView.Adapter adapter ;
    private List<ListItem> listItems;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor ed ;

    RecyclerView recyclerView ;

    public Category() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_category, container, false);



        recyclerView  = v.findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));

        load();


     /*   for(int i =0 ; i <10 ; i++ ){

            ListItem lis = new ListItem(
                    "head",
                    R.drawable.ic_action_name

            );
            listItems.add(lis) ;
        }*/



        // Inflate the layout for this fragment

        RecycleClick.addTo(recyclerView).setOnItemClickListener(new RecycleClick.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                sharedPreferences = getActivity().getSharedPreferences("data", Context.MODE_PRIVATE);
              ed = sharedPreferences.edit();


             //   Toast.makeText(getContext(), ""+listItems.get(position).getId(), Toast.LENGTH_SHORT).show();

                        ed.putString("id",listItems.get(position).getId());
                        ed.apply();
                    Order_Place category = new Order_Place() ;
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace( R.id.frame , category  ).addToBackStack("my_fragment").commit();




            }
        });

        return v ;



    }
    public void load (){
        Toast.makeText(getContext(), "Loading...", Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest("http://waheedsabir.com/shop/api_product_1.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                listItems = new ArrayList<>();

                try {

                    JSONArray jsonArray = new JSONArray(response);
                  //  JSONObject js = new JSONObject(response);

                   // JSONArray array  = js.getJSONArray("feed");


                  //  listItems.clear();
                    for (int i = 0 ; i<jsonArray.length() ; i++){
                        JSONObject jsObj = jsonArray.getJSONObject(i);

                        ListItem list_items = new ListItem(
                                jsObj.getString("id"),
                                jsObj.getString("thumb"),
                                jsObj.getString("name"),
                                jsObj.getString("price"),
                                jsObj.getString("images")




                        );


                        listItems.add(list_items);

                    }
                    adapter= new My_Adpater(listItems,getContext());
                    recyclerView.setAdapter(adapter);


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
