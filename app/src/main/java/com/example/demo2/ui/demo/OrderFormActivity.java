package com.example.demo2.ui.demo;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.demo2.R;
import com.example.demo2.adapter.AddressAdapter;
import com.example.demo2.app.Myapp;
import com.example.demo2.bean.CarBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class OrderFormActivity extends AppCompatActivity {

    @BindView(R.id.tv_order_form_name)
    TextView tv_mName;
    @BindView(R.id.tv_order_form_default)
    TextView tv_Default;
    @BindView(R.id.tv_order_form_phone)
    TextView tv_Phone;
    @BindView(R.id.tv_order_form_address)
    TextView tv_Address;
    @BindView(R.id.mRl)
    RelativeLayout mRl;
    @BindView(R.id.tv_order_form_coupon)
    TextView tv_Coupon;
    @BindView(R.id.mRlv_order_form)
    RecyclerView mRlv;
    @BindView(R.id.mCl_order_form_one)
    ConstraintLayout mCl_one;
    @BindView(R.id.mCl_order_form_two)
    ConstraintLayout mCl_two;
    @BindView(R.id.mCl_order_form_three)
    ConstraintLayout mCl_three;
    @BindView(R.id.tv_order_form_money)
    TextView tv_Money;
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_form);
        bind = ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        ArrayList<CarBean.DataBean.CartListBean> list = (ArrayList<CarBean.DataBean.CartListBean>) Myapp.getMap().get("shoppinglist");

        TextView one_left = mCl_one.findViewById(R.id.tv_order_form_left);
        TextView one_right = mCl_one.findViewById(R.id.tv_order_form_right);

        TextView two_left = mCl_two.findViewById(R.id.tv_order_form_left);
        TextView two_right = mCl_two.findViewById(R.id.tv_order_form_right);

        TextView three_left = mCl_three.findViewById(R.id.tv_order_form_left);
        TextView three_right = mCl_three.findViewById(R.id.tv_order_form_right);

        one_left.setText("商品合计");
        two_left.setText("运费");
        three_left.setText("优惠券");

        for (int i = 0; i < list.size(); i++) {
            one_right.setText("￥" + list.get(i).getRetail_price());
            tv_Money.setText("" + list.get(i).getRetail_price());
        }

        mRlv.setLayoutManager(new LinearLayoutManager(this));
        AddressAdapter addressAdapter = new AddressAdapter(this, list);
        mRlv.setAdapter(addressAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}