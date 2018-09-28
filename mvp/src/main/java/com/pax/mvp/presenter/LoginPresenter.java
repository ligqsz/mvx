package com.pax.mvp.presenter;

import com.pax.mvp.App;
import com.pax.mvp.R;
import com.pax.mvp.contract.LoginContract;
import com.pax.mvp.model.LoginModel;
import com.pax.mvp.ui.LoginActivity;
import com.pax.mvp.utils.KeyBoardUtils;
import com.pax.mvp.utils.ResUtils;

/**
 * @author ligq
 * @date 2018/9/28
 */

public class LoginPresenter extends BasePresenter<LoginActivity, LoginModel> implements LoginContract.PresenterContract {
    @Override
    public LoginModel loadModel() {
        return new LoginModel(this);
    }

    @Override
    public void login(final String username, final String pwd) {
        final LoginActivity view = getView();
        if (view == null) {
            return;
        }
        KeyBoardUtils.hideKeyBoard(view.getCurrentFocus());
        view.showProgress();
        App.getApp().runInBackGround(new Runnable() {
            @Override
            public void run() {
                int result = iModel.login(username, pwd);
                view.hideProgress();
                switch (result) {
                    case LoginContract.SUCCESS:
                        view.showSuccess(ResUtils.getString(R.string.prompt_success));
                        break;
                    case LoginContract.FAILED:
                        view.showFailed(ResUtils.getString(R.string.prompt_failed));
                        break;
                    case LoginContract.INVALID_PWD:
                        view.showPwdError(ResUtils.getString(R.string.error_invalid_password));
                        break;
                    case LoginContract.INVALID_USER:
                        view.showUserError(ResUtils.getString(R.string.error_invalid_email));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
