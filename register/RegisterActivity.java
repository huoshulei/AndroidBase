package com.example.icogn.mshb.register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.icogn.mshb.R;
import com.example.icogn.mshb.base.BaseActivity;
import com.example.icogn.mshb.view.MyToolbar;

import butterknife.BindView;


public class RegisterActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    MyToolbar toolbar;
    private Intent       intent;
    private RegisterBean bean;


    @Override
    protected int getLayoutResId() {
        return R.layout.register_activity;
    }

    @Override
    protected void configView() {
        setSupportActionBar(toolbar);
    }

    public void setToolbar(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage, menu);
        menu.getItem(0).setTitle("登录");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
            case R.id.action_manage:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initData() {
        bean = new RegisterBean();
        intent = getIntent();
        getFragmentManager().beginTransaction().replace(R.id.fl_register, new VerifyFragment()).commit();
    }


    public void navigateToMain(String phone, String password) {
        Bundle bundle = new Bundle();
        bundle.putString("name", phone);
        bundle.putString("password", password);
        intent.putExtras(bundle);
        setResult(0, intent);
        finish();
    }

    public RegisterBean getBean() {
        return bean;
    }

    /**
     * @param view 注册
     */
    public void register(View view) {
        if (TextUtils.isEmpty(bean.getName())) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(bean.getPassword())) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!bean.getPassword().equals(bean.getRepeatPWD())) {
            Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        http(api.register(bean.getName(), bean.getPhone(), bean.getPassword()), o -> {
        });
    }

    @Override
    protected void onNavigate() {
        navigateToMain(bean.getPhone(), bean.getPassword());
    }




}
