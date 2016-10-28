//package com.example.icogn.mshb.view;
//
//import android.content.Context;
//import android.content.Intent;
//import android.util.AttributeSet;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import com.example.icogn.mshb.R;
//import com.example.icogn.mshb.shb.dialog_activity.ClassifyActivity;
//import com.example.icogn.mshb.shb.seek.SeekActivity;
//
//
///**
// * 项目名称:  MSHB
// * 类描述:
// * 创建人:    ICOGN
// * 创建时间:  2016/9/19 15:55
// * 修改人:    ICOGN
// * 修改时间:  2016/9/19 15:55
// * 备注:
// * 版本:
// */
//public class Seek extends LinearLayout implements View.OnClickListener {
//
//    public Seek(Context context) {
//        this(context, null);
//    }
//
//    public Seek(Context context, AttributeSet attrs) {
//        this(context, attrs, 0);
//    }
//
//    public Seek(Context context, AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//        View.inflate(context, R.layout.rob_commodity_item, this);
//        findViewById(R.id.ll_classify).setOnClickListener(this);
//        findViewById(R.id.tv_seek).setOnClickListener(this);
//        findViewById(R.id.ll_scan).setOnClickListener(this);
//    }
//
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.ll_classify:
//                /**分类*/
//                getContext().startActivity(new Intent(getContext(), ClassifyActivity.class));
//                break;
//            case R.id.ll_scan:
//                /**扫描二维码*/
//                Toast.makeText(getContext(), "扫描二维码", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.tv_seek:
//                getContext().startActivity(new Intent(getContext(), SeekActivity.class));
//                break;
//        }
//    }
//}
