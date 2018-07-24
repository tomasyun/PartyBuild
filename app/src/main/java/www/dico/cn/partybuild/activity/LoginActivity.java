package www.dico.cn.partybuild.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.autonavi.rtbt.IFrameForRTBT;

import www.dico.cn.partybuild.MainActivity;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.LoginView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.persistance.LoginBean;
import www.dico.cn.partybuild.presenter.LoginPresenter;
import www.yuntdev.com.imitationiosdialoglibrary.AlertDialog;

//登录
@CreatePresenter(LoginPresenter.class)
public class LoginActivity extends AbstractMvpActivity<LoginView, LoginPresenter> implements LoginView {
    @FieldView(R.id.et_name_login)
    private EditText et_name_login;
    @FieldView(R.id.et_password_login)
    private EditText et_password_login;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ViewFind.bind(this);
//        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/fangzheng.ttf");
//        tv_login_ok.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
    }

    public void login(View view) {
//        new AlertDialog(this).builder()
//                .setCancelable(true)
//                .setTitle("退出登录")
//                .setMsg("退出QQ可能会使你现有记录归零，确定退出?")
//                .setPositiveButton("确定退出", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                }).setNegativeButton("取消", new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        }).show();

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
    public void resultSuccess(LoginBean result) {
        goTo(MainActivity.class, null);
        finish();
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
