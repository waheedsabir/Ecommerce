package com.waheedsabir.shop;



import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.waheedsabir.shop.database.MyHelper ;
import android.widget.Button;
import android.widget.Toast;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.waheedsabir.shop.fragments.Category;
import com.waheedsabir.shop.fragments.Home;
import com.waheedsabir.shop.fragments.Order_Place;
import com.waheedsabir.shop.map.Map;

import java.util.List;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

     private static int cart_count=0;

    RecyclerView.Adapter adapter ;
    private List<ListItem> listItems;

ViewPager viewPager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);








        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Home home = new Home() ;
        FragmentManager fragmentManager = getSupportFragmentManager() ;
        fragmentManager.beginTransaction().replace( R.id.frame , home  ).commit();
    }



    @Override
    public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (getFragmentManager().getBackStackEntryCount() > 0)
            getFragmentManager().popBackStack();
        else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
          getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.cart);
        menuItem.setIcon(Converter.convertLayoutToImage(this,cart_count,R.drawable.cart));

            // database cart counter
            MyHelper get = new MyHelper(this);
          long val =   get.get_cart_count();
          android.util.Log.d("counter ", ""+val);
          cart_count = (int) val ;
            invalidateOptionsMenu();

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if (id == R.id.cart) {


          startActivity(new Intent(Main.this , Cart.class));

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.home) {
            // Handle the camera action
            Category category = new Category() ;
            FragmentManager fragmentManager = getSupportFragmentManager() ;
            fragmentManager.beginTransaction().replace( R.id.frame , category  ).commit();

        } else if (id == R.id.profile) {

            startActivity(new Intent(Main.this , Developer.class));

        }  else if (id == R.id.order) {

            startActivity(new Intent(Main.this , About.class));

        }
        else if (id == R.id.cart) {

            startActivity(new Intent(Main.this , Cart.class));

        }
        else if (id == R.id.mylocation) {

            startActivity(new Intent(Main.this , Map.class));

        }
        else if (id == R.id.Logout) {

            startActivity(new Intent(Main.this , About.class));

        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//Auto image slider code here


}
