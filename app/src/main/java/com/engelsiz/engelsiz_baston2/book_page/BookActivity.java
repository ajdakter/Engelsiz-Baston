package com.engelsiz.engelsiz_baston2.book_page;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.engelsiz.engelsiz_baston2.R;


public class BookActivity extends AppCompatActivity {
    TextView yourTextview,tvTitle;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        yourTextview = (TextView) findViewById(R.id.info_textView);
        tvTitle =findViewById(R.id.tv_title);
        yourTextview.setMovementMethod(new ScrollingMovementMethod());

        intent = getIntent();
        String story = intent.getStringExtra("story");
        yourTextview.setText(story);
        String title = intent.getStringExtra("title");
        tvTitle.setText(title);


    }

    public void go_back(View view) {
       onBackPressed();
    }

}
