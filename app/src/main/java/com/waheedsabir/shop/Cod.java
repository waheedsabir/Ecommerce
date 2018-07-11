package com.waheedsabir.shop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.waheedsabir.shop.database.MyHelper;

import java.util.HashMap;
import java.util.Map;

public class Cod extends AppCompatActivity {
    SharedPreferences sharedPreferences ;

    EditText getname,getcontact , getaddress ,id_p ,pri ;

    TextView textView ;
    private SQLiteDatabase mySQLDb;

    TextView txt;


    private static final String url = "http://waheedsabir.com/shop/api_order.php";
    public static final String id = "id_p";
    public static final String price = "price";
    public static final String name = "name";
    public static final String address = "address";
    public static final String contact = "contact";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cod);

       sharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE);
        getname= findViewById(R.id.name);
        getaddress= findViewById(R.id.address);
        getcontact= findViewById(R.id.contact);
        id_p= findViewById(R.id.id_p);
        pri= findViewById(R.id.pri);

id_p.setText("Product ID  "+sharedPreferences.getString("id",null));
pri.setText("Price: "+sharedPreferences.getString("price",null));

//database
        MyHelper helper = new MyHelper(this);
        mySQLDb = helper.getWritableDatabase();

    }

    public void confirm(View view) {
       startActivity(new Intent(this,Cart.class));

        Cursor c = mySQLDb.query("cart",null,null,null,null,null,null);

        while(c.moveToNext()) {
            Toast.makeText(this, "" + c.getString(c.getColumnIndex("p_name")  ), Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "" + c.getString(c.getColumnIndex("p_id")  ), Toast.LENGTH_SHORT).show();

            submit();



        }



    }

    public void submit() {

      //  Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://waheedsabir.com/shop/api_order.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Cod.this,response,Toast.LENGTH_LONG).show();


                    }
                },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                })


        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put("name","working");
                params.put("nameb","working");





                return params;
            }
        };





        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);






    }


}
