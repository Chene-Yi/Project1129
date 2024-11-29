package com.example.project1129;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Bundle;
import android.content.Intent;

public class MyService extends Service {

    static Boolean flag = false;

    private int h=  0, m = 0, s = 0;

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID) {
        flag = intent.getBooleanExtra("flag", false);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (flag) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    s++;
                    if (s >= 60) {
                        s = 0;
                        m++;
                        if (m >= 60) {
                            m = 0;
                            h++;
                        }
                    }

                    Intent i = new Intent("MyMessage");
                    Bundle bundle = new Bundle();
                    bundle.putInt("H", h); // 時
                    bundle.putInt("M", m); // 分
                    bundle.putInt("S", s); // 秒
                    i.putExtras(bundle);    // 將資料封裝到 Intent
                    sendBroadcast(i);
                }
            }
        }).start();

        return START_STICKY;
    }

    public MyService() {
    }

}