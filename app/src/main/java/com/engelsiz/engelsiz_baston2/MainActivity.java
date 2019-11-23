package com.engelsiz.engelsiz_baston2;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.engelsiz.engelsiz_baston2.book_page.BookListActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    CustomAdapter custmAdapter;
    Intent intent;
    final List<Listing> elements = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        elements.add(new Listing("Birlikte Yürüyelim"));
        elements.add(new Listing("Haritalardan Yardım Al"));
        elements.add(new Listing("Kitap Dinleyelim"));
        elements.add(new Listing("Güncel Medya"));
        elements.add(new Listing("Bilgilendirme"));
        elements.add(new Listing("Destek"));

        listView = findViewById(R.id.listView);
        custmAdapter = new CustomAdapter(MainActivity.this, elements);

        listView.setAdapter(custmAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    intent = new Intent(getApplicationContext(), NavigationActivity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("geo:39.7668,30.5410"));
                    startActivity(intent);
                } else if (position == 2) {
                    intent = new Intent(getApplicationContext(), BookListActivity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    intent = new Intent(getApplicationContext(), NewspaperActivity.class);
                    intent.putExtra("temp","1");
                    startActivity(intent);
                } else if (position == 4) {
                    intent = new Intent(getApplicationContext(), InformationActivity.class);
                    startActivity(intent);
                } else if (position == 5) {
                    intent = new Intent(getApplicationContext(), SupportActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    public void go_newPage(View view) {
    }
}
