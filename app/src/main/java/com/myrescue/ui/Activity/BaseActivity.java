package com.myrescue.ui.Activity;

import android.app.Activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.myrescue.R;
import com.myrescue.observer.MyBroadcastReceiver;



public class BaseActivity extends Activity {

    private BDLocation bdLocation = new BDLocation();
    protected BaiduMap mBaiduMap;
    protected final double latitude = bdLocation.getLatitude();
    protected final double longitude = bdLocation.getLongitude();
    protected LatLng latLng = new LatLng(latitude, longitude);
    protected MyBroadcastReceiver receiver;

    private BitmapDescriptor bitmap;
    protected MapView mMapView;

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activiti_mapv);
        mMapView = (MapView) findViewById(R.id.mapb);
        mBaiduMap = mMapView.getMap();
//        普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
//        开启交通图
        mBaiduMap.setTrafficEnabled(true);
//       开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        receiver = new MyBroadcastReceiver();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomTo(12);
        mBaiduMap.setMapStatus(mapStatusUpdate);
//        是否显示缩放按钮
        this.mMapView.showZoomControls(true);
//      设置当前位置
        MapStatusUpdate mapStatusUpdate_circle = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.setMapStatus(mapStatusUpdate_circle);
        //显示指南针
        mBaiduMap.getUiSettings().setCompassEnabled(true);
        IntentFilter filter = new IntentFilter();
        // 网络错误
        filter.addAction(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR);
        // 效验key失败
        filter.addAction(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
        registerReceiver(receiver, filter);

    }
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
