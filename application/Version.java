package com.example.icogn.mshb;

import android.os.Build;

/**
 * 项目名称:  MSHB
 * 类描述:
 * 创建人:    ICOGN
 * 创建时间:  2016/10/24 11:16
 * 修改人:    ICOGN
 * 修改时间:  2016/10/24 11:16
 * 备注:
 * 版本:
 */

public final class Version {

//    private static boolean m;

    public static boolean isM() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }
}
