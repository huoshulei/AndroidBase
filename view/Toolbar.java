package com.example.icogn.mshb.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.icogn.mshb.R;

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
public class Toolbar extends LinearLayout implements View.OnClickListener {
    LinearLayout llCity;
    ImageView    tBarShopCar;
    ImageView    tBarAction;
    TextView     tvTBarCity;

    public Toolbar(Context context) {
        this(context, null);
    }

    public Toolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        LayoutInflater.from(context).inflate(R.layout.layout_toolbar, this, true);
        View view = View.inflate(context, R.layout.layout_toolbar, this);
        llCity = (LinearLayout) view.findViewById(R.id.ll_city);
        tBarShopCar = (ImageView) view.findViewById(R.id.t_bar_shop_car);
        tBarAction = (ImageView) view.findViewById(R.id.t_bar_action);
        tvTBarCity = (TextView) view.findViewById(R.id.tv_t_bar_city);
        llCity.setOnClickListener(this);
        tBarAction.setOnClickListener(this);
        tBarShopCar.setOnClickListener(this);
    }

    public Toolbar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setCity(String city) {
        tvTBarCity.setText(city);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_city:
                Toast.makeText(getContext(), "定位城市", Toast.LENGTH_SHORT).show();
                break;
            case R.id.t_bar_shop_car:
                Toast.makeText(getContext(), "购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.t_bar_action:
                Toast.makeText(getContext(), "Action", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
