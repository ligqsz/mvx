package com.pax.mvp.ui;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pax.mvp.R;
import com.pax.mvp.contract.LoginContract;
import com.pax.mvp.presenter.LoginPresenter;

/**
 * A login screen that offers login via email/password.
 *
 * @author ligq
 */
public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.ViewContract {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mEmailSignInButton;

    @Override
    protected void initListener() {
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    mPresenter.login(mEmailView.getText().toString(), mPasswordView.getText().toString());
                    return true;
                }
                return false;
            }
        });

        mEmailSignInButton.setOnClickListener(this);
    }

    @Override
    protected void initView() {
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mEmailSignInButton = findViewById(R.id.email_sign_in_button);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    @Override
    protected void loadData() {
        //do nothing
    }

    @Override
    protected LoginPresenter loadPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onClickProtected(View v) {
        if (v.getId() == R.id.email_sign_in_button) {
            mPresenter.login(mEmailView.getText().toString(), mPasswordView.getText().toString());
        }
    }

    @Override
    public void showSuccess(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showUserError(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mEmailView.setError(result);
            }
        });
    }

    @Override
    public void showPwdError(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPasswordView.setError(result);
                mPasswordView.setFocusable(true);
                mPasswordView.setFocusableInTouchMode(true);
                mPasswordView.requestFocus();
            }
        });

    }

    @Override
    public void showFailed(final String result) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressView.setVisibility(View.VISIBLE);
                mLoginFormView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProgressView.setVisibility(View.GONE);
                mLoginFormView.setVisibility(View.VISIBLE);
            }
        });
    }

}

