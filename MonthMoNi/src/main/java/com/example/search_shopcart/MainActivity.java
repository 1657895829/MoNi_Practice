package com.example.search_shopcart;

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
    }

    @OnClick({  R.id.search_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            //点击搜索按钮
            case R.id.search_btn:
                Toast.makeText(this, "按钮搜索", Toast.LENGTH_SHORT).show();
                p.getData(editInput.getText().toString());
                break;

            default:
                break;
        }
    }

    @Override
    public void success(SearchBean searchBean) {
        //添加数据
        if (searchBean != null) {
            adapter.addData(searchBean.getData());
            sousuoRecyview.setAdapter(adapter);
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

