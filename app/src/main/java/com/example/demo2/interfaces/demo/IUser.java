package com.example.demo2.interfaces.demo;



import com.example.demo2.bean.UserInfoBean;
import com.example.demo2.interfaces.Callback;
import com.example.demo2.interfaces.IBaseModel;
import com.example.demo2.interfaces.IBasePresenter;
import com.example.demo2.interfaces.IBaseView;

import java.util.Map;

public interface IUser {
    interface View extends IBaseView {
        void updateUserInfoReturn(UserInfoBean result);
    }

    interface Presenter extends IBasePresenter<View> {
        void updateUserInfo(Map<String, String> map);
    }


    interface Model extends IBaseModel {
        void updateUserInfo(Map<String, String> map, Callback callback);
    }
}
