package com.bwie.xiaqin.yuekao1121lx;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.bwie.xiaqin.yuekao1121lx.adapter.MyFragmentAdapter;
import com.bwie.xiaqin.yuekao1121lx.fragment.CarFragmenter;
import com.bwie.xiaqin.yuekao1121lx.fragment.FenFragmenter;
import com.bwie.xiaqin.yuekao1121lx.fragment.ShouFragmenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    private RadioGroup radio_group;
    private FragmentManager manager;
    private ViewPager frag_page;
    private ShouFragmenter shouFragmenter;
    private FenFragmenter fenFragmenter;
    private CarFragmenter carFragmenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        radio_group = findViewById(R.id.radio_group);
        frag_page = findViewById(R.id.frag_page);
        shouFragmenter = new ShouFragmenter();
        fenFragmenter = new FenFragmenter();
        carFragmenter = new CarFragmenter();
        //创建ArrayList集合
        List<Fragment> list = new ArrayList<>();
        list.add(new ShouFragmenter());
        list.add(new FenFragmenter());
        list.add(new CarFragmenter());
        MyFragmentAdapter adapter = new MyFragmentAdapter(getSupportFragmentManager(), list);
        frag_page.setAdapter(adapter);
        radio_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb1:
                        frag_page.setCurrentItem(0);
                        break;
                    case R.id.rb2:
                        frag_page.setCurrentItem(1);
                        break;
                    case R.id.rb3:
                        frag_page.setCurrentItem(2);
                        break;

                }
            }
        });
        frag_page.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                radio_group.check(radio_group.getChildAt(position).getId());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
