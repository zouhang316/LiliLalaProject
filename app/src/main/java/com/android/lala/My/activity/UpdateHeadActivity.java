package com.android.lala.my.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.android.lala.LaLaAppaction;
import com.android.lala.R;
import com.android.lala.api.ApiContacts;
import com.android.lala.base.BaseActivity;
import com.android.lala.base.commbuinese.CommDataDaoImpl;
import com.android.lala.http.constant.HttpUrls;
import com.android.lala.http.listener.HttpListener;
import com.android.lala.utils.LalaLog;
import com.android.lala.utils.PreferenceManager;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/14.
 */
public class UpdateHeadActivity extends BaseActivity implements View.OnClickListener{
    private HttpListener<String> httpListener;
    private ImageView headimage;
    private Button byCamera,byGallery;
    private TextView sava;
    private String headUrl;
    private String userid;
    private String Mypath;
    private Bitmap headbitmap;
    private ImageView back;
    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_updatehead);
        byCamera=findView(R.id.getheadbycamera);
        byGallery=findView(R.id.getheadbygallery);
        headimage=findView(R.id.updatehead);
        back=findView(R.id.back);
        sava=findView(R.id.save);
        Picasso.with(this).load(headUrl).into(headimage);
        setTitle("修改头像");
    }

    @Override
    protected void initData() {
        commDataDao=new CommDataDaoImpl(this,false,"");
        httpListener=new HttpListener<String>() {
            @Override
            public void onSuccess(int what, String response) {
                LalaLog.i("result","success");
            }

            @Override
            public void onFail(String errMsg) {
                LalaLog.i("result",errMsg);
            }
        };
        PreferenceManager preferenceManager=PreferenceManager.getInstance(this);
        userid=preferenceManager.getString("id","");
        headUrl=preferenceManager.getString("photo","");
    }

    @Override
    protected void initListener() {
        byGallery.setOnClickListener(this);
        byCamera.setOnClickListener(this);
        sava.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    protected boolean isShowToolBar() {
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.getheadbycamera:
                Intent gallery=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                gallery.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
                        Environment.getExternalStorageDirectory(),   "HeadIMG.jpg")));
                startActivityForResult(gallery,101);
                break;

            case R.id.getheadbygallery:
                Intent camere =new Intent(Intent.ACTION_PICK,null);
                camere.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(camere,102);
                break;

            case R.id.save:
                saveBitmap(headbitmap);
                OOSuploaddata(Mypath+"/head.jpg");
                break;
            case R.id.back:
                finish();
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 101:
                if (resultCode==RESULT_OK){
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/HeadIMG.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }
                break;
            case 102:
            if (resultCode==RESULT_OK){
                    cropPhoto(data.getData());//裁剪图片
                }
            break;
            case 103:
                if (data != null) {
                    sava.setVisibility(View.VISIBLE);
                    Bundle extras = data.getExtras();
                    headbitmap = extras.getParcelable("data");
                    if(headimage!=null){
                        /**
                         * 上传服务器代码
                         */
//                        saveBitmap(headbitmap);
                        headimage.setImageBitmap(headbitmap);//用ImageView显示出来
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
    public void saveBitmap(Bitmap bm) {
        Mypath=Environment.getExternalStorageDirectory().toString()+"/mayixinkong";
        File file=new File(Mypath);
        if (!file.exists()){
            file.mkdirs();
        }
        //Log.e(TAG, "保存图片");
        File f = new File(Mypath, "head.jpg");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bm.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
            // Log.i(TAG, "已经保存");
            // Toast.makeText(getApplicationContext(), "已经保存", 0).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void OOSuploaddata(String uploadFilePath){

        PutObjectRequest put = new PutObjectRequest(LaLaAppaction.OSS_BUCKET, "image/albums/"+getImageObjectKey(userid), uploadFilePath);

        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
            }
        });

        LaLaAppaction.oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(final PutObjectRequest request, PutObjectResult result) {
                LalaLog.i("PutObject", "UploadSuccess");
                LalaLog.i("ETag", result.getETag());
                LalaLog.i("RequestId", result.getRequestId());
                LalaLog.i("url",request.getObjectKey());
                //将上传成功的图片地址传给自己的服务器后台，比如修改用户数据库中，用户头像的url。
                //修改后台url成功后，再利用glide 下载最新的照片，修改本地头像图片。
                //request.getObjectKey() 是图片地址，但是不包含，OSS 图片域名
                String headurl=HttpUrls.OOSURL+request.getObjectKey();
                PreferenceManager.getInstance(getApplicationContext()).putString("photo",headurl);
                postUrl(headurl);
            }

            private void postUrl(final String headurl) {
                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
                StringRequest stringRequest=new StringRequest(Request.Method.POST,ApiContacts.UPLOADFILE , new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LalaLog.i("result","success");
                        finish();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        LalaLog.i("result","success");
                    }
                }){@Override
                protected Map<String, String> getParams()
                        throws AuthFailureError {
                    HashMap<String,String> paramers=new HashMap<>();
                    paramers.put("url", headurl);
                    paramers.put("userId",userid);
                    return paramers;
                }};
                queue.add(stringRequest);
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }

            }
        });
    }
    //通过UserCode 加上日期组装 OSS路径
    private String getImageObjectKey (String strUserCode){

        Date date = new Date();
        return new SimpleDateFormat("yyyy/M/d").format(date)+"/"+strUserCode+new SimpleDateFormat("yyyyMMddssSSS").format(date)+".jpg";

    }
}
