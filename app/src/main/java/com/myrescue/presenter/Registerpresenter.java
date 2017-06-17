package com.myrescue.presenter;

import android.util.Log;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.myrescue.model.Bean.LoginInfo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Men on 2017/4/25.
 */

public class Registerpresenter extends BasePresenter {

    @Override
    protected void parseJson(String json) {
        Log.i("",json);
        Gson gson = new Gson();

        LoginInfo homeInfo = gson.fromJson(json, LoginInfo.class);
        int invalidPosition = AdapterView.INVALID_POSITION;

//        adapter.setData(homeInfo);


    }

    @Override
    protected void showErrorMessage(String message) {

    }
    public void getHomeData(String lat,String lng){
        //调用responseInfoApi中的getHomeInfo方法
        int a = new Integer(0);
        Call<LoginInfo> logging = responseInfoApi.getlogging(1,"12");
        logging.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                Log.d("sjlad","12346523");
            }

            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                Log.i("-----------------zai","beng------------------");
            }
        });

    }

}
