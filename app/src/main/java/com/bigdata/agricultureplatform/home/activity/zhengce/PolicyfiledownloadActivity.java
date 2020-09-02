package com.bigdata.agricultureplatform.home.activity.zhengce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.util.Constants;
import com.bigdata.agricultureplatform.util.OpenFileTipDialog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class PolicyfiledownloadActivity extends AppCompatActivity {
    @Bind(R.id.imageView)
    ImageView imageView;
    @Bind(R.id.ll_finish_to_open)
    LinearLayout llFinishToOpen;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.progresss_text)
    TextView progresssText;
    @Bind(R.id.frameLayout_downloading)
    FrameLayout frameLayoutDownloading;
    @Bind(R.id.open_in_anotherapp)
    Button openInAnotherapp;
    @Bind(R.id.frameLayout_downloaded)
    FrameLayout frameLayoutDownloaded;
    @Bind(R.id.policyfile_name)
    TextView policyfileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policyfiledownload);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String policyfile_name = intent.getStringExtra("文件位置/名称");
        policyfileName.setText(policyfile_name);
        downloadFile(policyfile_name);
    }

    private void downloadFile(String policyfile_name) {
        /**
         * 下载附件
         */
        OkHttpUtils.get()
                .url(Constants.ZHENGCEWENJIANXIAZAI_URL)
                .build()
                .execute(new FileCallBack(Environment.getExternalStorageDirectory().getAbsolutePath(),policyfile_name) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("info: ", "onError :" + e.getMessage());

                        Toast.makeText(PolicyfiledownloadActivity.this, e.getMessage() , Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void inProgress(float progress, long total, int id) {
                        super.inProgress(progress, total, id);
                        Log.e("info: ","inProgress"+(int)(-progress/(float) total)/100);
                        // Toast.makeText(PolicyfiledownloadActivity.this, "inProgress"+(int)(progress/total/100), Toast.LENGTH_SHORT).show();
                        //此处,文件较小，以Toast反应慢，早已下载完。
                        int downloadprogress =  ((int)(progress/total/100));
                        progressBar.setProgress(downloadprogress);
                        progresssText.setText(downloadprogress + "%");

                    }

                    @Override
                    public void onResponse(File downloadFile, int id) {
                        frameLayoutDownloaded.setVisibility(View.VISIBLE);
                        frameLayoutDownloading.setVisibility(View.INVISIBLE);
                        OpenFileTipDialog.openFiles(downloadFile.getAbsolutePath(),PolicyfiledownloadActivity.this);
                        Log.e("info: ", "onResponse :" + downloadFile.getAbsolutePath());
                        Toast.makeText(PolicyfiledownloadActivity.this,"onResponse :" + downloadFile.getAbsolutePath() , Toast.LENGTH_LONG).show();
                    }

                });
    }
}
