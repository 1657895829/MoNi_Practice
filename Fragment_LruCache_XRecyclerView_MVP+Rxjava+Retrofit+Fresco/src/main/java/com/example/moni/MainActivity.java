package com.example.moni;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.moni.fragment.ClearCache_Fragment;
import com.example.moni.fragment.Image_Fragment;
import com.example.moni.fragment.List_Fragment;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//Fragment页面分类显示
public class MainActivity extends FragmentActivity {
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.liebiao)
    RadioButton liebiao;
    @BindView(R.id.image)
    RadioButton image;
    @BindView(R.id.clearCache)
    RadioButton clearCache;
    @BindView(R.id.radio)
    RadioGroup radio;
    private List<Fragment> list_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        //动态添加Fragment
        list_fragment = new ArrayList<>();
        list_fragment.add(new List_Fragment());
        list_fragment.add(new Image_Fragment());
        list_fragment.add(new ClearCache_Fragment());

        //设置适用于fragment的适配器：FragmentPagerAdapter(Fragment的管理对象)
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public Fragment getItem(int position) {
                return list_fragment.get(position);
            }
        });

        //为viewPager设置页面改变时的监听事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //选中某一页的时候需要让RadioGroup选中的那一个button
                radio.check(radio.getChildAt(position).getId());
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @OnClick({R.id.liebiao, R.id.image, R.id.clearCache})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liebiao:
                viewPager.setCurrentItem(0,false);
                break;
            case R.id.image:
                viewPager.setCurrentItem(1,false);
                break;
            case R.id.clearCache:
                viewPager.setCurrentItem(2,false);
                break;
            default:
                break;
        }
    }
}
