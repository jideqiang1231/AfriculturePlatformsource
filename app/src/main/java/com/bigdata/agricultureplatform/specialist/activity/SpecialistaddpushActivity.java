package com.bigdata.agricultureplatform.specialist.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigdata.agricultureplatform.R;
import com.bigdata.agricultureplatform.specialist.util.FileUtils;
import com.bigdata.agricultureplatform.specialist.util.ImageDeal;
import com.bigdata.agricultureplatform.specialist.util.RealPathFromUriUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class SpecialistaddpushActivity extends Activity implements View.OnClickListener {
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
    @Bind(R.id.et_specialist_pushinfo_image)
    EditText etSpecialistPushinfoImage;
    @Bind(R.id.ib_specialist_pushinfo_camera)
    ImageButton ibSpecialistPushinfoCamera;
    @Bind(R.id.ib_specialist_pushinfo_photo)
    ImageButton ibSpecialistPushinfoPhoto;
    @Bind(R.id.b_specialist_pushinfo_submitpush)
    Button bSpecialistPushinfoSubmitpush;
    //    //相册100，相机101
//    private static final int REQUEST_CODE_ALBUM = 100;
//    private static final int REQUEST_CODE_CAMERA = 101;

    private Integer specialistId;
    private String specialistType;
    private Bitmap headImage = null;
    public static final int TAKE_PHOTO = 1;
    public static final int CROP_PHOTO = 2;
    public static final int SELECT_PIC = 0;
    private Uri imageUri; //图片路径
    private String filename; //图片名称
    private CharSequence[] its = {"拍照", "从相册选择"};
    //返回值
    String back;
    //提取本地值
    private String nickname;
    private String name;
    private String image;
    private String address;
    private float Balance;
    private String Tel;
    private int Vip;
    private int sex;
    private int Uid;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specialistaddpush);
        ButterKnife.bind(this);
        /////////////////接收一下前边传来的参数，push的时候会用到////////////////////////////////////
        Intent intent = getIntent();
        specialistId = intent.getIntExtra("专家的id", 0);
        specialistType = intent.getStringExtra("专家的类型");
        Log.e("TAG", String.valueOf(specialistId));
        Log.e("TAG", specialistType);
        //////////先把能放的参数设置上（专家的id,type到时候直接okhttp发送）///////////////
        tvSpecialistPushinfoType.setText(specialistType);
        ////////////////////////////////////////////////////////////////
        ibSpecialistAddpushinfoBack.setOnClickListener(this);
        ibSpecialistPushinfoPhoto.setOnClickListener(this);
        ibSpecialistPushinfoCamera.setOnClickListener(this);
        bSpecialistPushinfoSubmitpush.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //返回按钮
        if (view == ibSpecialistAddpushinfoBack) {
            finish();

        } else if (view == ibSpecialistPushinfoCamera) {//点击相机功能调用图片
//            openCamera1();
            //图片名称 时间命名
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date(System.currentTimeMillis());  //System.currentTimeMillis()获取当前系统时间
            filename = format.format(date);

            //这里第一行代码用的是这个path//后期缓过来试试
            //File path=getExternalCacheDir();
            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
            File outputImage = new File(path, filename + ".jpg");
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
            //    imageUri = Uri.fromFile(outputImage);
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

        }
    }

    //这个是第一行代码中德，注释了
