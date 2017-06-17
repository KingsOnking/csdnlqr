package com.myrescue.ui.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.myrescue.R;
import com.myrescue.utils.CircleImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Men on 2017/4/24.
 */

public class AuditAcitivity extends BaseActivity implements View.OnClickListener{
    Bitmap photo;
    Button commitButton;
    protected CircleImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aptitude);
        ImageButton iB1 = (ImageButton) findViewById(R.id.add_certification);
        ImageButton iB2 = (ImageButton) findViewById(R.id.add_certification0);
        ImageButton viewById = (ImageButton) findViewById(R.id.add_certification1);
        ImageButton viewById1 = (ImageButton) findViewById(R.id.add_certification2);
        ImageButton viewById2 = (ImageButton) findViewById(R.id.fanhui);
        Button commitButton = (Button) findViewById(R.id.commit_button);
        File appDir = new File(Environment.getExternalStorageDirectory(), "sos");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            //            点击返回上一面页
            case R.id.fanhui:
                Intent intent = new Intent(getApplication(), RegisterActivity.class);
                startActivity(intent);
                break;
            //            点击替换图片
            case R.id.add_certification0:
                Intent openAlbumIntent = new Intent(Intent.ACTION_GET_CONTENT);
                openAlbumIntent.setType("image/*");
                startActivityForResult(openAlbumIntent, 11);
                break;
            //            点击替换图片
            //                Intent openAlbumIntent1 = new Intent(Intent.ACTION_GET_CONTENT);
//                openAlbumIntent1.addCategory(Intent.CATEGORY_OPENABLE);
//                startActivityForResult(openAlbumIntent1, 12);
            case R.id.add_certification:
                Intent openAlbumIntent1 = new Intent(Intent.ACTION_GET_CONTENT);
                openAlbumIntent1.setType("image/*");
                startActivityForResult(openAlbumIntent1, 11);
                break;
            //            点击替换图片
            case R.id.add_certification1:
                Intent openAlbumIntent2 = new Intent(Intent.ACTION_GET_CONTENT);
                openAlbumIntent2.setType("image/*");
                startActivityForResult(openAlbumIntent2, 11);
                break;
            //            点击替换图片
            case R.id.add_certification2:
                Intent openAlbumIntent3 = new Intent(Intent.ACTION_GET_CONTENT);
                openAlbumIntent3.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(openAlbumIntent3, 11);
                photo(view);
                break;
            //            点击提交申请
            case R.id.commit_button:
                Intent intent1 = new Intent(this, HomeActivity.class);
                startActivity(intent1);
                break;
        }
    }

    private void photo(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 80);
        intent.putExtra("outputY", 80);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 11);
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode != RESULT_OK)
            return;
        switch (requestCode) {
            case 11:
                Uri uri = data.getData();
                try {
                    if (photo != null)
                        photo.recycle();
                    photo = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    image.setImageBitmap(photo);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
}