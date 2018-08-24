package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.MainActivity;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.LoginBean;
import www.dico.cn.partybuild.modleview.LoginView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.LoginPresenter;

//登录
@CreatePresenter(LoginPresenter.class)
public class LoginActivity extends AbstractMvpActivity<LoginView, LoginPresenter> implements LoginView {
    @BindView(R.id.et_name_login)
    EditText et_name_login;
    @BindView(R.id.et_password_login)
    EditText et_password_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fangzheng.ttf");
//        tv_login_ok.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
    }

    public void login(View view) {
        String login_name = et_name_login.getText().toString().trim();
        String password = et_password_login.getText().toString().trim();
        if (TextUtils.isEmpty(login_name)) {
            showToast("用户名不能为空");
        } else if (TextUtils.isEmpty(password)) {
            showToast("密码不能为空");
        } else {
            getMvpPresenter().clickRequest(et_name_login.getText().toString().trim(), et_password_login.getText().toString().trim());
        }
    }

    @Override
    public void resultSuccess(String result) {
        LoginBean bean = new Gson().fromJson(result, LoginBean.class);
        if (bean.getCode().equals("0000")) {
            if (null != bean.getData()) {
                String partyPosition = bean.getData().getPartyBranchPost();//党内职务
                String position = bean.getData().getPosition();//行政职务
                String token = bean.getData().getToken();
                String userId = bean.getData().getUserId();//用户id
                String avatar = bean.getData().getAvatar();//头像
                boolean isManager = bean.getData().isManager();//是否为管理员
                partyPosition = (null == partyPosition) ? "" : partyPosition;
                AppConfig.getSpUtils().put("partyBranchPost", partyPosition);
                position = (null == position) ? "" : position;
                AppConfig.getSpUtils().put("position", position);
                AppConfig.getSpUtils().put("token", "Bearer " + token);
                AppConfig.getSpUtils().put("userId", userId);
                AppConfig.getSpUtils().put("avatar", avatar);
                AppConfig.getSpUtils().put("isManager", isManager);
                AppConfig.getSpUtils().put("isLoginOk",1);
                goTo(MainActivity.class, null);
                finish();
            }
        } else {
            showToast(bean.getMsg());
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        showToast("网络出现异常");
    }
}
