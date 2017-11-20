package cn.rongcloud.live.login_and_signup.register_frag;

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
import cn.rongcloud.live.data.beans.SysUser;
import cn.rongcloud.live.util.NormalUtil.NormalUtil;
import cn.rongcloud.live.util.NormalUtil.ToastUtil;
import cn.rongcloud.live.util.base.BaseFragment;

/**
 * Created by YSten on 2017/4/5.
 */

public class RegisterFragment extends BaseFragment<RegisterContract.Presenter> implements RegisterContract.View {

    public static final String TAG = "RegisterFrag";


    public static RegisterFragment newInstance() {

        Bundle args = new Bundle();

        RegisterFragment fragment = new RegisterFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.textTitle)
    TextView title;
    @BindView(R.id.et_phone_number)
    EditText phoneNumber;
    @BindView(R.id.et_userName)
    EditText etUserName;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_password_again)
    EditText inputPasswordAgain;
    @BindView(R.id.et_drivingLicenseId)
    EditText etDrivingId;
    @BindView(R.id.btn_next_step)
    Button registerBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_phone_register, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;

    }


    private void initView(){
        title.setText("注册");
        registerBtn.setText("注册");

    }
    @Override
    public void success() {
        ToastUtil.showToast(getContext(),"注册成功");

    }

    @OnClick({R.id.back, R.id.btn_next_step})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                getActivity().getSupportFragmentManager().popBackStack();
                break;
            case R.id.btn_next_step:
                register();
                break;
        }
    }

    private void register() {
        String phoneNuber = phoneNumber.getText().toString();
        String password = etPassword.getText().toString();
        String drivingId = etDrivingId.getText().toString();
        String passwordAgain = inputPasswordAgain.getText().toString();
        String userName = etUserName.getText().toString();

        if (phoneNuber!=null&&!phoneNuber.equals("")&&!NormalUtil.isMobileNO(phoneNuber)){
            ToastUtil.showToast(getContext(),"请输入正确的手机号码");
            return;
        }

        if (password==null||password.equals("")||passwordAgain==null||passwordAgain.equals("")){
            ToastUtil.showToast(getContext(),"请输入密码");
            return;
        }
        if(!password.equals(passwordAgain)){
            ToastUtil.showToast(getContext(),"两次输入密码不相等");
            return;
        }

        SysUser user = new SysUser();
        user.setPassword(password);
        user.setPhoneNumber(phoneNuber);

        user.setUsername(userName);
        mPresenter.onRegister(user);

    }

    @Override
    public void failed(String errMsg) {

        ToastUtil.showToast(getContext(),"注册失败"+errMsg);
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
