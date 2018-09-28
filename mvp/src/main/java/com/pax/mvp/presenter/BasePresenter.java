package com.pax.mvp.presenter;

import com.pax.mvp.mvp.IModel;
import com.pax.mvp.mvp.IPresenter;
import com.pax.mvp.mvp.IView;

import java.lang.ref.WeakReference;

/**
 * @author ligq
 * @date 2018/9/27
 */

public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter {
    private WeakReference<V> viewReference;
    protected M iModel;

    public BasePresenter() {
        iModel = loadModel();
    }

    public abstract M loadModel();

    @SuppressWarnings("unchecked")
    @Override
    public void attachView(IView view) {
        viewReference = new WeakReference<>((V) view);
    }

    @Override
    public void detachView() {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }

    @Override
    public V getView() {
        return viewReference.get();
    }
}
