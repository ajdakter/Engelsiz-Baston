package com.engelsiz.engelsiz_baston2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class NavigationActivity extends AppCompatActivity {

    TextView tvInfo, tvNavigation;
    DatabaseReference read, myref;
    FirebaseDatabase firebaseDatabase;
    ToggleButton toggleButton;
    Long counter;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation);

        toggleButton = findViewById(R.id.toggleButton);
        tvInfo = findViewById(R.id.tvInfo);
        tvNavigation = findViewById(R.id.tvNavigationValue);
        tvNavigation.setText("");
        firebaseDatabase = FirebaseDatabase.getInstance();

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                final Handler handler = new Handler();
                Timer  timer = new Timer();
                if (isChecked) {
                    tvInfo.setText("Açık ");
                    TimerTask timerTask = new TimerTask() {
                        @Override
                        public void run() {

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    refresh();
                                }
                            });
                        }
                    };
                    timer.schedule(timerTask,1000,1000);
                }
                else {
                    tvInfo.setText("Kapalı ");

                }
            }
        });

    }

    private void refresh() {

        myref = firebaseDatabase.getReference().child("ESP8266_Test").child("Push").child("Int");
        query = myref.orderByKey().limitToLast(1);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    String myPlaces = data.getValue().toString();
                    Integer value = Integer.parseInt(myPlaces);
                    if(value<=50 || value >=40){
                        tvNavigation.setText("50-40 cm aralığında bir engel algılandı...");
                    }
                    else if(value<=40 || value >=30){
                        tvNavigation.setText("50-40 cm aralığında bir engel algılandı...");
                    }
                    else if(value<=30 || value >=20){
                        tvNavigation.setText("50-40 cm aralığında bir engel algılandı...");
                    }
                    else if(value<=20 || value >=10){
                        tvNavigation.setText("50-40 cm aralığında bir engel algılandı...");
                    }
                    else if(value<=10 || value >=50){
                        tvNavigation.setText("50-40 cm aralığında bir engel algılandı...");
                    }

                    if (value <= 100 && value > 0) {
                        Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        vib.vibrate(500);
                        Toast.makeText(getApplicationContext(), "Vibrate...", Toast.LENGTH_LONG).show();
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void go_backPage(View view) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        myref = firebaseDatabase.getReference().child("ESP8266_Test").child("Push");
        myref.removeValue();
        onBackPressed();
    }
}