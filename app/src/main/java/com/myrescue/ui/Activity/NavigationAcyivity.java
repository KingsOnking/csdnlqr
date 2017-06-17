package com.myrescue.ui.Activity;
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.myrescue.R;
import com.myrescue.utils.PermissionUtil;
import com.myrescue.utils.overlayutil.DrivingRouteOverlay;
import java.util.ArrayList;
import java.util.List;
public class NavigationAcyivity extends Activity {
    private static final String TAG = "1";
    private static final String[] PERMISSIONS_CONTACT = new String[]{"requestPermission"};
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private BDLocation bdLocation = new BDLocation();
    private double latitude = bdLocation.getLatitude();
    private double longitude = bdLocation.getLongitude();
    private LatLng latLng = new LatLng(latitude, longitude);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiti_map);
        if (Build.VERSION.SDK_INT >= 23) {
            showContacts(mMapView);
        } else {
            searche();//导航方法
        }
        mMapView = (MapView) findViewById(R.id.map);
        mBaiduMap = mMapView.getMap();

    }

    /**
     * 驾车路线
     */
    private void searche() {

        RoutePlanSearch routePlanSearch = RoutePlanSearch.newInstance();
        routePlanSearch.setOnGetRoutePlanResultListener(new Mylistener());
        // 驾车路线搜索
        DrivingRoutePlanOption option = new DrivingRoutePlanOption();
        // 设置策略
        option.policy(DrivingRoutePlanOption.DrivingPolicy.ECAR_FEE_FIRST);
        // 设置起点
        BikingRoutePlanOption bikingRoutePlanOption = new BikingRoutePlanOption();
        PlanNode from = PlanNode.withLocation(latLng);
        bikingRoutePlanOption.from(from);
        option.from(from);
        // 设置终点
        PlanNode to = PlanNode.withLocation(latLng);
        option.to(to);
//         设置途经点
        List<PlanNode> list = new ArrayList<>();
        list.add(PlanNode.withLocation(new LatLng(40.061058, 116.621817)));
        option.passBy(list);
        routePlanSearch.drivingSearch(option);
    }
     public class Mylistener implements OnGetRoutePlanResultListener {
        @Override
        public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {}
        @Override
        public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {}
        @Override
        public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {}
        @Override
        public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {
            if (drivingRouteResult == null || drivingRouteResult.error == PoiResult.ERRORNO.RESULT_NOT_FOUND) {
                Toast.makeText(getApplicationContext(), "未查询到结果", Toast.LENGTH_SHORT).show();
                return;
            }else {
                DrivingRouteOverlay drivingRouteOverlay = new DrivingRouteOverlay(mBaiduMap);
                drivingRouteOverlay.setData(drivingRouteResult.getRouteLines().get(0));
                drivingRouteOverlay.addToMap();
                drivingRouteOverlay.zoomToSpan();
            }
        }
        @Override
        public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {
        }

        @Override
        public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

        }
    }
    public void showContacts(View v) {
        Log.i(TAG, "Show contacts button pressed. Checking permissions.");

        // Verify that all required contact permissions have been granted.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            // Contacts permissions have not been granted.
            Log.i(TAG, "Contact permissions has NOT been granted. Requesting permissions.");
            requestContactsPermissions(v);

        } else {

            // Contact permissions have been granted. Show the contacts fragment.
            Log.i(TAG,
                    "Contact permissions have already been granted. Displaying contact details.");
            searche();
        }
    }
    private void requestContactsPermissions(View v) {
        // BEGIN_INCLUDE(contacts_permission_request)
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                || ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_PHONE_STATE)
                ) {
            Log.i(TAG,
                    "Displaying contacts permission rationale to provide additional context.");
            // Display a SnackBar with an explanation and a button to trigger the request.
            Snackbar.make(v, "permission_contacts_rationale",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(NavigationAcyivity.this, PERMISSIONS_CONTACT,
                                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                        }
                    })
                    .show();
        } else {
            // Contact permissions have not been granted yet. Request them directly.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        // END_INCLUDE(contacts_permission_request)
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (PermissionUtil.verifyPermissions(grantResults)) {
                searche();
            } else {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }
}