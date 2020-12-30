package com.example.demo2.model;

import com.example.demo2.base.BaseModel;
import com.example.demo2.bean.UserInfoBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.IUser;
import com.example.demo2.net.CommonSubscriber;
import com.example.demo2.net.HttpManager;
import com.example.demo2.utils.RxUtils;

import java.util.Map;

public class UserModel extends BaseModel implements IUser.Model {
    @Override
    public void updateUserInfo(Map<String, String> map, Callback callback) {
        addDisposible(HttpManager.getInstance().getService().updateUserInfo(map).
                compose(RxUtils.rxScheduler())
                .subscribeWith(new CommonSubscriber<UserInfoBean>(callback) {
                    @Override
                    public void onNext(UserInfoBean userInfoBean) {
                        callback.success(userInfoBean);
                    }
                }));
    }
}
