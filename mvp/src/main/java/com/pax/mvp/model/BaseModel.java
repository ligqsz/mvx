package com.pax.mvp.model;

import com.pax.mvp.mvp.IModel;
import com.pax.mvp.presenter.BasePresenter;

/**
 * @author ligq
 * @date 2018/9/28
 */

public class BaseModel<P extends BasePresenter> implements IModel {
    P mPresenter;

    BaseModel(P presenter) {
        this.mPresenter = presenter;
    }
}
