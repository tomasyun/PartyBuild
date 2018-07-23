package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.persistance.CreditInfoBean;
import www.dico.cn.partybuild.presenter.CreditInfoPresenter;
import www.dico.cn.partybuild.modleview.CreditInfoView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;

@CreatePresenter(CreditInfoPresenter.class)
public class CreditInfoActivity extends AbstractMvpActivity<CreditInfoView, CreditInfoPresenter> implements CreditInfoView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditinfo);
        ViewFind.bind(this);
    }

    @Override
    public void resultSuccess(CreditInfoBean result) {

    }

    @Override
    public void resultFailure(String result) {

    }
}
