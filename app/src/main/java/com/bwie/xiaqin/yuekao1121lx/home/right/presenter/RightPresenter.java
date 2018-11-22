package com.bwie.xiaqin.yuekao1121lx.home.right.presenter;

import com.bwie.xiaqin.yuekao1121lx.bean.FenRightBean;
import com.bwie.xiaqin.yuekao1121lx.feilei.net.ICallBack;
import com.bwie.xiaqin.yuekao1121lx.fragment.FenFragmenter;
import com.bwie.xiaqin.yuekao1121lx.home.right.model.RightModel;
import com.bwie.xiaqin.yuekao1121lx.home.right.view.RightIView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2018/11/21.
 */

public class RightPresenter {
    private RightIView iv;
    private RightModel rightModel;
    public void attch(FenFragmenter iv){
        this.iv = iv;
        rightModel = new RightModel();
    }
    public void getright(String url) {
        Type type = new TypeToken<FenRightBean>() {
        }.getType();
        rightModel.getrights(url,new ICallBack(){

            @Override
            public void onSuccess(Object obj) {
                FenRightBean rightBean = (FenRightBean) obj;
                if (rightBean!=null){
                    iv.getrights(rightBean.getData());
                }
            }

            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }
        },type);
    }
    public void detach() {
        if (iv != null) {
            iv = null;
        }
    }
}
