package com.example.demo2.presenter;


import com.example.demo2.base.BasePresenter;
import com.example.demo2.bean.UserInfoBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.demo.IUser;
import com.example.demo2.model.UserModel;

import java.util.Map;

public class UserPresenter extends BasePresenter<IUser.View> implements IUser.Presenter {

    IUser.Model model;

    public UserPresenter(){
        model = new UserModel();
    }

    @Override
    public void updateUserInfo(Map<String, String> map) {
        model.updateUserInfo(map,new Callback<UserInfoBean>() {
            @Override
            public void success(UserInfoBean data) {
                if(mView != null){
                    mView.updateUserInfoReturn(data);
                }
            }

            @Override
            public void fail(String err) {

            }
        });
    }

}
