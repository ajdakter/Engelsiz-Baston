package com.engelsiz.engelsiz_baston2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class NewspaperActivity extends AppCompatActivity {

    String value="0";

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspaper);

    }

    public void goDetails(View view) {
        if(view.getId()== R.id.g_news){
            value="1";
            intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
            intent.putExtra("value", value);
            startActivity(intent);
        }
        else if(view.getId()==R.id.c_news){
            value="2";
            intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
            intent.putExtra("value", value);
            startActivity(intent);
        }
        else if(view.getId()==R.id.w_news){
            value="3";
            intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
            intent.putExtra("value", value);
            startActivity(intent);

        }



    }

    public void go_back(View view) {
        onBackPressed();
    }
}




