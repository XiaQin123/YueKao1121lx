package com.bwie.xiaqin.yuekao1121lx.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.xiaqin.yuekao1121lx.R;
import com.bwie.xiaqin.yuekao1121lx.adapter.RecyclerAdapter;
import com.bwie.xiaqin.yuekao1121lx.bean.RecycleBean;
import com.bwie.xiaqin.yuekao1121lx.mvp.presenter.RecyclerPresenter;
import com.bwie.xiaqin.yuekao1121lx.mvp.view.RecyclerIView;
import com.bwie.xiaqin.yuekao1121lx.sousuobuju.SuoActivity;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.List;

public class ShouFragmenter extends Fragment implements RecyclerIView{
    private RecyclerView recy_view;
    private RecyclerPresenter presenter;
    private List<RecycleBean.DataBean> ListBean;
    private RecyclerAdapter adapet;
    private EditText shousuo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shou_fragmenter, container, false);
        recy_view = view.findViewById(R.id.recy_view);
        //搜索框
        view.findViewById(R.id.shousuo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               startActivity(new Intent(getActivity(),SuoActivity.class));

            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recy_view.setLayoutManager(manager);
        ListBean = new ArrayList<>();
        adapet = new RecyclerAdapter(getActivity(), ListBean);
        recy_view.setAdapter(adapet);
        presenter = new RecyclerPresenter();
        presenter.attach(this);
        presenter.get();
        //布局管理器
        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recy_view.setLayoutManager(layoutManager);
        return view;
    }

    @Override
    public void onSuccess(List<RecycleBean.DataBean> list) {
        if (list != null){
            ListBean.clear();
            ListBean.addAll(list);
            adapet.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailed(Exception e) {
        Toast.makeText(getActivity(),"网络连接错误",Toast.LENGTH_LONG).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }
}
