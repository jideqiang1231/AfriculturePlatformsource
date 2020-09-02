package com.bigdata.agricultureplatform.specialist.activity.zhongzi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.util.FileUtils;
import com.bigdata.agricultureplatform.specialist.util.RealPathFromUriUtils;
import com.bigdata.agricultureplatform.util.Constants;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SpecialistaddpushActivity extends AppCompatActivity implements View.OnClickListener, OnDateSetListener {
    @Bind(R.id.ib_specialist_addpushinfo_back)
    ImageButton ibSpecialistAddpushinfoBack;
    @Bind(R.id.tv_specialist_pushinfo_type)
    TextView tvSpecialistPushinfoType;
    @Bind(R.id.et_specialist_pushinfo_name)
    EditText etSpecialistPushinfoName;
    @Bind(R.id.et_specialist_pushinfo_introduce)
    EditText etSpecialistPushinfoIntroduce;
    @Bind(R.id.et_specialist_pushinfo_plantmethod)
    EditText etSpecialistPushinfoPlantmethod;
    @Bind(R.id.et_specialist_pushinfo_plantarea)
    EditText etSpecialistPushinfoPlantarea;
    @Bind(R.id.et_specialist_pushinfo_note)
    EditText etSpecialistPushinfoNote;
    @Bind(R.id.et_specialist_pushinfo_price)
    EditText etSpecialistPushinfoPrice;
    @Bind(R.id.et_specialist_pushinfo_manufacture)
    EditText etSpecialistPushinfoManufacture;
    @Bind(R.id.et_specialist_pushinfo_store)
    EditText etSpecialistPushinfoStore;
    @Bind(R.id.et_specialist_pushinfo_phone)
    EditText etSpecialistPushinfoPhone;
    @Bind(R.id.et_specialist_pushinfo_productdata)
    EditText etSpecialistPushinfoProductdata;
    @Bind(R.id.et_specialist_pushinfo_shelflife)
    EditText etSpecialistPushinfoShelflife;
    @Bind(R.id.et_specialist_pushinfo_plantnumber)
    EditText etSpecialistPushinfoPlantnumber;
    @Bind(R.id.iv_specialist_pushinfo_image)
    ImageView ivSpecialistPushinfoImage;
    //这个为了可视化文件名
    @Bind(R.id.ib_specialist_pushinfo_camera)
    ImageButton ibSpecialistPushinfoCamera;
    @Bind(R.id.ib_specialist_pushinfo_photo)
    ImageButton ibSpecialistPushinfoPhoto;
    @Bind(R.id.b_specialist_pushinfo_submitpush)
    Button bSpecialistPushinfoSubmitpush;
    @Bind(R.id.tv_specialist_pushinfo_imagename)
    TextView tvSpecialistPushinfoImagename;
    @Bind(R.id.tv_specialist_pushinfo_productdata)
    TextView tvSpecialistPushinfoProductdata;
    //点击选择日期

    private File outputImage;
    private String specialistType;
    String back;
    private Bitmap bitmap;
    private Bitmap headImage;
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int SELECT_PIC = 0;
    private Uri imageUri; //图片路径
    private String filename; //图片名称
    private CharSequence[] its = {"拍照", "从相册选择"};
    //返回值

    //提取本地值
    private String seedName;
    private String seedIntroduce;
    private String seedPlantarea;
    private String seedMethod;
    private String seedPrice;
    private String seedManufacturer;
    private String seedNote;
    private String seedStore;
    private String seedPhone;
    private String seedProductiondate;
    private String seedShelflife;
    private String seedPlantnumber;
    private String seedType;
    private String seedImage;
    private Integer specialistId;
    //日期时间的选择
    TimePickerDialog mDialogYearMonthDay1;
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistaddpush);
        ButterKnife.bind(this);
        //继承的appcompatactivity实现隐藏标题栏
        getSupportActionBar().hide();

        //时间选择may be no use
        //mDialogYearMonthDay1和mDialogYearMonthDay2分别是生产日期和保质期
        long tenYears = 10L * 365 * 1000 * 60 * 60 * 24L;
        mDialogYearMonthDay1 = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setCallBack(this)
                .build();
        /////////////////接收一下前边传来的参数，push的时候会用到////////////////////////////////////
        Intent intent = getIntent();
        specialistId = intent.getIntExtra("专家的id", 0);
        specialistType = intent.getStringExtra("专家的类型");
        Log.e("TAG", String.valueOf(specialistId));
        Log.e("TAG", specialistType);
        ///////先把能放的参数设置上（专家的id,type到时候直接okhttp发送）///////////
        tvSpecialistPushinfoType.setText(specialistType);

        ibSpecialistAddpushinfoBack.setOnClickListener(this);
        ibSpecialistPushinfoPhoto.setOnClickListener(this);
        ibSpecialistPushinfoCamera.setOnClickListener(this);
        bSpecialistPushinfoSubmitpush.setOnClickListener(this);
        //日期点击事件
        tvSpecialistPushinfoProductdata.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //返回按钮
        if (view == ibSpecialistAddpushinfoBack) {
            finish();

        } else if (view == tvSpecialistPushinfoProductdata) {
            mDialogYearMonthDay1.show(getSupportFragmentManager(), "year_month_day");
        } else if (view == ibSpecialistPushinfoCamera) {//点击相机功能调用图片
//            openCamera1();
            //图片名称 时间命名
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date(System.currentTimeMillis());  //System.currentTimeMillis()获取当前系统时间
            filename = format.format(date);

            //这里第一行代码用的是这个path//后期缓过来试试
            //File path=getExternalCacheDir();
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            outputImage = new File(path, filename + ".jpg");
            filename = filename + ".jpg";
            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //将File对象转换为Uri并启动照相程序

            if (Build.VERSION.SDK_INT >= 24) {
                try {
                    imageUri = FileUtils.getUriForFile(SpecialistaddpushActivity.this, outputImage);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } else {
                imageUri = Uri.fromFile(outputImage);
            }
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE"); //照相
            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
            startActivityForResult(intent, TAKE_PHOTO); //启动照相
            //  tv3.setText(filename);
//            ibImage.setImageBitmap(imageUri);
        } else if (view == ibSpecialistPushinfoPhoto) {//点击调用相册功能
//            openAlbum();
            //第一行代码中是这么写的先判断，
//            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//                ActivityCompat.requestPermissions(MainActivity.this,new String[]{
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                },1);
//            }else{
            //这个openAlbum方法在下边注释了
