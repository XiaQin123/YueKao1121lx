package com.bwie.xiaqin.yuekao1121lx.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.bwie.xiaqin.yuekao1121lx.R;
import com.bwie.xiaqin.yuekao1121lx.adapter.CarAdapter;
import com.bwie.xiaqin.yuekao1121lx.bean.CarInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CarFragmenter extends Fragment implements View.OnClickListener{

    public static final String TAG = "CarFragmenter";
    String url = "http://www.zhaoapi.cn/product/getCarts?uid=71";
    private ExpandableListView elc_show_main;
    private CheckBox cb_allCheck_main;
    private TextView btn_allPrice_main;
    private Button btn_allNumber_main;
    private CarAdapter shoppAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.car_fragmenter, container, false);
        elc_show_main = view.findViewById(R.id.elc_show_gw);
        cb_allCheck_main = view.findViewById(R.id.cb_allCheck_gw);
        btn_allPrice_main = view.findViewById(R.id.btn_allPrice_gw);
        btn_allNumber_main = view.findViewById(R.id.btn_allNumber_gw);
        cb_allCheck_main.setOnClickListener(this);
        btn_allNumber_main.setOnClickListener(this);
        initData();
        return view;


    }

    private void initData() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e );
            }

            @Override
            public void onResponse(final Call call, Response response) throws IOException {
                final String string = response.body().string();
                getActivity().runOnUiThread( new Runnable(){
                    private List<CarInfo.DataBean> sellerData;
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        CarInfo shoppBean = gson.fromJson(string, CarInfo.class);
                        sellerData = shoppBean.getData();

                        shoppAdapter = new CarAdapter(sellerData, getActivity());
                        shoppAdapter.setOnCartListChangeListener(new CarAdapter.OnCartListChangeListener() {
                            @Override
                            public void SellerSelectedChange(int groupPosition) {
                                boolean b = shoppAdapter.isCurrentSellerAllProductSelected(groupPosition);
                                //改变所有当前商家的选中状态
                                shoppAdapter.changeCurrentSellerAllProductSelected(groupPosition,!b);
                                shoppAdapter.notifyDataSetChanged();

                                refreshAllSelectedAndTotalPriceAndTotalNumber();
                            }

                            @Override
                            public void changeCurrentProductSelected(int groupPosition, int childPosition) {
                                shoppAdapter.changeCurrentProductSelected(groupPosition,childPosition);
                                shoppAdapter.notifyDataSetChanged();
                                refreshAllSelectedAndTotalPriceAndTotalNumber();
                            }

                            @Override
                            public void ProductNumberChange(int groupPosition, int childPosition, int number) {
                                shoppAdapter.changeCurrentProductNumber(groupPosition,childPosition,number);
                                shoppAdapter.notifyDataSetChanged();
                                //刷新底部的方法
                                refreshAllSelectedAndTotalPriceAndTotalNumber();
                            }
                        });
                        elc_show_main.setAdapter(shoppAdapter);
                        for (int i = 0;i<sellerData.size();i++){
                            elc_show_main.expandGroup(i);
                        }
                    }
                });
            }
        });
    }
    private void  refreshAllSelectedAndTotalPriceAndTotalNumber(){

        boolean allProductsSelected = shoppAdapter.isAllProductsSelected();
        cb_allCheck_main.setChecked(allProductsSelected);
//计算总金额
        Double totalPrice = shoppAdapter.calculateTotalPrice();
        btn_allPrice_main.setText("总价：￥"+totalPrice);
        //计算总数量
        int totalNumber = shoppAdapter.calculateTotalNumber();
        btn_allNumber_main.setText("去结算("+totalNumber+")");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_allNumber_gw:

                break;
            case R.id.cb_allCheck_gw:
                boolean allProductsSelected = shoppAdapter.isAllProductsSelected();
                shoppAdapter.changeAllProductsSelected(!allProductsSelected);
                shoppAdapter.notifyDataSetChanged();
                //刷新底部的方法
                refreshAllSelectedAndTotalPriceAndTotalNumber();
                break;

        }
    }

}
