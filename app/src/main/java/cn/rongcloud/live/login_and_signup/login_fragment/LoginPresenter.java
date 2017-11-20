package cn.rongcloud.live.login_and_signup.login_fragment;

import android.content.Context;

import cn.rongcloud.live.data.DataRepository;
import cn.rongcloud.live.data.SharedPreferenceUtil.SharedPreferencesUtil;
import cn.rongcloud.live.data.beans.SysUser;
import cn.rongcloud.live.util.base.BaseResponse;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by quchwe on 2017/4/5 0005.
 */

public class LoginPresenter implements LoginContract.Presenter {


    private final DataRepository mDataRepository;
    private final Context mContext;
    private final LoginContract.View mView;


    public LoginPresenter(DataRepository mDataRepository, Context mContext, LoginContract.View mView) {
        this.mDataRepository = mDataRepository;
        this.mContext = mContext;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }

    @Override
    public void onLogin(final String phoneNumber, final String password) {
        SysUser user = new SysUser();
        user.setPhoneNumber(phoneNumber);
        user.setPassword(password);
        mDataRepository.login(user)
                .subscribe(new Observer<BaseResponse<SysUser>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResponse<SysUser> sysUserBaseResponse) {
                        if (sysUserBaseResponse.getErrCode().equals(BaseResponse.SUCCESS_CODE)){
                            SharedPreferencesUtil.saveData(mContext,"userId",sysUserBaseResponse.getData().getId());
                            SharedPreferencesUtil.saveData(mContext,"phoneNumber",sysUserBaseResponse.getData().getPhoneNumber());
                            SharedPreferencesUtil.saveData(mContext,"token",sysUserBaseResponse.getData().getToken());
                            mView.success();
                        }else {
                            mView.failed();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.failed();
                    }

                    @Override
                    public void onComplete() {

                    }

                });

    }
}
