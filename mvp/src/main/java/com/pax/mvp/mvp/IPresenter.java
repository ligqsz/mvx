package com.pax.mvp.mvp;

/**
 * @author ligq
 * @date 2018/9/27
 */

public interface IPresenter<V extends IView> {
    void attachView(V view);

    void detachView();

    V getView();
}
