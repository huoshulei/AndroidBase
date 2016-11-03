package com.example.icogn.mshb.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.example.icogn.mshb.App;
import com.example.icogn.mshb.R;
import com.example.icogn.mshb.base.BaseActivity;
import com.example.icogn.mshb.rests.idea.IdeaActivity;

import java.util.List;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/9/19 15:55
 * 修改人:    ICOGN
 * 修改时间:  2016/9/19 15:55
 * 备注:
 * 版本:
 */
public class TelOffer extends LinearLayout implements View.OnClickListener {

    public TelOffer(Context context) {
        this(context, null);
    }

    public TelOffer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TelOffer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.offer, this);
        findViewById(R.id.tv_offer_tel).setOnClickListener(this);
        findViewById(R.id.tv_offer_idea).setOnClickListener(this);
        findViewById(R.id.tv_offer_home).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_offer_home:
                List<BaseActivity> activities = App.application.getActivities();
                for (Activity activity : activities) {
                    BaseActivity baseActivity = (BaseActivity) activity;
                    baseActivity.finish();
                }
                break;
            case R.id.tv_offer_tel:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + getResources().getString(R.string.service_tel)));
                getContext().startActivity(intent);
                break;
            case R.id.tv_offer_idea:
                getContext().startActivity(new Intent(getContext(), IdeaActivity.class));
                break;
        }
    }
}
