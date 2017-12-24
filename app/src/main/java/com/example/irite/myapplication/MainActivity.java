package com.example.irite.myapplication;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface myService;
    Button btn;
    EditText txt;
    private ServiceConnection ServiceConn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            myService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button);
        txt = (EditText) findViewById(R.id.text);
        Intent intent = new Intent(MainActivity.this, MyService.class);
        intent.setAction(IMyAidlInterface.class.getName());
        intent.setPackage("com.example.irite.myapplication");
        bindService(
                intent,
                ServiceConn,
                Context.BIND_AUTO_CREATE
        );


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int input = Integer.parseInt(txt.getText().toString());
                try {
                    myService.yen_to_don(input);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}