package com.example.icogn.mshb.register;

import android.os.Handler;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/9/10 14:55
 * 修改人:    ICOGN
 * 修改时间:  2016/9/10 14:55
 * 备注:
 * 版本:
 */
abstract class TimeRunnable implements Runnable {
    private Handler mHandler;
    private int time = 60;

    TimeRunnable(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void run() {
        time = time - 1;
        turnoverTime(time);
        if (time > 0) {
            mHandler.postDelayed(this, 1000);
        } else {
            overTime();
        }
    }

    protected abstract void overTime();

    public abstract void turnoverTime(int time);
}
