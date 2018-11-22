package com.bwie.xiaqin.yuekao1121lx.mvp.view;

import com.bwie.xiaqin.yuekao1121lx.bean.RecycleBean;

import java.util.List;

/**
 * Created by lenovo on 2018/11/21.
 */

public interface RecyclerIView {
    void onSuccess(List<RecycleBean.DataBean> list);

    void onFailed(Exception e);

}
