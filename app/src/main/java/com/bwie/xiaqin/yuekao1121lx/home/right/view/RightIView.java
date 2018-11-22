package com.bwie.xiaqin.yuekao1121lx.home.right.view;

import com.bwie.xiaqin.yuekao1121lx.bean.FenRightBean;

import java.util.List;

/**
 * Created by lenovo on 2018/11/21.
 */

public interface RightIView {
    void getrights(List<FenRightBean.DataBean> list);
    void failed(Exception e);
}