//                openAlbum();
//            }
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent, SELECT_PIC);
        } else if (view == bSpecialistPushinfoSubmitpush) { //点击提交功能插入图片
            if (ivSpecialistPushinfoImage.getDrawable() == null || TextUtils.isEmpty(filename) || !outputImage.exists() || outputImage.length() == 0) {
                Toast.makeText(SpecialistaddpushActivity.this, "内容不能为空偶", Toast.LENGTH_SHORT);
            } else {
                Map<String, String> params = null;
                //这里的ouloadimage是后端要get的表示，filename是咱这个名称，也要用到outputimage是这个image文件地址
                OkHttpUtils.post()//*************************由于此处是根据专家的类型起的后端文件名字，所以，涉及到专家注册的时候，类型不能错，包括多一个少一个空格。
                        .addFile("uploadimage", specialistType + "/" + filename, outputImage)//
                        .url(Constants.SEEDIMAGE)
                        .params(params)//
                        .build()//
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e(TAG, "种子图片插入失败==" + e.getMessage());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e(TAG, "种子图片插入成功==" + response);
                            }
                        });
                Toast.makeText(this, "图片插入完成", Toast.LENGTH_SHORT).show();
            }
//*****************************************************************************************************
//            //接着是另一个请求，请求插入种子信息//突然想起，专家那块的空格可以用trim去除
            seedType = tvSpecialistPushinfoType.getText().toString().trim();
            seedName = etSpecialistPushinfoName.getText().toString().trim();
            seedIntroduce = etSpecialistPushinfoIntroduce.getText().toString().trim();
            seedMethod = etSpecialistPushinfoPlantmethod.getText().toString().trim();
            seedPlantarea = etSpecialistPushinfoPlantarea.getText().toString().trim();
            seedNote = etSpecialistPushinfoNote.getText().toString().trim();
            seedPrice = etSpecialistPushinfoPrice.getText().toString().trim();
            seedManufacturer = etSpecialistPushinfoManufacture.getText().toString().trim();
            seedStore = etSpecialistPushinfoStore.getText().toString().trim();
            seedPhone = etSpecialistPushinfoPhone.getText().toString().trim();
            seedProductiondate = etSpecialistPushinfoProductdata.getText().toString().trim();
            seedShelflife = etSpecialistPushinfoShelflife.getText().toString().trim();
            seedPlantnumber = etSpecialistPushinfoPlantnumber.getText().toString().trim();

            seedImage = specialistType + "/" + filename;
            //specialistId;
            if (TextUtils.isEmpty(seedName)
                    || TextUtils.isEmpty(seedIntroduce)
                    || TextUtils.isEmpty(seedPlantarea)
                    || TextUtils.isEmpty(seedMethod)
                    || TextUtils.isEmpty(seedPrice)
                    || TextUtils.isEmpty(seedManufacturer)
                    || TextUtils.isEmpty(seedNote)
                    || TextUtils.isEmpty(seedStore)
                    || TextUtils.isEmpty(seedPhone)
                    || TextUtils.isEmpty(seedProductiondate)
                    || TextUtils.isEmpty(seedShelflife)
                    || TextUtils.isEmpty(seedPlantnumber)
                    || TextUtils.isEmpty(seedType)
                    || TextUtils.isEmpty(seedImage)
                    //新增逻辑
                    || ivSpecialistPushinfoImage.getDrawable() == null
                    || TextUtils.isEmpty(filename)
                    || !outputImage.exists() || outputImage.length() == 0) {
                Toast.makeText(this, "请检查哪里没有填写", Toast.LENGTH_LONG).show();
            } else {
                String url = Constants.SEEDINFO;
                OkHttpUtils
                        .post()
                        .url(url)
                        //先干掉
                        .addParams("specialistId", String.valueOf(specialistId))
                        .addParams("seedName", seedName)
                        .addParams("seedIntroduce", seedIntroduce)
                        .addParams("seedPlantarea", seedPlantarea)
                        .addParams("seedMethod", seedMethod)
                        .addParams("seedPrice", seedPrice)
                        .addParams("seedManufacturer", seedManufacturer)
                        .addParams("seedNote", seedNote)
                        .addParams("seedStore", seedStore)
                        .addParams("seedPhone", seedPhone)
                        .addParams("seedProductiondate", seedProductiondate)
                        .addParams("seedShelflife", seedShelflife)
                        .addParams("seedPlantnumber", seedPlantnumber)
                        .addParams("seedType", seedType)
                        .addParams("seedImage", seedImage)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                Log.e(TAG, "登录数据请求数据失败==" + e.getMessage());
                                Toast.makeText(SpecialistaddpushActivity.this, "完蛋了，崩了", Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                Log.e(TAG, "注册成功后的response==" + response);
                                Toast.makeText(SpecialistaddpushActivity.this, "成功发布种子的信息", Toast.LENGTH_LONG).show();

                            }
                        });


            }
        }
    }

    //这个是第一行代码中，注释
