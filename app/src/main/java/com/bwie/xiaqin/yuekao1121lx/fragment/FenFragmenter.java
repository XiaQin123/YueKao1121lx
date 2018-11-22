package com.bwie.xiaqin.yuekao1121lx.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwie.xiaqin.yuekao1121lx.R;
import com.bwie.xiaqin.yuekao1121lx.adapter.LeftAdapter;
import com.bwie.xiaqin.yuekao1121lx.adapter.RightAdapter;
import com.bwie.xiaqin.yuekao1121lx.bean.FenLeftBean;
import com.bwie.xiaqin.yuekao1121lx.bean.FenRightBean;
import com.bwie.xiaqin.yuekao1121lx.home.left.presenter.LeftPresenter;
import com.bwie.xiaqin.yuekao1121lx.home.left.view.LeftIView;
import com.bwie.xiaqin.yuekao1121lx.home.right.presenter.RightPresenter;
import com.bwie.xiaqin.yuekao1121lx.home.right.view.RightIView;

import java.util.ArrayList;
import java.util.List;

public class FenFragmenter extends Fragment implements LeftIView,RightIView{
    private RecyclerView recleft;
    private LeftAdapter leftAdapter;
    private List<FenLeftBean.DataBean> beanList;
    private LeftPresenter leftPresenter;
    private LinearLayout layoutright;
    private RightAdapter rightAdapter;
    private RightPresenter rightPresenter;
    private List<FenRightBean.DataBean> beanright;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fen_fragmenter, container, false);
        recleft = view.findViewById(R.id.rec_left);
        layoutright = view.findViewById(R.id.right_lnl);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1, LinearLayoutManager.VERTICAL, false);
        recleft.setLayoutManager(layoutManager);
        beanList = new ArrayList<>();
        beanright = new ArrayList<>();
        leftAdapter = new LeftAdapter(getActivity(), beanList);
        leftAdapter.setOnItemClickListener(new LeftAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemview, int position) {
                FenLeftBean.DataBean dataBean = beanList.get(position);
                rightPresenter.getright("http://www.zhaoapi.cn/product/getProductCatagory?cid="+dataBean.getCid());

            }
        });
        recleft.setAdapter(leftAdapter);
        leftPresenter = new LeftPresenter();
        leftPresenter.attch(this);
        leftPresenter.getleft();
        rightPresenter = new RightPresenter();
        rightPresenter.attch(this);

    }

    @Override
    public void getLeft(List<FenLeftBean.DataBean> list) {
        if (list!=null){
            beanList.clear();
            beanList.addAll(list);
            leftAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void getrights(List<FenRightBean.DataBean> list) {
        if (list!=null){
            layoutright.removeAllViews();
            for (int i=0;i<list.size();i++){
                TextView textView = new TextView(getActivity());
                textView.setText(list.get(i).getName());
                RecyclerView rvNextb = new RecyclerView(getActivity());
                RecyclerView.LayoutManager layoutManager2 = new GridLayoutManager(getActivity(), 3);
                rvNextb.setLayoutManager(layoutManager2);
                rightAdapter = new RightAdapter(getActivity(), list.get(i).getList());
                rvNextb.setAdapter(rightAdapter);
                beanright.clear();
                beanright.addAll(list);
                rightAdapter.notifyDataSetChanged();
                layoutright.addView(textView);
                layoutright.addView(rvNextb);

            }
        }

    }

    @Override
    public void failed(Exception e) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (leftPresenter!=null){
            leftPresenter.detach();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        rightPresenter.getright("http://www.zhaoapi.cn/product/getProductCatagory?cid=1");

    }
    }