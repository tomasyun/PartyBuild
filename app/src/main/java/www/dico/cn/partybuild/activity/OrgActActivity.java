package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.OrgActView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.OrgActPresenter;

@CreatePresenter(OrgActPresenter.class)
public class OrgActActivity extends AbstractMvpActivity<OrgActView, OrgActPresenter> implements OrgActView{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgact);
    }

    public void goback(View view) {
        this.finish();
    }
}
