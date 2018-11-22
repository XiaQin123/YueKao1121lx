package com.bwie.xiaqin.yuekao1121lx.mvp.presenter;

import com.bwie.xiaqin.yuekao1121lx.bean.RecycleBean;
import com.bwie.xiaqin.yuekao1121lx.mvp.model.RecycleModel;
import com.bwie.xiaqin.yuekao1121lx.mvp.view.RecyclerIView;
import com.bwie.xiaqin.yuekao1121lx.recyclernet.ICallBack;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by lenovo on 2018/11/21.
 */

public class RecyclerPresenter {
    private RecyclerIView iv;
    private RecycleModel model;
    public void attach(RecyclerIView iv) {
        this.iv = iv;
        model = new RecycleModel();
    }

    public void get(){

        Type type = new TypeToken<RecycleBean>(){}.getType();

        model.getData("http://www.xieast.com/api/news/news.php", new ICallBack() {
            @Override
            public void onSuccess(Object o) {
                RecycleBean Bean = (RecycleBean) o;
                if (Bean != null){
                    iv.onSuccess(Bean.getData());
                }
            }

            @Override
            public void onFailed(Exception e) {
                iv.onFailed(e);
            }
        },type);
    }



    public void detach() {
        if (iv != null){
            iv=null;
        }
    }

}
