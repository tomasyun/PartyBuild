package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.CreditRankView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.persistance.CreditRankBean;
import www.dico.cn.partybuild.presenter.CreditRankPresenter;

@CreatePresenter(CreditRankPresenter.class)
public class CreditRankActivity extends AbstractMvpActivity<CreditRankView, CreditRankPresenter> implements CreditRankView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditrank);
        ViewFind.bind(this);
    }

    @Override
    public void resultSuccess(CreditRankBean result) {

    }

    @Override
    public void resultFailure(String result) {

    }
}
