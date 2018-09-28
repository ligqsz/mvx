package com.pax.mvp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.pax.mvp.mvp.IView;
import com.pax.mvp.presenter.BasePresenter;

/**
 * @author ligq
 * @date 2018/9/28
 */

@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IView, View.OnClickListener {
    protected P mPresenter;
    protected View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getView());
        mPresenter = loadPresenter();
        loadData();
        initView();
        initListener();
    }

    protected abstract void initListener();

    protected abstract void initView();

    protected abstract void loadData();

    protected View getView() {
        mView = LayoutInflater.from(this).inflate(getLayoutId(), null);
        return mView;
    }

    protected abstract P loadPresenter();

    protected abstract int getLayoutId();

    @Override
    public void onClick(View v) {
        onClickProtected(v);
    }

    protected abstract void onClickProtected(View v);

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
