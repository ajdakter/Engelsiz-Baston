package com.engelsiz.engelsiz_baston2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewsDetailsActivity extends AppCompatActivity {

    private Intent intent;
    TextView tv_title,tv_info;
    DatabaseReference read, myRef,myRef_story;
    FirebaseDatabase firebaseDatabase;
    String value;
    ImageView img;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);


        tv_title=findViewById(R.id.news_title);
        tv_info=findViewById(R.id.news_info);
        img=findViewById(R.id.newss_image);

        tv_info.setMovementMethod(new ScrollingMovementMethod());

        intent = getIntent();
        String story = intent.getStringExtra("value");



        progressDialog = new ProgressDialog(NewsDetailsActivity.this);
        progressDialog.setMessage("Devam eden işleminiz bulunmaktadır. Lütfen bekleyiniz..");
        progressDialog.show();

        read = FirebaseDatabase.getInstance().getReference().child("News").child("news");
        firebaseDatabase = FirebaseDatabase.getInstance();

        if(Integer.valueOf(story)==1){
            img.setImageResource(R.drawable.dolar);

            myRef = firebaseDatabase.getReference().child("News").child("news1").child("title");
            myRef_story = firebaseDatabase.getReference().child("News").child("news1").child("info");

            myRef.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    value = dataSnapshot.getValue().toString();
                    tv_title.setText(value);
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

            myRef_story.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    value = dataSnapshot.getValue().toString();
                    tv_info.setText(value);
                    progressDialog.dismiss();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });


    }
        else if(Integer.valueOf(story)==2){

            img.setImageResource(R.drawable.c_img);

            myRef = firebaseDatabase.getReference().child("News").child("news2").child("title");
            myRef_story = firebaseDatabase.getReference().child("News").child("news2").child("info");

            myRef.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    value = dataSnapshot.getValue().toString();
                    tv_title.setText(value);
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

            myRef_story.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    value = dataSnapshot.getValue().toString();
                    tv_info.setText(value);
                    progressDialog.dismiss();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }
        else if(Integer.valueOf(story)==3){

            img.setImageResource(R.drawable.img_world);

            myRef = firebaseDatabase.getReference().child("News").child("new3").child("title");
            myRef_story = firebaseDatabase.getReference().child("News").child("new3").child("info");

            myRef.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    value = dataSnapshot.getValue().toString();
                    tv_title.setText(value);
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

            myRef_story.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    value = dataSnapshot.getValue().toString();
                    tv_info.setText(value);
                    progressDialog.dismiss();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }

    }

    public void go_back(View view) {
        onBackPressed();
    }

    public void change(View view) {
        progressDialog = new ProgressDialog(NewsDetailsActivity.this);
        progressDialog.setMessage("Devam eden işleminiz bulunmaktadır. Lütfen bekleyiniz..");
        progressDialog.show();

        img.setImageResource(R.drawable.son);

        myRef = firebaseDatabase.getReference().child("News").child("new4").child("title");
        myRef_story = firebaseDatabase.getReference().child("News").child("new4").child("info");

        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                value = dataSnapshot.getValue().toString();
                tv_title.setText(value);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        myRef_story.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                value = dataSnapshot.getValue().toString();
                tv_info.setText(value);
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