//    private void openAlbum() {
//        Intent	intent	=	new	Intent("android.intent.action.GET_CONTENT");
//        intent.setType("image/*");
//        startActivityForResult(intent,	CHOOSE_PHOTO);	//	打开相册
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String imagePath;
        switch (requestCode) {
            case SELECT_PIC://相册
                //这里也省略了不少
                String path = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
                outputImage = new File(path);
                filename = outputImage.getName();
                //    imageUri = Uri.fromFile(file);
                if (Build.VERSION.SDK_INT >= 24) {
                    try {
                        imageUri = FileUtils.getUriForFile(SpecialistaddpushActivity.this, outputImage);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                Intent intent1 = new Intent("com.android.camera.action.CROP");
                intent1.setDataAndType(imageUri, "image/*");
                intent1.putExtra("crop", "true");
                intent1.putExtra("aspectX", 1);
                intent1.putExtra("aspectY", 1);
                intent1.putExtra("outputX", 450);
                intent1.putExtra("outputY", 450);
                intent1.putExtra("return-data", false);
                intent1.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                intent1.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
                intent1.putExtra("noFaceDetection", true);
                startActivityForResult(intent1, CROP_PHOTO);
                break;
            case TAKE_PHOTO://相机
//                if(resultCode==RESULT_OK){
                try {
                    //第一行代码中加入了
                    //Bitmap bitmap=BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//然后在这里显示
                    //他这里做了裁剪
                    Intent intent = new Intent("com.android.camera.action.CROP"); //剪裁
                    intent.setDataAndType(imageUri, "image/*");
                    intent.putExtra("scale", true);
                    //设置宽高比例
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                    //设置裁剪图片宽高
                    intent.putExtra("outputX", 450);
                    intent.putExtra("outputY", 450);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                    Toast.makeText(SpecialistaddpushActivity.this, "剪裁图片", Toast.LENGTH_SHORT).show();
                    //广播刷新相册
                    Intent intentBc = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intentBc.setData(imageUri);
                    this.sendBroadcast(intentBc);
                    startActivityForResult(intent, CROP_PHOTO); //设置裁剪参数显示图片至ImageView
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                }
                break;
            //一下代码是裁剪
            case CROP_PHOTO:
                try {
                    //图片解析成Bitmap对象
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    //    headImage = ImageDeal.toRoundBitmap(bitmap);
                    headImage = bitmap;
                    //这里不能用system.out，记住
//                    System.out.println("图片大小为" + headImage.getByteCount() / 1024 +
//                            "KB宽度为" + headImage.getHeight() + "高度为：" + headImage.getWidth());
//                    System.out.println(imageUri);
//                    System.out.println(filename);
                    Log.e(TAG, String.valueOf(headImage.getByteCount() / 1024));
                    Log.e(TAG, String.valueOf(imageUri));
                    Log.e(TAG, filename);

                    // updatePicture();   //上传头像
                    //    touxiang.setImageBitmap(headImage); //将剪裁后照片显示出来\
                    //并且将这个图片通过ImageDeal中的toroundBitmap方法弄成原型
                    ivSpecialistPushinfoImage.setImageBitmap(bitmap);
                    tvSpecialistPushinfoImagename.setText(filename);
                    //将剪裁后照片显示出来

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }
//日期这块必须执行的方法，不然没法显示text
    @Override
    public void onDateSet(TimePickerDialog timePickerDialog, long millseconds) {
        String text = getDateToString(millseconds);
        etSpecialistPushinfoProductdata.setText(text);
    }

    public String getDateToString(long millseconds) {
        Date date = new Date(millseconds);
        return sf.format(date);
    }
}