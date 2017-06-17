package com.myrescue.presenter;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.myrescue.model.Bean.LoginInfo;
import com.myrescue.ui.Activity.LoginActivity;

import java.sql.Savepoint;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created
 */

public class LoginPresenter extends BasePresenter {
    private LoginActivity activity;
    private Savepoint savepoint;

    public LoginPresenter(LoginActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void parseJson(String json) {
        Log.i("", json);
        Gson gson = new Gson();
        LoginInfo loginInfo = gson.fromJson(json, LoginInfo.class);
        activity.init(loginInfo);
    }
    @Override
    protected void showErrorMessage(String message) {

    }

    public void getLoginData(int account, String password) {
        final Call<LoginInfo> logging = responseInfoApi.getlogging(account, password);
//        logging.enqueue(new CallBackAdapter());
        logging.enqueue(new Callback<LoginInfo>() {
            @Override
            public void onResponse(Call<LoginInfo> call, Response<LoginInfo> response) {
                LoginInfo body = response.body();
                if(body.equals("2000")){

                }
            }

            @Override
            public void onFailure(Call<LoginInfo> call, Throwable t) {
                String message = t.getMessage();
                Toast.makeText(activity,message,Toast.LENGTH_LONG).show();
            }
        });

    }
}