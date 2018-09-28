package com.pax.mvp.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.pax.mvp.App;

/**
 * @author ligq
 * @date 2018/9/28
 */

@SuppressWarnings("unused")
public class KeyBoardUtils {
    private KeyBoardUtils() {
        throw new IllegalArgumentException();
    }

    public static void hideKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) App.getApp().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void showKeyBoard(View view) {
        InputMethodManager imm = (InputMethodManager) App.getApp().getSystemService(Context.INPUT_METHOD_SERVICE);
        assert imm != null;
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }
}
