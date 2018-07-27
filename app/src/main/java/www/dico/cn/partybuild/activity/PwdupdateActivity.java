package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.PwdupdateView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.bean.PwdupdateBean;
import www.dico.cn.partybuild.presenter.PwdupdatePresenter;

//密码更新
@CreatePresenter(PwdupdatePresenter.class)
public class PwdupdateActivity extends AbstractMvpActivity<PwdupdateView, PwdupdatePresenter> implements PwdupdateView {
    @FieldView(R.id.et_pwd_old)
    EditText et_pwd_old;
    @FieldView(R.id.et_pwd_new)
    EditText et_pwd_new;
    @FieldView(R.id.et_pwd_new_confirm)
    EditText et_pwd_new_confirm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwdupdate);
        ViewFind.bind(this);
    }

    public void goback(View view) {
        this.finish();
    }

    //更新操作
    public void updatePassword(View view) {
        String old_password = et_pwd_old.getText().toString().trim();
        String new_password = et_pwd_new.getText().toString().trim();
        String confirm_password = et_pwd_new_confirm.getText().toString().trim();
        if (TextUtils.isEmpty(old_password)) {
            showToast("原密码不能为空");
        } else if (TextUtils.isEmpty(new_password)) {
            showToast("新密码不能为空");
        } else if (TextUtils.isEmpty(confirm_password)) {
            showToast("确认密码不能为空");
        } else if (!new_password.equals(confirm_password)) {
            showToast("抱歉!两次密码不一致");
        } else {
            getMvpPresenter().pwdupdateRequest("", old_password, new_password);
        }
    }

    @Override
    public void resultSuccess(PwdupdateBean result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
