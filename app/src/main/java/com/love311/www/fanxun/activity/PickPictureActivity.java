package com.love311.www.fanxun.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.adapter.PickPictureAdapter;
import com.pizidea.imagepicker.AndroidImagePicker;
import com.pizidea.imagepicker.ImgLoader;
import com.pizidea.imagepicker.UilImgLoader;
import com.pizidea.imagepicker.Util;
import com.pizidea.imagepicker.bean.ImageItem;
import com.pizidea.imagepicker.ui.activity.ImagesGridActivity;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/8/19.
 */
public class PickPictureActivity extends AutoLayoutActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private final int REQ_IMAGE = 1433;
    @BindView(R.id.single_choose)
    Button singleChoose;
    @BindView(R.id.multi_choose)
    Button multiChoose;
    SelectAdapter mAdapter;
    @BindView(R.id.gridview)
    GridView gridview;
    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    private int screenWidth;
    ImgLoader presenter = new UilImgLoader();
    List<ImageItem> imageList;
    private Intent intent;
    private String statusString;
    private ArrayList<String> listPath= new ArrayList<>();
    private PickPictureAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_picture_activity);
        ButterKnife.bind(this);
        Intent intent1 = this.getIntent();
        statusString = intent1.getStringExtra("picture");
        listPath = intent1.getStringArrayListExtra("list_path");
        mAdapter = new SelectAdapter(this);
        gridview.setAdapter(mAdapter);
        if (listPath != null) {
            adapter = new PickPictureAdapter(this, listPath);
            gridview.setAdapter(adapter);
            Log.d(TAG,"-------listpath调用了");
        }
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();
        singleChoose.setOnClickListener(this);
        multiChoose.setOnClickListener(this);
        topLeft.setOnClickListener(this);
        topMid.setText("房源图片");
        topRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        int requestCode = REQ_IMAGE;
        boolean isShowCamera = true;
        imageList = AndroidImagePicker.getInstance().getSelectedImages();
        switch (view.getId()) {

            case R.id.single_choose:
                /*AndroidImagePicker.getInstance().setSelectMode(AndroidImagePicker.Select_Mode.MODE_SINGLE);
                AndroidImagePicker.getInstance().setShouldShowCamera(false);
                break;*/
                AndroidImagePicker.getInstance().pickSingle(PickPictureActivity.this, isShowCamera, new AndroidImagePicker.OnImagePickCompleteListener() {
                    @Override
                    public void onImagePickComplete(List<ImageItem> items) {
                        if (items != null && items.size() > 0) {
                            Log.i(TAG, "=====选择了：" + items.get(0).path);
                            mAdapter.clear();
                            mAdapter.addAll(items);
                        }
                    }
                });
                return;
            case R.id.multi_choose:
                /*AndroidImagePicker.getInstance().setSelectMode(AndroidImagePicker.Select_Mode.MODE_MULTI);
                AndroidImagePicker.getInstance().setShouldShowCamera(false);
                break;*/
                AndroidImagePicker.getInstance().pickMulti(PickPictureActivity.this, isShowCamera, new AndroidImagePicker.OnImagePickCompleteListener() {
                    @Override
                    public void onImagePickComplete(List<ImageItem> items) {
                        if (items != null && items.size() > 0) {
                            Log.i(TAG, "=====选择了：" + items.get(0).path);
                            mAdapter.clear();
                            mAdapter.addAll(items);
                        }
                    }
                });
                return;

//            case R.id.crop:
//
//                AndroidImagePicker.getInstance().pickAndCrop(PickPictureActivity.this, true, 120, new AndroidImagePicker.OnImageCropCompleteListener() {
//                    @Override
//                    public void onImageCropComplete(Bitmap bmp, float ratio) {
//                        Log.i(TAG,"=====onImageCropComplete (get bitmap="+bmp.toString());
//                        ivCrop.setVisibility(View.VISIBLE);
//                        ivCrop.setImageBitmap(bmp);
//                    }
//                });

//                return;
            case R.id.top_left:
                intent = new Intent();
                intent.putExtra("picture", statusString);
                PickPictureActivity.this.setResult(RESULT_OK, intent);
                PickPictureActivity.this.finish();
                return;
            case R.id.top_right:
                intent = new Intent();
                intent.putExtra("picture", imageList.size() + "张");
                for (int i = 0; i < imageList.size(); i++) {
                    Log.d(TAG,imageList.get(i).getPath());
                    Log.d(TAG,listPath.toString());
                    listPath.add(imageList.get(i).getPath());
                }
                Log.d("TEST_path-----", listPath.toString());
                intent.putStringArrayListExtra("list_path", listPath);
                Log.d("TEST----", imageList.toString());
                PickPictureActivity.this.setResult(RESULT_OK, intent);
                PickPictureActivity.this.finish();
                return;
            default:
                break;
        }
        intent.setClass(this, ImagesGridActivity.class);
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQ_IMAGE) {
                // ivCrop.setVisibility(View.GONE);
                mAdapter.clear();
                mAdapter.addAll(imageList);
            }/*else if(requestCode == REQ_IMAGE_CROP){
                Bitmap bmp = (Bitmap)data.getExtras().get("bitmap");
                Log.i(TAG,"-----"+bmp.getRowBytes());
            }*/
        }

    }

    class SelectAdapter extends ArrayAdapter<ImageItem> {

        //private int mResourceId;
        public SelectAdapter(Context context) {
            super(context, 0);
            //this.mResourceId = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageItem item = getItem(position);
            LayoutInflater inflater = getLayoutInflater();
            //View view = inflater.inflate(mResourceId, null);
            int width = (screenWidth - Util.dp2px(PickPictureActivity.this, 10 * 2)) / 3;

            ImageView imageView = new ImageView(PickPictureActivity.this);
            imageView.setBackgroundColor(Color.WHITE);
            GridView.LayoutParams params = new AbsListView.LayoutParams(width, width);
            imageView.setLayoutParams(params);
            presenter.onPresentImage(imageView, item.path, width);
            Log.d("TEST-----------", item.path);
            return imageView;
        }
    }

    @Override
    public void onBackPressed() {
        intent = new Intent();
        intent.putExtra("picture", statusString);
        PickPictureActivity.this.setResult(RESULT_OK, intent);
        PickPictureActivity.this.finish();
    }
}
