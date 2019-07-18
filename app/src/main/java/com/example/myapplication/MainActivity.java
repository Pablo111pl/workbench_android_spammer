package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    final int SEND_SMS_PERMISSION_REQUEST_CODE=1;

    EditText number;
    EditText message;
    Button send;
    EditText how_many;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        message=findViewById(R.id.message);
        number=findViewById(R.id.phone_number);
        how_many=findViewById(R.id.number);
        send=findViewById(R.id.send);


        send.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS)) {
            send.setEnabled(true);

        } else {

            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);
        }


    }


    public void onSend (View v){
    String smsNumber=number.getText().toString();
    String smsMessage=message.getText().toString();
        String how__many = how_many.getText().toString();

        if(smsNumber.length()<9 || smsNumber.length()>9 ){
            Toast.makeText(this,"Number must contain 9 digits", Toast.LENGTH_SHORT).show();
            return;
        }
    

        if(smsNumber==null || smsNumber.length()==0 ||
        smsMessage==null || smsMessage.length()==0  ||
                how__many==null || how__many.length()==0){
            Toast.makeText(this,"Add all field", Toast.LENGTH_SHORT).show();
        return;
        }
       Integer how_many_number=Integer.parseInt(how__many);
        if(checkPermission(Manifest.permission.SEND_SMS))
        {
            SmsManager smsManager=SmsManager.getDefault();
           while(how_many_number!=0){
                smsManager.sendTextMessage(smsNumber, null, smsMessage, null, null);
                Toast.makeText(this, "Message left "+how_many_number, Toast.LENGTH_SHORT).show();
               how_many_number--;
            }
        }
        else {
            Toast.makeText(this,"Fail", Toast.LENGTH_SHORT).show();

        }
    }

    public boolean checkPermission(String permission){

        int check= ContextCompat.checkSelfPermission(this,permission);
        return (check== PackageManager.PERMISSION_GRANTED);
    }

}
