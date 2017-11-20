package cn.rongcloud.live.login_and_signup.register_frag;

import android.content.Context;
import android.util.Log;

import org.reactivestreams.Subscriber;

import cn.rongcloud.live.data.DataRepository;
import cn.rongcloud.live.data.SharedPreferenceUtil.SharedPreferencesUtil;
import cn.rongcloud.live.data.beans.BaseBean;
import cn.rongcloud.live.data.beans.SysUser;
import cn.rongcloud.live.util.base.BaseResponse;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static cn.rongcloud.live.login_and_signup.register_frag.RegisterFragment.TAG;


/**
 * Created by YSten on 2017/4/5.
 */

public class RegisterPresenter implements RegisterContract.Presenter{

    private final DataRepository mDataRepository;
    private final Context mContext;
    private final RegisterContract.View mView;

    public RegisterPresenter(DataRepository mDataRepository, Context mContext, RegisterContract.View mView) {
        this.mDataRepository = mDataRepository;
        this.mContext = mContext;
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void onRegister(SysUser user) {
            mDataRepository.register(user).subscribe(new Observer<BaseResponse<SysUser>>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d(TAG,"subscribe");
                }

                @Override
                public void onNext(BaseResponse<SysUser> sysUserBaseResponse) {
                    if (sysUserBaseResponse.getErrCode().equals(BaseResponse.SUCCESS_CODE)){
                        SharedPreferencesUtil.saveData(mContext,"userId",sysUserBaseResponse.getData().getId());
                        SharedPreferencesUtil.saveData(mContext,"phoneNumber",sysUserBaseResponse.getData().getPhoneNumber());
                        SharedPreferencesUtil.saveData(mContext,"token",sysUserBaseResponse.getData().getToken());
                        mView.success();
                    }else {
                        mView.failed(sysUserBaseResponse.getErrMsg());
                    }
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG, "onError: ", e.getCause());
                    mView.failed(e.getMessage());
                }

                @Override
                public void onComplete() {
                    mView.success();
                }
            });
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
