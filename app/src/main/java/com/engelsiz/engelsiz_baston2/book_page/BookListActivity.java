package com.engelsiz.engelsiz_baston2.book_page;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.engelsiz.engelsiz_baston2.Listing;
import com.engelsiz.engelsiz_baston2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.List;


public class BookListActivity extends AppCompatActivity {

    ListView listView;
    CustomBookAdapter custmAdapter;
    Intent intent;
    DatabaseReference read, myRef;
    FirebaseDatabase firebaseDatabase;
    TextView tvBook,tvTitle;
    final List<Listing> elements = new ArrayList<>();
    String value;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        elements.add(new Listing("Ayna"));
        elements.add(new Listing("Hayatın İçinden"));
        elements.add(new Listing("Yeniden Hayata"));
        elements.add(new Listing("Kayıp Kasaba"));
        elements.add(new Listing("Yalanla Kurulan Dünya"));


        listView = findViewById(R.id.listView);
        tvBook = findViewById(R.id.info_textView);
        tvTitle =findViewById(R.id.tv_title);
        read = FirebaseDatabase.getInstance().getReference().child("Hikayeler").child("No:1");
        firebaseDatabase = FirebaseDatabase.getInstance();


        custmAdapter = new CustomBookAdapter(BookListActivity.this, elements);
        listView.setAdapter(custmAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                progressDialog = new ProgressDialog(BookListActivity.this);
                progressDialog.setMessage("Devam eden işleminiz bulunmaktadır. Lütfen bekleyiniz..");
                progressDialog.show();

                if (position == 0) {
                    myRef = firebaseDatabase.getReference().child("Hikayeler").child("No:1").child("Ayna");

                    myRef.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            value = dataSnapshot.getValue().toString();
                            intent = new Intent(getApplicationContext(), BookActivity.class);
                            intent.putExtra("story", value.toString());
                            intent.putExtra("title", "Ayna");
                            startActivity(intent);
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });
                } else if (position == 1) {
                    myRef = firebaseDatabase.getReference().child("Hikayeler").child("No:1").child("Hayatın İçinden");

                    myRef.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            value = dataSnapshot.getValue().toString();
                            intent = new Intent(getApplicationContext(), BookActivity.class);
                            intent.putExtra("story", value.toString());
                            intent.putExtra("title", "Hayatın İçinden");
                            startActivity(intent);
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });
                } else if (position == 2)
                {
                        myRef = firebaseDatabase.getReference().child("Hikayeler").child("No:1").child("Yeniden Hayata");
                        myRef.addValueEventListener(new ValueEventListener() {

                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                value = dataSnapshot.getValue().toString();
                                intent = new Intent(getApplicationContext(), BookActivity.class);
                                intent.putExtra("story", value.toString());
                                intent.putExtra("title", "Yeniden Hayata");
                                startActivity(intent);
                                progressDialog.dismiss();
                            }

                            @Override
                            public void onCancelled(DatabaseError error) {
                            }
                        });
                }
                else if (position == 3)
                {
                    myRef = firebaseDatabase.getReference().child("Hikayeler").child("No:1").child("Kayıp Kasaba");
                    myRef.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            value = dataSnapshot.getValue().toString();
                            intent = new Intent(getApplicationContext(), BookActivity.class);
                            intent.putExtra("story", value.toString());
                            intent.putExtra("title", "Kayıp Kasaba");
                            startActivity(intent);
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });
                }
                else if (position == 4)
                {
                    myRef = firebaseDatabase.getReference().child("Hikayeler").child("No:1").child("Yalanla Kurulan Dünya");
                    myRef.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            value = dataSnapshot.getValue().toString();
                            intent = new Intent(getApplicationContext(), BookActivity.class);
                            intent.putExtra("story", value.toString());
                            intent.putExtra("title", "Yalanla Kurulan Dünya");
                            startActivity(intent);
                            progressDialog.dismiss();
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });
                }

            }
        });
    }

    public void go_back(View view) {

        onBackPressed();
    }
}
