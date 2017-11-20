package cn.rongcloud.live.login_and_signup.login_fragment;


import cn.rongcloud.live.util.base.IBasePresenter;
import cn.rongcloud.live.util.base.IBaseView;

/**
 * Created by quchwe on 2017/4/5 0005.
 */

public interface LoginContract {
    interface Presenter extends IBasePresenter {
        void onLogin(String phoneNumber, String password);
    }
    interface View extends IBaseView<Presenter> {
        void success();
        void failed();
    }
}
