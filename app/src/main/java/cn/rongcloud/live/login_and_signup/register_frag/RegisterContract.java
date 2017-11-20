package cn.rongcloud.live.login_and_signup.register_frag;


import cn.rongcloud.live.data.beans.SysUser;
import cn.rongcloud.live.util.base.IBasePresenter;
import cn.rongcloud.live.util.base.IBaseView;

/**
 * Created by quchwe on 2017/4/5 0005.
 */

public interface RegisterContract {
    interface Presenter extends IBasePresenter {
        void onRegister(SysUser user);
    }

    interface View extends IBaseView<Presenter> {
        void success();
        void failed(String errMsg);
    }
}
