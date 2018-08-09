package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.OrgActBriefBean;
import www.dico.cn.partybuild.modleview.OrgActBriefView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.OrgActBriefPresenter;

@CreatePresenter(OrgActBriefPresenter.class)
public class OrgActBriefActivity extends AbstractMvpActivity<OrgActBriefView, OrgActBriefPresenter> implements OrgActBriefView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orgactbrief);
    }

    public void goBackOrgActBrief(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
