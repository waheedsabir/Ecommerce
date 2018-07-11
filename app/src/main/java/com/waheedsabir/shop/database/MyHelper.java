package com.waheedsabir.shop.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.icu.text.LocaleDisplayNames;
import android.util.Log;
import android.widget.Toast;

import com.waheedsabir.shop.model.Cart_GetSet;

import java.util.ArrayList;
import java.util.List;

public class MyHelper extends SQLiteOpenHelper {

    List<Cart_GetSet> list = new ArrayList<>();
    public static final String db_name = "cart.db";

    Context context;

    public MyHelper(Context context) {
        super(context, db_name, null, 1);


    }


    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL("create table cart (_id integer primary key , p_name text ,p_id text ,p_shop_id text  ,p_shop_name text, p_image text , p_quanity text ,p_price text , p_detail text ,p_shop_address text ,p_shop_contact text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public long addtocard(String p_name, String pr_id, String p_shop_id,String p_shop_name, String p_image, String p_quanity, String p_price, String p_detail, String p_shop_address, String p_shop_contact) {


        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("p_name", "Ladies Shoes");
        cv.put("p_id", "1235");
        cv.put("p_shop_id", "786");
        cv.put("p_shop_name", "punjab elec");
        cv.put("p_image", "https://fiverr-res.cloudinary.com/images/t_main1,q_auto,f_auto/gigs/7462077/original/45b79a988151cc31b17f1d73f8ca3fe0dd5f1bfb/design-a-simple-logo-with-an-illustration.jpg");
        cv.put("p_quanity", "12");
        cv.put("p_price", p_price);
        cv.put("p_detail", "Detail of the produc");
        cv.put("p_shop_address", "Madina Colony Mananwala");
        cv.put("p_shop_contact", "03034486786");

        long x = sqLiteDatabase.insert("cart", null, cv);

        return x;
    }


    public List<Cart_GetSet> getAllcartitems() {


        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM cart", null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String p_name= cursor.getString(1);
                String p_id= cursor.getString(2);
                String p_shop_id= cursor.getString(3);
                String p_shop_name= cursor.getString(4);
                String p_image= cursor.getString(5);
                String p_quanity= cursor.getString(6);
                String p_price= cursor.getString(7);
                String p_detail= cursor.getString(8);
                String p_shop_address= cursor.getString(9);
                String p_shop_contact= cursor.getString(10);

                Cart_GetSet cart_getSet = new Cart_GetSet(id,p_name,p_id,p_shop_id,p_shop_name,p_image,p_quanity,p_price,p_detail,p_shop_address,p_shop_contact);
                list.add(cart_getSet);



                total(p_price);
                back();
            } while (cursor.moveToNext());




            }
        return list;

        }

    public  int  deleteEntry(long rowId) {
        SQLiteDatabase delete = getWritableDatabase();

        Log.d("database",""+rowId);
       int result =  delete.delete("cart", "_id="  + rowId, null);
      //  delete.delete("cart" , "id="+rowId, null);
        Log.d("database_q",""+result);

        return result;
        }
        int total =0 ;

    public  void total(String value) {



        Log.d("database_q",""+Integer.parseInt(value));
        total = total +Integer.parseInt(value);
        Log.d("database_q",""+total);





    }
    public int back(){

        int t= total;

        return t;
    }


        public  long  get_cart_count() {
        SQLiteDatabase count_value = getWritableDatabase();

        return android.database.DatabaseUtils.queryNumEntries(count_value, "cart");

        }

}


