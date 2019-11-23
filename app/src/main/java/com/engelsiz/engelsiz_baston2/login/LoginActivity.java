package com.engelsiz.engelsiz_baston2.login;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.engelsiz.engelsiz_baston2.MainActivity;
import com.engelsiz.engelsiz_baston2.R;
import com.engelsiz.engelsiz_baston2.login.finger_printer.FingerPrinterActivity;


public class LoginActivity extends AppCompatActivity {

    SharedPreferences prefs;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStart", true);

        if (firstStart) {
            showStartDialog();
        }
    }

    public void go_newPage(View view) {

        if(view.getId()== R.id.gotoFingerPrinter){
           intent = new Intent(getApplicationContext(), FingerPrinterActivity.class);
           //intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

        }

    }
    private void showStartDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Dikkat")
                .setMessage("Uygulama hakkında genel bilgi, nasıl kullanacağınız ve dikkat edilecek noktaları uygulamamızın 'Bilgilendirme' kısmında detaylıca anlatılmaktadır. \n" +

                        "Lütfen uygulamayı kullanmaya başlamadan önce inceleyiniz. Bu bildirimi ise telefonunuzun herhangi bir yerine tıkladığınızda kapatabilirsiniz.\n" )
                       .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();

        prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }




}
