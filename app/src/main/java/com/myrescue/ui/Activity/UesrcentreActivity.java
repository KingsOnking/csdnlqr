package com.myrescue.ui.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myrescue.R;
import com.myrescue.utils.CircleImageView;

public class UesrcentreActivity extends Activity {
    ImageButton registerbacktrack;
    TextView homeTvAddress;
    CircleImageView uesrHeadportrait;
    TextView uesrName;
    TextView uesrName1;
    ImageButton uesrName2;
    RelativeLayout userChange;
    TextView uesrWeixin;
    TextView uesrPhone;
    TextView uesrEmail;
    TextView uesrEarnings;
    ImageButton uesrEarnings1;
    TextView uesrTocose;
    TextView changeEMail;
    RelativeLayout weixinButtm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acyivity_personal);

        init();
    }

    private void init() {
        changeEMail.setText("123");
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.registerbacktrack:
                break;
            case R.id.home_tv_address:
                break;
            case R.id.uesr_headportrait:
                break;
            case R.id.uesr_name:
                break;
            case R.id.uesr_name1:
                break;
            case R.id.uesr_name2:
                break;
//            修改姓名
            case R.id.user_Change:
                break;
//            修改微信号
            case R.id.weixin_buttm:

                break;
//            修改手机号
            case R.id.uesr_phone:
                break;
//            修改Email
            case R.id.uesr_email:
                break;
//            收益查询 跳页面
            case R.id.uesr_earnings:
                break;
//            改密码
            case R.id.uesr_tocose:
                break;
        }
    }

}
