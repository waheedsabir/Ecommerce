package com.waheedsabir.shop.fragments;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chootdev.recycleclick.RecycleClick;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.waheedsabir.shop.ListItem;
import com.waheedsabir.shop.Main;
import com.waheedsabir.shop.My_Adpater;
import com.waheedsabir.shop.R;
import com.waheedsabir.shop.ViewPagerAdapter;
import com.waheedsabir.shop.adapters.Top_shop_Adpater;
import com.waheedsabir.shop.services.Top_shop_itemlist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    RecyclerView.Adapter adapter ;
    private List<Top_shop_itemlist> listItems;

    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor ed ;

    RecyclerView recyclerView ;
    ViewPager viewPager ;
    EditText editText ;
    private ProgressBar spinner;

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {





        View v = inflater.inflate(R.layout.home, container, false);
        //progress_Bar
        spinner = (ProgressBar)v.findViewById(R.id.progress);


        //slider code start
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getContext());
        viewPager.setAdapter(viewPagerAdapter);
        recyclerView  = v.findViewById(R.id.recyclerView);
        ImageButton mic = v.findViewById(R.id.mic);
        editText= v.findViewById(R.id.search);

        //timer for slider
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new timer_for_slider(),2000 , 4000);

        //slider code end

        //animation code start
        YoYo.with(Techniques.Bounce)
                .duration(1000)
                .repeat(300)
                .playOn(v.findViewById(R.id.mic));
        YoYo.with(Techniques.Pulse)
                .duration(1000)
                .repeat(300)
                .playOn(v.findViewById(R.id.search_button));


        //animation code end





        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///Google Voice code here
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

                try {
                    startActivityForResult(intent, 1);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getContext(), "Oops! Your device doesn't support Speech to Text",Toast.LENGTH_SHORT).show();
                }
                //Google voice end here
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));

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
                Shop_detail shop_detail = new Shop_detail() ;
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace( R.id.frame , shop_detail  ).addToBackStack("my_fragment").commit();




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

                        Top_shop_itemlist list_items = new Top_shop_itemlist(
                                jsObj.getString("id"),
                                jsObj.getString("thumb"),
                                jsObj.getString("name"),
                                jsObj.getString("price"),
                                jsObj.getString("images")




                        );


                        listItems.add(list_items);


                    }
                    adapter= new Top_shop_Adpater(listItems,getContext());
                    recyclerView.setAdapter(adapter);

                    spinner.setVisibility(View.GONE);


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


public class timer_for_slider extends TimerTask{


    @Override
    public void run() {
        Home.this.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (viewPager.getCurrentItem()==0 ){

                    viewPager.setCurrentItem(1);
                }else if(viewPager.getCurrentItem()==1) {
                    viewPager.setCurrentItem(2);
                }
                else{
                    viewPager.setCurrentItem(0);

                }
            }
        });
    }
}


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1: {
                if (resultCode == Activity.RESULT_OK && null != data) {
                    String yourResult = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS).get(0);
                  //  Toast.makeText(getContext(), yourResult, Toast.LENGTH_SHORT).show();
                    editText.setText(yourResult);
                }
                break;
            }
        }
    }
}
