package com.bwie.xiaqin.yuekao1121lx.home.left.view;

import com.bwie.xiaqin.yuekao1121lx.bean.FenLeftBean;

import java.util.List;

/**
 * Created by lenovo on 2018/11/21.
 */

public interface LeftIView {

     void getLeft(List<FenLeftBean.DataBean> list);

    void failed(Exception e);

}
