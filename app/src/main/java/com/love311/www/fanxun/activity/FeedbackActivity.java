package com.love311.www.fanxun.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.love311.www.fanxun.R;
import com.love311.www.fanxun.application.MyApplication;
import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/9/2.
 */
public class FeedbackActivity extends AutoLayoutActivity {

    @BindView(R.id.top_left)
    ImageView topLeft;
    @BindView(R.id.top_mid)
    TextView topMid;
    @BindView(R.id.top_right)
    TextView topRight;
    @BindView(R.id.et_feedback)
    EditText etFeedback;
    @BindView(R.id.iv_submit)
    ImageView ivSubmit;
    private String url = "app/suggestion/ajax/create?";
    private MyApplication my;
    private static String URL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_activity);
        ButterKnife.bind(this);
        my = (MyApplication) getApplication();
        URL = my.getURL() + url;
        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackActivity.this.finish();
            }
        });
        topMid.setText("建议反馈");
        topRight.setVisibility(View.GONE);
        ivSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etFeedback.getText().toString().equals("")){
                    OkHttpUtils
                            .post()
                            .url(URL)
                            .addParams("content", etFeedback.getText().toString())
                            .build()
                            .execute(new StringCallback() {
                                @Override
                                public void onError(Call call, Exception e, int id) {
                                    Toast.makeText(FeedbackActivity.this, "意见提交失败！",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onResponse(String response, int id) {
                                    Toast.makeText(FeedbackActivity.this, "意见提交成功！",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                }else {
                    Toast.makeText(FeedbackActivity.this, "信息为空，请先填写意见反馈！",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
