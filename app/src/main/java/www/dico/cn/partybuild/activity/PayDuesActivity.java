package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.PayDuesView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.PayDuesPresenter;
@CreatePresenter(PayDuesPresenter.class)
public class PayDuesActivity extends AbstractMvpActivity<PayDuesView,PayDuesPresenter> implements PayDuesView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paydues);
    }
}
