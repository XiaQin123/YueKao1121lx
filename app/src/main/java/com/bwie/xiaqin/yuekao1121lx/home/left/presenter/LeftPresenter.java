package com.bwie.xiaqin.yuekao1121lx.home.left.presenter;


import com.bwie.xiaqin.yuekao1121lx.bean.FenLeftBean;
import com.bwie.xiaqin.yuekao1121lx.feilei.net.ICallBack;
import com.bwie.xiaqin.yuekao1121lx.home.left.model.LeftModel;
import com.bwie.xiaqin.yuekao1121lx.home.left.view.LeftIView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2018/11/21.
 */

public class LeftPresenter {
    private LeftIView iv;
    private LeftModel leftmodel;
    public void attch(final LeftIView iv){
        this.iv = iv;
        leftmodel = new LeftModel();
    }
    public void getleft(){
        Type type = new TypeToken<FenLeftBean>(){}.getType();
        leftmodel.getlefts("http://www.zhaoapi.cn/product/getCatagory", new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                FenLeftBean leftBean = (FenLeftBean) obj;
                if (leftBean!=null){
                    iv.getLeft(leftBean.getData());
                }
            }

            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }
        },type);
    }
    public void detach(){
        if (iv!=null){
            iv = null;
        }
    }
}
