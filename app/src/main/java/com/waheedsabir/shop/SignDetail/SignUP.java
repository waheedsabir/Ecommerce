package com.waheedsabir.shop.SignDetail;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.waheedsabir.shop.Main;
import com.waheedsabir.shop.R;

public class SignUP extends AppCompatActivity {
    SharedPreferences sharedPreferences ;
    EditText name , contact , address , lat , log ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.waheedsabir.shop.R.layout.sign_up);
        name=findViewById(R.id.NameSignUp) ;
        contact = findViewById(R.id.contact);
        address = findViewById(R.id.address);




        sharedPreferences  = getSharedPreferences("data",MODE_PRIVATE);
        address.setText(sharedPreferences.getString("user_address",""));


    }


    public void signup(View view) {

        startActivity(new Intent(SignUP.this, Main.class));
    }
}
