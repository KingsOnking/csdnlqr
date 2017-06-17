package com.myrescue.ui.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.myrescue.R;
import com.myrescue.utils.IdcardUtils;
import com.myrescue.utils.RegExpUtility;
import com.myrescue.utils.SMSUtil;

import java.text.ParseException;

import cn.smssdk.EventHandler;
import cn.smssdk.OnSendMessageHandler;
import cn.smssdk.SMSSDK;


/**
 * Created by Men on 2017/4/22.
 */

public class RegisterActivity extends Activity implements View.OnClickListener{
    private static final int GET_CODE_SUCCES = 100;//获取验证码成功
    private static final int GET_CODE_FAIL = 101;//获取验证码失败
    private static final int KEEP_TIME_MINS = 102;//保持时间递减的状态码
    private static final int RESET_TIME = 103;//重置时间为60秒
    private static final int SUBMIT_CODE_SUCCES = 104;//校验验证码成功
    private static final int SUBMIT_CODE_FAIL = 105;//校验验证码失败
    private int time = 60;
    protected IdcardUtils id = new IdcardUtils();;
    ImageButton registerbacktrack;
    LinearLayout one;
    TextView homeTvAddress;
    EditText phone;
    EditText etPassword;
    EditText code;
    EditText etPasswordSecond;
    EditText etName;
    EditText etID;
    Button buttonZhuce;
    LinearLayout activityRegister;

    TextView gain_code;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GET_CODE_SUCCES:
                    Toast.makeText(RegisterActivity.this, "获取验证码成功", Toast.LENGTH_SHORT).show();
                    break;
                case GET_CODE_FAIL:
                    Toast.makeText(RegisterActivity.this, "获取验证码失败", Toast.LENGTH_SHORT).show();
                    break;
                case SUBMIT_CODE_SUCCES:
                    Toast.makeText(RegisterActivity.this, "校验验证码成功", Toast.LENGTH_SHORT).show();
                    //必须获取校验成功,才可以继续下一个发送请求做登录过程
                    break;
                case SUBMIT_CODE_FAIL:
                    Toast.makeText(RegisterActivity.this, "校验验证码失败", Toast.LENGTH_SHORT).show();
                    break;
                case KEEP_TIME_MINS:
                    code.setText("稍后再发(" + (time--) + ")");
                    break;
                case RESET_TIME:
                    code.setText("重新发送");
                    time = 60;
                    break;
            }
        }
    };
    private EditText text;
    private Button bt_zhuce;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        text = (EditText) findViewById(R.id.et_ID);
        bt_zhuce = (Button) findViewById(R.id.button_zhuce);
        String mText = text.toString();
        try {
                id.IDCardValidate(mText);
                    id.isDataFormat(mText);
        } catch (ParseException e) {
            e.printStackTrace();

        }


    }
        public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gain_code:
                checkLogin();
                break;
            case R.id.button_zhuce:
                login();
                startActivity( new Intent(this, AuditAcitivity.class));
                break;
        }
    }


    private void checkLogin() {
        //电话


        if(SMSUtil.isMobileNO(phone.toString())){
            //手机号码和验证码,放再sharesdk平台校验过程
//           需要添加share SDk的Key和v
//SMSSDK.submitVerificationCode("86",phone,code);
        }
    }
    private void login() {
                    //电话
                    String p = phone.getText().toString().trim();
                     //验证码
                    String c = code.getText().toString().trim();
                    //密码
                    String psd = etPassword.getText().toString().trim();
                    //再次输入密码
                    String pdsd =  etPasswordSecond.getText().toString().trim();
                    String name = etName.getText().toString().trim();
                    String userid = etID.getText().toString().trim();
        if(!RegExpUtility.isMobileNO(p) && TextUtils.isEmpty(psd) && TextUtils.isEmpty(c) && TextUtils.isEmpty(pdsd)
                && TextUtils.isEmpty(name)&& TextUtils.isEmpty(userid)
                            ){
            if(!psd.equals(pdsd)){
                Toast.makeText(this,"密码不匹配",Toast.LENGTH_LONG).show();
            }
//                        LoginPresenter loginPresenter = new LoginPresenter(this);
////   此处为空拿到数据更改
//                        loginPresenter.getLoginData(null,psd,null,2);
                    }else {
            Toast.makeText(this,"请您填写完整",Toast.LENGTH_LONG).show();
        }
                }

                //    EVENT_SUBMIT_VERIFICATION_CODE
                 EventHandler eventHandler = new EventHandler(){
                    @Override
                    public void afterEvent(int event, int result, Object o) {
                        //此方法是运行在子线程中的,所以不可以
                        if (result == SMSSDK.RESULT_COMPLETE){
                            //成功
                            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                                //下发验证码短信成功后,才可以做验证码短信+手机号码校验过程
                                handler.sendEmptyMessage(GET_CODE_SUCCES);
                            }
                            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                                //校验验证码成功
                                handler.sendEmptyMessage(SUBMIT_CODE_SUCCES);
                            }
                        }else{
                            //失败
                            if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
                                //下发验证码短信成功后,才可以做验证码短信+手机号码校验过程
                                handler.sendEmptyMessage(GET_CODE_FAIL);
                            }
                            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE){
                                //校验验证码失败
                                handler.sendEmptyMessage(SUBMIT_CODE_FAIL);
                            }
                        }
                        //做某一个事件结果的监听
                        super.afterEvent(event, result,o);
                    }
                };
    private void sendCode() {
        String phone1 = phone.getText().toString().trim();
                    if(SMSUtil.isMobileNO(phone1)){
                        //下发验证码短信(发送成功,失败 EventHandler --->afterEvent())
                        SMSSDK.getVerificationCode("86",phone1, new OnSendMessageHandler() {
                            @Override
                            public boolean onSendMessage(String country, String phone) {
                                return false;
                            }
                        });
                        //子线程进行倒计时
                        new Thread(){
                            @Override
                            public void run() {
                                //如果time的值大于0,则说明还有计数的时间
                                while(time>0){
                                    try {
                                        Thread.sleep(999);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    //发送一条消息,用于减少time的时间
                                    handler.sendEmptyMessage(KEEP_TIME_MINS);
                                }
                                //重新下发验证码短信
                                handler.sendEmptyMessage(RESET_TIME);

                            }
                        }.start();
                    }
                }
            }


