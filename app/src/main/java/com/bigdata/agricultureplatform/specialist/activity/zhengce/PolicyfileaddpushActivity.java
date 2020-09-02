package com.bigdata.agricultureplatform.specialist.activity.zhengce;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.util.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class PolicyfileaddpushActivity extends Activity implements View.OnClickListener {

    @Bind(R.id.ib_policyfile_addpush_back)
    ImageButton ibPolicyfileAddpushBack;
    @Bind(R.id.et_policyfile_name)
    EditText etPolicyfileName;
    @Bind(R.id.b_submit_policyfile)
    Button bSubmitPolicyfile;
    @Bind(R.id.choose_policy_file)
    TextView choosePolicyFile;
    @Bind(R.id.et_policyfile_location)
    EditText etPolicyfileLocation;
    private String path;
    private String policyfilepath;
    private String policyfilename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policyfileaddpush);
        ButterKnife.bind(this);
        ibPolicyfileAddpushBack.setOnClickListener(this);
        bSubmitPolicyfile.setOnClickListener(this);
        choosePolicyFile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == ibPolicyfileAddpushBack) {
            finish();
        } else if (view == choosePolicyFile) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");//无类型限制
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, 1);
        } else if (view == bSubmitPolicyfile) {
            policyfilepath =etPolicyfileLocation.getText().toString();
            policyfilename =etPolicyfileName.getText().toString();
            first_uploadinfo(policyfilename);
            second_uploadfile(policyfilepath,policyfilename);
        }
    }

    private void second_uploadfile(String policyfilepath, String policyfilename) {
        File file = new File(policyfilepath);
        if (!file.exists()) {
            Toast.makeText(PolicyfileaddpushActivity.this, "文件不存在", Toast.LENGTH_SHORT).show();
            return;
        }
        //   FileInputStream fis = new FileInputStream(f);
        Map<String, String> params = null;
        Map<String, String> contenttype =new HashMap<>();
        contenttype.put("Content-Type","multipart/form-data");
        //此处必须要请求头，Content-type，
        OkHttpUtils.post()
                .headers(contenttype)
                .addFile("uploadPolicyfile", policyfilename, file)//
                .url(Constants.ZHENGCEWENJIANSHANGCHUAN_URL)
                .params(params)//
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "文件上传失败==" + e.getMessage());
                        Toast.makeText(PolicyfileaddpushActivity.this, "完犊子", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "文件上传成功==" + response);
                        Toast.makeText(PolicyfileaddpushActivity.this, "文件上传成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void first_uploadinfo(String policyfilename) {
        String url = Constants.ZHENGCEWENJIANINFOADD_URL;
        OkHttpUtils
                .post()
                .url(url)
                //这里的policyfilename就是其路径中存的路径，
                //后端D://policyfile/****/+policyfilename即为路径
                .addParams("policyfileLocation", policyfilename)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e(TAG, "政策文件的基本信息插入失败" + e.getMessage());
                        Toast.makeText(PolicyfileaddpushActivity.this, "完蛋了，崩了", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e(TAG, "政策文件的额基本信息插入成功==" + response);
                        Toast.makeText(PolicyfileaddpushActivity.this, "成功发布种子的信息", Toast.LENGTH_LONG).show();

                    }
                });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == Activity.RESULT_OK && requestCode == 1001) {//判断是否选择和Code判断
//            Uri uri = data.getData();//拿到路径
//            etPath.setText(uri.getPath());
//            File file = new File(uri.getPath());
//            file.getAbsolutePath();
//            etName.setText(file.getName());
        if (resultCode == Activity.RESULT_OK && requestCode == 1) {
            Uri uri = data.getData();

            if ("file".equalsIgnoreCase(uri.getScheme())) {//使用第三方应用打开
                path = uri.getPath();

                etPolicyfileLocation.setText(path);
                Toast.makeText(this, path + "11111", Toast.LENGTH_SHORT).show();
                File file = new File(path);
                etPolicyfileName.setText(file.getName());
                return;
            }
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                path = getPath(this, uri);
                etPolicyfileLocation.setText(path);
                File file = new File(path);
                etPolicyfileName.setText(file.getName());
                Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
            } else {//4.4以下下系统调用方法
                path = getRealPathFromURI(uri);
                etPolicyfileLocation.setText(path);
                File file = new File(path);
                etPolicyfileName.setText(file.getName());
                Toast.makeText(PolicyfileaddpushActivity.this, path + "222222", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private String getRealPathFromURI(Uri uri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        if(null!=cursor&&cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
            cursor.close();
        }
        return res;
    }

    @SuppressLint("NewApi")

    private String getPath(PolicyfileaddpushActivity context, Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());

    }

    private boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private String getDataColumn(PolicyfileaddpushActivity context, Uri contentUri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(contentUri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
    private boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

}
