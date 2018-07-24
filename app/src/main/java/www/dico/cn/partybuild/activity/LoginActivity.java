package www.dico.cn.partybuild.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import www.dico.cn.partybuild.MainActivity;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.LoginView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.persistance.LoginBean;
import www.dico.cn.partybuild.presenter.LoginPresenter;

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
        switch (view.getId()) {
            case R.id.tv_login_ok:
                if (et_name_login.getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else if (et_password_login.getText().toString().trim().equals("")) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    getMvpPresenter().clickRequest(et_name_login.getText().toString().trim(), et_password_login.getText().toString().trim());
                }
                break;
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
