package com.example.demo2.ui.home;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.example.demo2.MainActivity;
import com.example.demo2.R;
import com.example.demo2.app.Myapp;
import com.example.demo2.base.BaseFragment;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.sql.Favorites;
import com.example.demo2.ui.TestActivity;
import com.example.demo2.ui.demo.FavoritesActivity;
import com.example.demo2.ui.demo.LoginActivity;
import com.example.demo2.ui.user.UserInfoDetailActivity;

import butterknife.BindView;

public class FiveFragment extends BaseFragment {

    @BindView(R.id.iv_five_yh)
    ImageView ivFiveYh;
    @BindView(R.id.tv_five_name)
    TextView tvFiveName;
    @BindView(R.id.iv_five_jt)
    ImageView ivFiveJt;
    @BindView(R.id.ll_five_dingdan)
    LinearLayout llFiveDingdan;
    @BindView(R.id.ll_five_youhuiquan)
    LinearLayout llFiveYouhuiquan;
    @BindView(R.id.ll_five_lipinka)
    LinearLayout llFiveLipinka;
    @BindView(R.id.ll_five_shoucang)
    LinearLayout llFiveShoucang;
    @BindView(R.id.ll_five_zuji)
    LinearLayout llFiveZuji;
    @BindView(R.id.ll_five_fuli)
    LinearLayout llFiveFuli;
    @BindView(R.id.ll_five_dizhi)
    LinearLayout llFiveDizhi;
    @BindView(R.id.ll_five_zhanghao)
    LinearLayout llFiveZhanghao;
    @BindView(R.id.ll_five_lianxi)
    LinearLayout llFiveLianxi;
    @BindView(R.id.ll_five_bangzhu)
    LinearLayout llFiveBangzhu;
    @BindView(R.id.ll_five_fankui)
    LinearLayout llFiveFankui;

    @Override
    protected int getLayout() {
        return R.layout.fragment_five;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {

        //登录
        tvFiveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getContext(), LoginActivity.class);
                startActivityForResult(intent,100);
            }
        });

        //头像
        ivFiveYh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UserInfoDetailActivity.class);
                startActivity(intent);
            }
        });

        //收藏
        llFiveShoucang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),FavoritesActivity.class));
            }


        });

        //地址
        llFiveDizhi.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    protected void initData() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==200){
            String name = data.getStringExtra("name");
            tvFiveName.setText(name);
        }
    }
}