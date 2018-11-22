package com.bwie.xiaqin.yuekao1121lx.mvp.model;

import com.bwie.xiaqin.yuekao1121lx.recycleUtils.HttpUtils;
import com.bwie.xiaqin.yuekao1121lx.recyclernet.ICallBack;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2018/11/21.
 */

public class RecycleModel {
    public void getData(String url, ICallBack callBack, Type type){
        HttpUtils.getInstance().get(url,callBack,type);
    }

}
