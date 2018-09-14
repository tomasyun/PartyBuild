package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.IntegrityBuildView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.IntegrityBuildPresenter;

@CreatePresenter(IntegrityBuildPresenter.class)
public class IntegrityBuildActivity extends AbstractMvpActivity<IntegrityBuildView, IntegrityBuildPresenter> implements IntegrityBuildView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integritybuild);
    }
    public void goBackIntegrityBuild(View view){
        this.finish();
    }
}
