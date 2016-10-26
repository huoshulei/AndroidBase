package com.example.icogn.mshb.register;

import com.android.databinding.library.baseAdapters.BR;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.icogn.mshb.R;
import com.example.icogn.mshb.base.BaseDataFragment;
import com.example.icogn.mshb.base.HttpResult;
import com.example.icogn.mshb.utils.RegularUtils;
import com.example.icogn.mshb.utils.logger.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyFragment extends BaseDataFragment {

    private Handler      handler;
    private RegisterBean bean;
    private TimeRunnable runnable;

    public VerifyFragment() {
        // Required empty public constructor
    }

    private RegisterActivity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
        activity = (RegisterActivity) getActivity();
        activity.setToolbar("验证手机号");
        bean = activity.getBean();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.register_verify_fragment;
    }


    @Override
    public void configView(View view) {

    }

    @Override
    protected void initData() {
        setData(bean);
        setData(BR.module, this);
    }

    @Override
    public void showProgress() {
        bean.setProgress(true);
    }


    /**
     * 关闭加载动画
     */
    @Override
    public void dismissProgress() {
        bean.setProgress(false);
    }

    @Override
    protected void onNavigate() {
        FragmentManager     fragmentManager     = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_register, new RegisterFragment()).commit();
    }


    private void requestVerificationCode() {
        api.yanzheng(bean.getPhone()).enqueue(new Callback<HttpResult<Object>>() {
            @Override
            public void onResponse(Call<HttpResult<Object>> call, Response<HttpResult<Object>> response) {
                if (response.code() != 200) {
                    handler.removeCallbacks(runnable);
                    bean.setTime("获取验证码");
                    bean.setTiming(false);
                    return;
                }
                if (response.body().getResultCode() != 200) {
                    handler.removeCallbacks(runnable);
                    bean.setTime("获取验证码");
                    bean.setTiming(false);
//                    return;
                }
                Toast.makeText(activity, response.body().getState(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<HttpResult<Object>> call, Throwable t) {
            }
        });
    }

    /**
     * 发送验证码延迟时间
     */
    private void delayTime() {
        bean.setTiming(true);
        runnable = new TimeRunnable(handler) {
            @Override
            protected void overTime() {
                bean.setTime("获取验证码");
                bean.setTiming(false);
                bean.setProgress(false);
            }

            @Override
            public void turnoverTime(int time) {
                bean.setTime(time + "s");
            }
        };
        handler.postDelayed(runnable, 1000);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        activity = null;
    }

    /**
     * @param view 发送验证码
     */
    public void auth(View view) {
        if (!RegularUtils.isMobileExact(bean.getPhone())) {
            Toast.makeText(activity, "手机号码格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        delayTime();
        requestVerificationCode();
    }

    /**
     * @param view 下一步
     */
    public void next(View view) {
        if (TextUtils.isEmpty(bean.getAuthCode())) {
            Toast.makeText(activity, "验证码为空", Toast.LENGTH_SHORT).show();
            return;
        }
        http(api.dtmima(bean.getAuthCode(), bean.getPhone()), o -> {
            Logger.d("VerifyFragment next:" + bean.getAuthCode() + bean.getPhone());
        });
    }
}
