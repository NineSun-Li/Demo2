package com.example.demo2.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.demo2.MainActivity;
import com.example.demo2.R;
import com.example.demo2.base.BaseActivity;
import com.example.demo2.interfaces.IBasePresenter;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {
    @BindView(R.id.vp_splash)
    ViewPager vpSplash;
    private ArrayList<View> views;
    Disposable disposable;
    private TextView tv_timer;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }

    @Override
    protected IBasePresenter createPrenter() {
        return null;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        views = new ArrayList<View>();
        View p1 = LayoutInflater.from(this).inflate(R.layout.page1, null);
        View p2 = LayoutInflater.from(this).inflate(R.layout.page2, null);
        View p3 = LayoutInflater.from(this).inflate(R.layout.page3, null);
        views.add(p1);
        views.add(p2);
        views.add(p3);

        SplashVP splashVP = new SplashVP(this,views);
        vpSplash.setAdapter(splashVP);

        tv_timer = p3.findViewById(R.id.tv_timer);

        //点按钮跳转
        TextView jump = p3.findViewById(R.id.jump);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                cancelCallback();
            }
        });

        //页码的点击监听
        vpSplash.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2){//在最后一页执行倒计时
                    //TODO            Interval操作符(有范围)：创建一个按照固定时间发射整数序列的Observable
                    disposable = Observable.intervalRange(0, 4, 0, 1, TimeUnit.SECONDS) //起始值，发送总数量，初始延迟，固定延迟
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            //两种写法    1. lambda表达式写法：
//                            .subscribe(time -> tv_timer.setText((10 - time) + "s"),
//                                    Throwable::printStackTrace,
//                                    () -> {
//                                        startActivity(new Intent(SplashActivity.this,MainActivity.class));
//                                    }
//                            );

                    .subscribe(new Consumer<Long>() {
                        @Override
                        public void accept(Long aLong) throws Exception {
                            long time = 3 - aLong;
                            tv_timer.setText(time+"s");
                            if(time == 0){
                                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                            }
                        }
                    });
                }else{
                    cancelCallback();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    //取消订阅的方法
    private void cancelCallback() {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }
}
