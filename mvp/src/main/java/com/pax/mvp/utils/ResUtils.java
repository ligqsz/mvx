package com.pax.mvp.utils;

import com.pax.mvp.App;

/**
 * @author ligq
 * @date 2018/9/28
 */

public class ResUtils {
    private ResUtils() {
        throw new IllegalArgumentException();
    }
    public static String getString(int id){
        return App.getApp().getString(id);
    }
}
