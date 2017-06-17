package com.myrescue;
import android.app.Application;
import com.baidu.mapapi.SDKInitializer;
public class MainActivity extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(getApplicationContext());
    }

}