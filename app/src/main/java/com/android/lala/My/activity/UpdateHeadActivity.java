package com.android.lala.My.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.android.lala.R;
import com.android.lala.base.BaseActivity;
import com.android.lala.utils.PreferenceManager;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Administrator on 2016/9/14.
 */
public class UpdateHeadActivity extends BaseActivity implements View.OnClickListener{
    private ImageView headimage;
    private Button byCamera,byGallery;
    private String headUrl;
    private Bitmap headbitmap;
    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_updatehead);
        byCamera=findView(R.id.getheadbycamera);
        byGallery=findView(R.id.getheadbygallery);
        headimage=findView(R.id.updatehead);
        Picasso.with(this).load(headUrl).into(headimage);
        setTitle("修改头像");
    }

    @Override
    protected void initData() {
        PreferenceManager.getInstance(this).getString("photo","");
    }

    @Override
    protected void initListener() {
        byGallery.setOnClickListener(this);
        byCamera.setOnClickListener(this);
    }

    @Override
    protected boolean isShowToolBar() {
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getheadbycamera:
                Intent camere =new Intent(Intent.ACTION_PICK,null);
                camere.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(camere,101);
                break;

            case R.id.getheadbygallery:
                Intent gallery=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                gallery.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                        Environment.getExternalStorageDirectory(),   "HeadIMG.jpg")));
                startActivityForResult(gallery,102);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 101:
                if (resultCode==RESULT_OK){
                    cropPhoto(data.getData());//裁剪图片
                }
                break;

            case 102:
                if (resultCode==RESULT_OK){
                File temp = new File(Environment.getExternalStorageDirectory()
                        + "/HeadIMG.jpg");
                cropPhoto(Uri.fromFile(temp));//裁剪图片
                }
                break;
            case 103:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    headbitmap = extras.getParcelable("data");
                    if(headimage!=null){
                        /**
                         * 上传服务器代码
                         */
                        // setPicToView(head);//保存在SD卡中
//                        saveBitmap(headbitmap);
//                        iv_head.setImageBitmap(headbitmap);//用ImageView显示出来
                    }
                }
                break;

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 103);
    }
}
