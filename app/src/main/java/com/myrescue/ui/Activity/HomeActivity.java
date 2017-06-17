package com.myrescue.ui.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.myrescue.R;




/**
 * Created by Men on 2017/4/22.
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener{


    private ImageButton loC;
    private ImageButton mN;
    private ImageButton aCc;
    private LinearLayout heLp;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        loC = (ImageButton) findViewById(R.id.loc);
        mN = (ImageButton) findViewById(R.id.mn);
        aCc = (ImageButton) findViewById(R.id.acc);
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loc:
                startActivity(new Intent(this,LocationActivity.class));
                break;
            case R.id.mn:
                startActivity(new Intent(this,orderActivity.class));
                break;
            case R.id.help:
                Toast.makeText(this,"暂时未开放",Toast.LENGTH_LONG).show();
                break;
            case R.id.acc:
                Toast.makeText(this,"图没定下来，敬请期待谢谢",Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
