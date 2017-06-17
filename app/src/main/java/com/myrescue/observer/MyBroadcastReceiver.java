package com.myrescue.observer;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.baidu.mapapi.SDKInitializer;


/**
 * Created by Men on 2017/5/12.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        // 网络错误
        if (action.equals(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR)) {
            Toast.makeText(context, "无法连接网络", Toast.LENGTH_SHORT).show();
            // key效验失败
        } else if (action.equals(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR)) {
            Toast.makeText(context, "百度地图key效验失败", Toast.LENGTH_SHORT).show();

        }
    }
}