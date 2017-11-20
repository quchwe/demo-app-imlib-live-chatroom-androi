package cn.rongcloud.live.login_and_signup.login_fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.rongcloud.live.R;
import cn.rongcloud.live.login_and_signup.LoginAndSignUpActivity;
import cn.rongcloud.live.login_and_signup.register_frag.RegisterFragment;
import cn.rongcloud.live.util.NormalUtil.ToastUtil;
import cn.rongcloud.live.util.base.BaseFragment;

/**
 * Created by quchwe on 2017/4/5 0005.
 */

public class LoginFragment extends BaseFragment<LoginContract.Presenter> implements LoginContract.View {


    public static final String TAG = "LoginFragement-";


    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.et_inputNumber)
    EditText phoneNumber;
    @BindView(R.id.et_inputPassword)
    EditText inputPassword;
    @BindView(R.id.btn_next_step)
    Button login;
    @BindView(R.id.textTitle)
    TextView title;

    @BindView(R.id.tv_register)
    TextView register;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_quickly_login, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        title.setText("注册登录");
        login.setText("立即登录");
    }

    @OnClick({R.id.btn_next_step, R.id.back, R.id.tv_register})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_next_step:
                login();
                break;
            case R.id.back:
                getActivity().finish();
                break;
            case R.id.tv_register:
                toRegister();
        }
    }

    private void toRegister() {
        LoginAndSignUpActivity activity = (LoginAndSignUpActivity) getActivity();
        RegisterFragment fragment = (RegisterFragment) activity.getSupportFragmentManager().findFragmentByTag(RegisterFragment.TAG);
        if (fragment == null) {
            fragment = activity.getmRegisterView();
        }

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame, fragment, RegisterFragment.TAG)
                .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right,
                        R.anim.slide_in_left, R.anim.slide_out_left)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    private void login() {
        String number = phoneNumber.getText().toString();
        String password = inputPassword.getText().toString();

        if (!number.equals("")
                && !password.equals("")) {
            mPresenter.onLogin(number, password);
        }
    }

    @Override
    public void success() {
        ToastUtil.showToast(getContext(), "登录成功");

//        Intent intent = new Intent(getContext(), MainFrameActivity.class);
//        startActivity(intent);
    }

    @Override
    public void failed() {
        ToastUtil.showToast(getContext(), "登录失败");
    }
}
