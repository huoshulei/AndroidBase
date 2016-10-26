package com.example.icogn.mshb.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.icogn.mshb.Constants;
import com.example.icogn.mshb.R;
import com.example.icogn.mshb.base.BaseDataActivity;
import com.example.icogn.mshb.entity.User;
import com.example.icogn.mshb.register.RegisterActivity;
import com.example.icogn.mshb.utils.SPUtils;

/**
 *
 */
public class LoginActivity extends BaseDataActivity {
    public static final int REQUEST_CODE = 0;
    private LoginBean bean;

    @Override
    protected int getLayoutResId() {
        return R.layout.login_activity;
    }

    /**
     * 加载布局
     */
    @Override
    protected void configView() {
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_manage, menu);
        menu.getItem(0).setTitle("注册");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.action_manage:
                startActivityForResult(new Intent(this, RegisterActivity.class), REQUEST_CODE);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 初始化数据
     */
    @Override
    protected void initData() {
        bean = new LoginBean();
        String phone    = SPUtils.getString(this, Constants.USER_PHONE);
        String password = SPUtils.getString(this, Constants.USER_PASSWORD);
        if (phone != null && password != null && !phone.isEmpty() && !password.isEmpty()) {
            login(phone, password);
        }
        setData(bean);
    }


    /**
     * 关闭加载动画
     */
    @Override
    public void dismissProgress() {
        bean.setProgress(false);
    }

    @Override
    protected void showProgress() {
        bean.setProgress(true);
    }

    /**
     * 导航至主页
     */
    @Override
    public void onNavigate() {
        setResult(0);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) return;
        switch (requestCode) {
            case REQUEST_CODE:
                Bundle extras = data.getExtras();
                String phone = extras.getString("name");
                String password = extras.getString("password");
                login(phone, password);
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void user(User user) {
        SPUtils.putBoolean(this, Constants.USER_IS_LOGIN, true);
        SPUtils.putString(this, Constants.USER_NAME, user.getName());
        SPUtils.putString(this, Constants.USER_ID, user.getUserId());
        SPUtils.putString(this, Constants.USER_ICON, user.getIcon());
        SPUtils.putString(this, Constants.USER_PHONE, user.getPhone());
        SPUtils.putString(this, Constants.USER_BABY_NUM, user.getBabyNum());
        SPUtils.putString(this, Constants.USER_SPOOR, user.getSpoor());
        SPUtils.putString(this, Constants.USER_NICKNAME, user.getNickname());
        SPUtils.putString(this, Constants.USER_SEX, user.getSex());
        SPUtils.putInt(this, Constants.USER_TYPE, user.getUserType());
    }


    /**
     * 登录
     *
     * @param phone    手机号
     * @param password 密码
     */
    private void login(String phone, String password) {
        http(api.login(phone, password), this::user);
        SPUtils.putString(this, Constants.USER_PASSWORD, password);
    }

    public void login(View view) {
        if (TextUtils.isEmpty(bean.getPhone())) {
            Toast.makeText(LoginActivity.this, "手机号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(bean.getPassword())) {
            Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        login(bean.getPhone(), bean.getPassword());
    }

    /**
     * @param view 忘记密码
     */
    public void forget(View view) {
        // TODO: 2016/10/15 找回密码
        Toast.makeText(LoginActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
    }
}
