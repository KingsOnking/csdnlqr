package com.myrescue.ui.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.myrescue.R;
import com.myrescue.model.Bean.LoginInfo;
import com.myrescue.presenter.LoginPresenter;




public class LoginActivity extends Activity implements View.OnClickListener {

    private LoginPresenter loginPresenter;
    private Button debark;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mian);
        debark = (Button) findViewById(R.id.debark);
        loginPresenter = new LoginPresenter(this);
    }

    public void init(LoginInfo k) {

    }

    public void showData(LoginInfo activeInfo){
        Toast.makeText(this,activeInfo.getAccount(),Toast.LENGTH_LONG).show();
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.debark:
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.forget_password:
                Toast.makeText(this,"跳转页面",Toast.LENGTH_LONG).show();
                break;
            case R.id.register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;
        }
    }
}
