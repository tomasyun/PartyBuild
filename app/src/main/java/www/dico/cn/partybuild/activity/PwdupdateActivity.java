package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.PwdupdateView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.persistance.PwdupdateBean;
import www.dico.cn.partybuild.presenter.PwdupdatePresenter;
//密码更新
@CreatePresenter(PwdupdatePresenter.class)
public class PwdupdateActivity extends AbstractMvpActivity<PwdupdateView, PwdupdatePresenter> implements PwdupdateView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pwdupdate);
        ViewFind.bind(this);
    }

    @Override
    public void resultSuccess(PwdupdateBean result) {

    }

    @Override
    public void resultFailure(String result) {

    }
}
