package com.pax.mvp.contract;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author ligq
 * @date 2018/9/28
 */

public interface LoginContract {
    int SUCCESS = 0;
    int INVALID_USER = 1;
    int INVALID_PWD = 2;
    int FAILED = -1;

    interface ViewContract {
        void showSuccess(String result);

        void showUserError(String result);

        void showPwdError(String result);

        void showFailed(String result);

        void showProgress();

        void hideProgress();
    }

    interface PresenterContract {
        void login(String username, String pwd);
    }

    interface ModelContract {
        @LoginResult
        int login(String username, String pwd);
    }

    @IntDef(value = {SUCCESS, INVALID_USER, INVALID_PWD, FAILED})
    @Retention(RetentionPolicy.SOURCE)
    @interface LoginResult {
    }
}
