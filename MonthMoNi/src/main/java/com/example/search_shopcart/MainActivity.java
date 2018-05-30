package com.example.search_shopcart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.search_shopcart.adapter.HistoryAdapter;
import com.example.search_shopcart.adapter.SearchActivityAdapter;
import com.example.search_shopcart.app.MyApplication;
import com.example.search_shopcart.base.BaseMvpActivity;
import com.example.search_shopcart.bean.SearchBean;
import com.example.search_shopcart.bean.SearchDaoBean;
import com.example.search_shopcart.dao.SearchDaoBeanDao;
import com.example.search_shopcart.presenter.SearchPresenter;
import com.example.search_shopcart.view.SearchViewListener;
import com.nex3z.flowlayout.FlowLayout;
import org.greenrobot.greendao.query.Query;
import java.util.ArrayList;
import java.util.List;
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
    @BindView(R.id.history_recyclerView)
    RecyclerView historyRecyclerView;
    private SearchActivityAdapter adapter;
    private HistoryAdapter historyAdapter;
    List<String>  list = new ArrayList<>(); //历史搜索输入数据集合
    private Query<SearchDaoBean> queryDao;  //历史搜索查询数据集合
    private SearchDaoBeanDao dao;

    @Override
    public SearchPresenter initPresenter() {
        return new SearchPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //设置搜索结果布局视图
        initSearchResultData();

        //设置搜索记录布局视图
        initHistoryResultData();

        //获取数据库实例,把历史记录显示在页面上
        dao = MyApplication.session.getSearchDaoBeanDao();
        queryDao = dao.queryBuilder().orderAsc(SearchDaoBeanDao.Properties.Id).build();
        List<SearchDaoBean> daoBeanList = queryList();
        for (int i = 0; i < daoBeanList.size(); i++){
            list.add(daoBeanList.get(i).getSelectGoods());
        }

    }

    private void initHistoryResultData() {
        //设置搜索结果适配器以及布局管理器
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.VERTICAL,false));
        historyAdapter = new HistoryAdapter(MainActivity.this, list);
        historyRecyclerView.setAdapter(historyAdapter);
    }

    private void initSearchResultData() {
        //设置搜索结果适配器以及布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        sousuoRecyview.setLayoutManager(manager);
        adapter = new SearchActivityAdapter(MainActivity.this);
    }

    @OnClick({R.id.search_btn, R.id.flow2,R.id.clearbtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            //点击搜索按钮,请求网络数据RecyclerView显示，并存入数据库
            case R.id.search_btn:
                p.getData(editInput.getText().toString());

                //保存搜索历史到数据库
                String trim = editInput.getText().toString().trim();
                SearchDaoBean daoBean = new SearchDaoBean(null, "1775", "TheScar", trim);
                dao.insert(daoBean);
                historyAdapter.notifyDataSetChanged();

                break;

            //点击任意一条搜索记录，跳转到购物车界面
            case R.id.flow2:
                Toast.makeText(this, "跳转购物车", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, ShopActivity.class));

            //清空历史搜索集合,，清空数据库，刷新数据
            case R.id.clearbtn:
                list.clear();
                deleteAllData();
                historyAdapter.notifyDataSetChanged();
                break;

            default:
                break;
        }
    }

    @Override
    public void success(SearchBean searchBean) {
        //添加数据
        if (adapter != null) {
            adapter.addData(searchBean.getData());
            sousuoRecyview.setAdapter(adapter);
        }
    }

    //查询全部数据的方法
    private List<SearchDaoBean> queryList() {
        List<SearchDaoBean> daoBeans = queryDao.list();
        return daoBeans;
    }

    //删除所有数据，即清空历史记录
    public void deleteAllData(){
        dao.deleteAll();
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

