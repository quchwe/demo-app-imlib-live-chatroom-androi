package cn.rongcloud.live.login_and_signup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;


import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.rongcloud.live.R;
import cn.rongcloud.live.data.DataRepository;
import cn.rongcloud.live.login_and_signup.login_fragment.LoginContract;
import cn.rongcloud.live.login_and_signup.login_fragment.LoginFragment;
import cn.rongcloud.live.login_and_signup.login_fragment.LoginPresenter;
import cn.rongcloud.live.login_and_signup.register_frag.RegisterContract;
import cn.rongcloud.live.login_and_signup.register_frag.RegisterFragment;
import cn.rongcloud.live.login_and_signup.register_frag.RegisterPresenter;
import cn.rongcloud.live.util.base.BaseActivity;
import cn.rongcloud.live.util.base.BaseFragment;

/**
 * Created by YSten on 2017/3/3.
 */

public class LoginAndSignUpActivity extends BaseActivity {


    public static final String TAG = "LoginAndSign-";
    private final DataRepository mDataResposity = new DataRepository(this);
    private LoginContract.View mLoginView;
    private LoginContract.Presenter mLoginPresenter;
    private RegisterContract.View mRegisterView;
    private RegisterContract.Presenter mRegisterPresenter;

    @BindView(R.id.frame)
    FrameLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        initAllFragment();
        initAllPresenter();

        BaseFragment frag = (BaseFragment) mFragmentManager.findFragmentById(R.id.frame);

        if (frag == null) {
            frag = getmLoginView();
            mFragmentManager.beginTransaction().add(R.id.frame, frag, LoginFragment.TAG).commit();
        }

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);

    }

    private void initAllPresenter() {
        mLoginPresenter = new LoginPresenter(mDataResposity,this,getmLoginView());
        mRegisterPresenter = new RegisterPresenter(mDataResposity,this,getmRegisterView());
    }

    private void initAllFragment() {
        Fragment frag = null;
        frag = mFragmentManager.findFragmentByTag(LoginFragment.TAG);
        mLoginView = (frag == null) ? LoginFragment.newInstance() : (LoginFragment) frag;
        frag = mFragmentManager.findFragmentByTag(RegisterFragment.TAG);
        mRegisterView = (frag ==null)? RegisterFragment.newInstance():(RegisterFragment)frag;

    }

    public LoginFragment getmLoginView() {
        return (LoginFragment) mLoginView;
    }

    public RegisterFragment getmRegisterView(){
        return (RegisterFragment)mRegisterView;
    }

}
