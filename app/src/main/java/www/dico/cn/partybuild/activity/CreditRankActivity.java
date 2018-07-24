package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.CreditRankView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.persistance.CreditRankBean;
import www.dico.cn.partybuild.presenter.CreditRankPresenter;

//积分排名
@CreatePresenter(CreditRankPresenter.class)
public class CreditRankActivity extends AbstractMvpActivity<CreditRankView, CreditRankPresenter> implements CreditRankView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creditrank);
        ViewFind.bind(this);
    }

    public void goback(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(CreditRankBean result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
