package com.example.search_shopcart;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.example.search_shopcart.adapter.SelectCartAdapter;
import com.example.search_shopcart.base.BaseMvpActivity;
import com.example.search_shopcart.bean.SelectCartBean;
import com.example.search_shopcart.presenter.SelectCartPresenter;
import com.example.search_shopcart.view.SelectCartViewListener;
import butterknife.BindView;
import butterknife.ButterKnife;

//购物车页面
public class ShopActivity extends BaseMvpActivity<SelectCartViewListener, SelectCartPresenter> implements SelectCartViewListener {
    @BindView(R.id.recycler_View)
    RecyclerView recyclerView;
    @BindView(R.id.quanxuan)
    CheckBox quanxuan;
    @BindView(R.id.total_price)
    TextView total_price;
    @BindView(R.id.total_num)
    TextView total_num;
    @BindView(R.id.pay)
    TextView pay;
    private SelectCartAdapter selectCartAdapter;

    @Override
    public SelectCartPresenter initPresenter() {
        return new SelectCartPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);

        //调用presenter里面的请求数据的方法
        p.getData();

        quanxuan.setTag(1);//1为不选中

        LinearLayoutManager manager = new LinearLayoutManager(ShopActivity.this, LinearLayoutManager.VERTICAL, false);
        selectCartAdapter = new SelectCartAdapter(ShopActivity.this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(selectCartAdapter);
        selectCartAdapter.notifyDataSetChanged();

        //调用recyAdapter里面的接口,设置 全选按钮 总价 总数量
        selectCartAdapter.setUpdateListener(new SelectCartAdapter.UpdateListener() {
            @Override
            public void setTotal(String total, String num, boolean allCheck) {
                //设置ui的改变
                total_num.setText("共"+num+"件商品");//总数量
                Double aDouble = new Double(total);
                total_price.setText(""+aDouble.intValue());//总价
                if(allCheck){
                    quanxuan.setTag(2);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_selected);
                }else{
                    quanxuan.setTag(1);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_unselected);
                }

                quanxuan.setChecked(allCheck);
            }
        });

        //这里只做ui更改, 点击全选按钮,,调到adapter里面操作
        quanxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用adapter里面的方法 ,,把当前quanxuan状态传递过去

                int tag = (int) quanxuan.getTag();
                if(tag==1){
                    quanxuan.setTag(2);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_selected);
                }else{
                    quanxuan.setTag(1);
                    quanxuan.setBackgroundResource(R.drawable.shopcart_unselected);
                }

                selectCartAdapter.quanXuan(quanxuan.isChecked());
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(ShopActivity.this,"共需支付："+total_price.getText().toString()+" 元",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void success(SelectCartBean selectCartBean) {

        //将数据传给适配器
        if (selectCartBean != null) {
            selectCartAdapter.add(selectCartBean);
            selectCartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void failure() {

    }

}
