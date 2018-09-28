package com.pax.mvp.model;

import android.os.SystemClock;
import android.text.TextUtils;

import com.pax.mvp.contract.LoginContract;
import com.pax.mvp.presenter.LoginPresenter;

import java.util.Random;

/**
 * @author ligq
 * @date 2018/9/28
 */

public class LoginModel extends BaseModel<LoginPresenter> implements LoginContract.ModelContract {
    public LoginModel(LoginPresenter presenter) {
        super(presenter);
    }

    @Override
    public int login(String username, String pwd) {
        if (TextUtils.isEmpty(username) || !checkInfo(username)) {
            return LoginContract.INVALID_USER;
        }

        if (TextUtils.isEmpty(pwd) || !checkInfo(pwd)) {
            return LoginContract.INVALID_PWD;
        }
        SystemClock.sleep(2000);
        int i = new Random().nextInt(10);
        return i % 2 == 0 ? LoginContract.SUCCESS : LoginContract.FAILED;
    }

    private boolean checkInfo(String str) {
        return str.length() < 10 && str.length() > 4;
    }
}
