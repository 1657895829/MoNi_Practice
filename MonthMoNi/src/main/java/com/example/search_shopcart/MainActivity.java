package com.example.search_shopcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.search_shopcart.adapter.SearchActivityAdapter;
import com.example.search_shopcart.base.BaseMvpActivity;
import com.example.search_shopcart.bean.SearchBean;
import com.example.search_shopcart.presenter.SearchPresenter;
import com.example.search_shopcart.view.SearchViewListener;
import com.nex3z.flowlayout.FlowLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//搜索框页面
public class MainActivity extends BaseMvpActivity<SearchViewListener, SearchPresenter> implements SearchViewListener {
    @BindView(R.id.sousuo_recyview)
    RecyclerView sousuoRecyview;
    @BindView(R.id.edit_input)
    EditText editInput;
    @BindView(R.id.search_btn)
    Button searchBtn;
    @BindView(R.id.flow2)
    FlowLayout flow2;
    private SearchActivityAdapter adapter;

    @Override
    public SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //设置适配器以及布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        sousuoRecyview.setLayoutManager(manager);
        adapter = new SearchActivityAdapter(MainActivity.this);
        sousuoRecyview.setAdapter(adapter);
    }

    @OnClick({R.id.search_btn,R.id.flow2})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            //点击搜索按钮
            case R.id.search_btn:
                p.getData(editInput.getText().toString());
                break;

            //点击任意一条搜索记录，跳转到购物车界面
            case R.id.flow2:
                Toast.makeText(this, "跳转购物车", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,ShopActivity.class));

            default:
                break;
        }
    }

    @Override
    public void success(SearchBean searchBean) {
        //添加数据
        if (adapter != null) {
            adapter.addData(searchBean.getData());
        }
    }

    @Override
    public void failure(Exception e) {
        System.out.println("错误：" + e);
    }

    @Override
    public void empty() {
        Toast.makeText(this, "输入内容不能为空", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void falseEdit() {
        Toast.makeText(this, "请输入手机或笔记本", Toast.LENGTH_SHORT).show();
    }

}

