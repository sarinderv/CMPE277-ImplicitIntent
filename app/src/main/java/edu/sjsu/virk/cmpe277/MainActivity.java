package edu.sjsu.virk.cmpe277;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PHONE_CALL = 1;
    EditText website, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        website = (EditText) findViewById(R.id.editText);
        phone = (EditText) findViewById(R.id.editText2);
    }

    public void go_to_website(View view) {
        String s = website.getText().toString();
        if (!s.startsWith("https://") && !s.startsWith("http://")){
            s = "http://" + s;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
        startActivity(intent);
    }

    public void call_to_phone(View view) {
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED)
            phone_call_intent();
        else
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_PHONE_CALL);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults) {
        if (requestCode == REQUEST_PHONE_CALL)
            phone_call_intent();
    }

    private void phone_call_intent() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:".concat(phone.toString())));
        startActivity(intent);
    }
}
