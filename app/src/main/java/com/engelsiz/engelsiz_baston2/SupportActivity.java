package com.engelsiz.engelsiz_baston2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SupportActivity extends AppCompatActivity implements View.OnClickListener {
  Button recordVoiceButton;
  Button stopVoiceButton;
  Button playVoiceButton;
  Button sendVoiceButton;
  private MediaRecorder recorder;
  private MediaPlayer player;
  private final String filepath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/record.mp4";

  FirebaseStorage firebaseStorage;

  private static final int REQUEST_AUDIO_PERMISSION_CODE = 5000;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_support);

    recordVoiceButton = findViewById(R.id.btn_start_record);
    recordVoiceButton.setOnClickListener(this);
    stopVoiceButton = findViewById(R.id.btn_end_record);
    stopVoiceButton.setOnClickListener(this);
    playVoiceButton = findViewById(R.id.btn_listen);
    playVoiceButton.setOnClickListener(this);

    sendVoiceButton = findViewById(R.id.btn_sent);
    sendVoiceButton.setOnClickListener(this);

    firebaseStorage = FirebaseStorage.getInstance();
  }

  @Override
  public void onClick(View view) {
    if (view == recordVoiceButton) {
      if (checkPermissions()) { // izin konrolü yapılır ve daha önce verilmişse record işlmei yapılır.
        startRecording();
      } else {                   // verilmemişse izin isteği yapılır.
        requestPermissions();
        startRecording();
      }
    } else if (view == stopVoiceButton) {
      stopRecording();
    } else if (view == playVoiceButton) {
      startPlaying();
    } else if (view == sendVoiceButton) {
      sendVoice();

    }
  }

  private void sendVoice() {
    Uri uri = Uri.fromFile(new File(filepath));

    FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
    StorageReference storageRef = firebaseStorage.getReference();

    if (filepath != null) {
      StorageReference rf = firebaseStorage.getReferenceFromUrl("gs://engelsiz-baston-92e05.appspot.com/");
      storageRef.child("SupportMessage").child(uri.getLastPathSegment()).putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        // Reference verdiğimiz yere dosyayı yükleme işlemini gerçekleştirecektir.
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

          Toast.makeText(SupportActivity.this, "Ses Dosyası Başarılı Bir Şekilde Kaydedildi..", Toast.LENGTH_SHORT).show();
          onBackPressed(); // geldiği sayfaya geri dönmesini sağlayan hazır metot..
        }
      }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
          Toast.makeText(SupportActivity.this, "Ses Dosyası Kaydedilemedi!!..", Toast.LENGTH_SHORT).show();
        }
      });
    } else {
      //you can display an error toast
      Toast.makeText(getApplicationContext(), "Hata ", Toast.LENGTH_SHORT).show();

    }

  }

  private void startRecording() {

    recorder = new MediaRecorder();
    recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
    recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
    recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
    recorder.setOutputFile(filepath);

    try {
      recorder.prepare(); // prepare metodu ile MediaRecorder sınıfı verilen parametrelerle kayıt işlemi için hazırlanır.
      recorder.start();   // Bir sorun oluşmazsa start metodu ile kaydı başlatabiliriz.
      Toast.makeText(getApplicationContext(), "Kayıt Yapılıyor", Toast.LENGTH_LONG).show();
    } catch (IllegalStateException e) {
      //bir sorun oluşursa IllegalStateException hatası ile sorunun sebebi bize bildirilir.
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void stopRecording() {
    if (recorder != null) {
      Toast.makeText(getApplicationContext(), "Kayıt Durduruldu", Toast.LENGTH_LONG).show();
      recorder.stop();
      recorder.reset();
      recorder.release();
      recorder = null;
    }
  }

  private void startPlaying() {
    player = new MediaPlayer();
    player.setVolume(1.0f, 1.0f);
    try {
      player.setDataSource(filepath);
      player.prepare();
      player.start();
      Toast.makeText(getApplicationContext(), "Kayıt Çalıyor", Toast.LENGTH_LONG).show();
      player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {


        @Override
        public void onCompletion(MediaPlayer arg0) {
          player.stop();
          player.release();
          player = null;
        }
      });
    } catch (Exception e) {
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    switch (requestCode) {
      case REQUEST_AUDIO_PERMISSION_CODE:
        if (grantResults.length > 0) {
          //grantResult[0] birden fazla izin istenilmiş olabilir o yuzden parametreler olarak gelen grantresult değeri dizi olacaktır
          boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
          boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;
          if (permissionToRecord && permissionToStore) { // ikisi de true ise..
            Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_LONG).show();
            // PackageManager.PERMISSION_GRANTED : Bu int değeri iznin verilmiş olduğunu belirtir.
          } else {
            Toast.makeText(getApplicationContext(), "Permission Denied", Toast.LENGTH_LONG).show();

          }
        }
        break;
    }
  }

  public boolean checkPermissions() {
    int result = ContextCompat.checkSelfPermission(getApplicationContext(), WRITE_EXTERNAL_STORAGE);
    int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), RECORD_AUDIO);
    return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;

  }

  private void requestPermissions() {
    ActivityCompat.requestPermissions(SupportActivity.this, new String[]{RECORD_AUDIO, WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
  }


}
