package com.example.irite.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
      return mbind;
    }

    private IMyAidlInterface.Stub mbind = new IMyAidlInterface.Stub() {

        public void yen_to_don(int yen) throws RemoteException {
            int don = 0;
            don = yen * 200;
            Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(don), Toast.LENGTH_SHORT);
            toast.show();
        }
    };

}
