package com.engelsiz.engelsiz_baston2;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.engelsiz.engelsiz_baston2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;


public class InformationActivity extends AppCompatActivity {

  TextView listView;
  CustomInfoAdapter custmAdapter;
  Intent intent;
  DatabaseReference read, myRef;
  FirebaseDatabase firebaseDatabase;
  TextView tvBook;
  final List<Listing> elements = new ArrayList<>();
  String value;

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_information);

    elements.add(new Listing("Ayna"));
    elements.add(new Listing("Hayatın İçinden"));
    elements.add(new Listing("Yeniden Hayata"));
    elements.add(new Listing("Kayıp Kasaba"));
    elements.add(new Listing("Yalanla Kurulan Dünya"));


    listView = findViewById(R.id.listView);
    listView.setMovementMethod(new ScrollingMovementMethod());
    //tvBook = findViewById(R.id.info_textView);
    read = FirebaseDatabase.getInstance().getReference().child("Hikayeler").child("No:1");
    firebaseDatabase = FirebaseDatabase.getInstance();


  }
  public void go_back(View view) {
    onBackPressed();
  }
}