//    private void openAlbum() {
//        Intent	intent	=	new	Intent("android.intent.action.GET_CONTENT");
//        intent.setType("image/*");
//        startActivityForResult(intent,	CHOOSE_PHOTO);	//	打开相册
//    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        String imagePath;
        switch (requestCode) {
            case SELECT_PIC://相册
                //这里也省略了不少
                String path = RealPathFromUriUtils.getRealPathFromUri(this, data.getData());
                File file = new File(path);
                filename = file.getName();
                //    imageUri = Uri.fromFile(file);
                if (Build.VERSION.SDK_INT >= 24) {
                    try {
                        imageUri = FileUtils.getUriForFile(SpecialistaddpushActivity.this, file);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }

                } else {
                    imageUri = Uri.fromFile(file);
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
                    Log.e(TAG, String.valueOf(headImage.getByteCount()/1024));
                    Log.e(TAG, String.valueOf(imageUri));
                    Log.e(TAG, filename);




                    // updatePicture();   //上传头像
                    //    touxiang.setImageBitmap(headImage); //将剪裁后照片显示出来\
                    //并且将这个图片通过ImageDeal中的toroundBitmap方法弄成原型
                    ivSpecialistPushinfoImage.setImageBitmap(bitmap); //将剪裁后照片显示出来
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }

    }


}

//            public static void upLoadToServer(final Context context, HashMap<String,String >params,final String url, String filePath, final ArrayList<String> list,final HttpCallBackListener listener) {
//                if (NetUtils.isConnected(context)) {
//                    Map<String, String> headers = new HashMap<>();
//                    headers.put("Content-Disposition", "form-data;filename=enctype");
//                    File file = new File(filePath);
//                    if (!file.exists()) {
//                        MyToast.showMessage("文件不存在，请修改文件路径");
//                        return;
//                    }
//                    String filename = file.getName();
//                    OkHttpUtils.post().params(params)
//                            .headers(headers)
//                            .addFile("mFile", filename, file)
//                            .build()
//                            .execute(new StringCallback() {
//                                @Override
//                                public void onError(Call call, Exception e, int id) {
//
//                                }
//
//                                @Override
//                                public void onResponse(String response, int id) {
//                                }
//                            });
//                }
//            }
////照相机
//    private void openCamera1() {
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        startActivityForResult(intent, REQUEST_CODE_CAMERA);
//    }
////相册
//    private void openAlbum() {
//        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/*");
//        startActivityForResult(intent, REQUEST_CODE_ALBUM);
//    }
//    //回调方法，使用相机相册获得文件
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REQUEST_CODE_ALBUM && resultCode == RESULT_OK){
//            if (data != null) {
//                // 照片的原始资源地址
//                Uri uri = data.getData();
//                String path = uri.getPath();
//                //原本context报错，加入了private
//                ContentResolver cr = SpecialistaddpushActivity.this.getContentResolver();
//                try {
//                    Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
//                    /* 将Bitmap设定到ImageView */
//                    ibSpecialistPushinfoImage.setImageBitmap(bitmap);
//                    etSpecialistPushinfoImage.setText(path);
//                } catch (FileNotFoundException e) {
//                    Log.e("Exception", e.getMessage(), e);
//                }
//            }
//        }else if(requestCode == REQUEST_CODE_CAMERA && resultCode == RESULT_OK){
//            if(data != null && data.getData() != null){
//                    Bundle extras = data.getExtras();
//                    Log.e(TAG, String.valueOf(extras));
//
//                        Bitmap bitmap = extras.getParcelable("data");
//                        /* 将Bitmap设定到ImageView */
//                        ibSpecialistPushinfoImage.setImageBitmap(bitmap);
//                        //可将图片保存下来，用于上传或其他操作（如果不需要可以省略此步）
//                        //这里得image也是自己定义的
//                 String path = savePicToSdcard(image,Environment.getExternalStorageDirectory().getPath(),System.currentTimeMillis() + ".jpg");
//
//            }
//        }
//    }
//    public static String savePicToSdcard(Bitmap bitmap, String path, String fileName) {
//        String filePath = "";
//        if (bitmap != null) {
//            filePath = path + File.separator + fileName;
//            File destFile = new File(filePath);
//            OutputStream os = null;
//            try {
//                os = new FileOutputStream(destFile);
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
//                os.flush();
//                os.close();
//            } catch (IOException e) {
//                filePath = "";
//            }
//        }
//        return filePath;
//    }
//
//}