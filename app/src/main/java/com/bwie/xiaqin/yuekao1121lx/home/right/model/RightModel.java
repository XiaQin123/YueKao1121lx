package com.bwie.xiaqin.yuekao1121lx.home.right.model;

import com.bwie.xiaqin.yuekao1121lx.feilei.net.ICallBack;
import com.bwie.xiaqin.yuekao1121lx.feilei.utils.HttpUtils;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2018/11/21.
 */

public class RightModel {
    public void getrights(String url, ICallBack callBack, Type type){
        HttpUtils.getInstance().get(url,callBack,type);
    }

}
