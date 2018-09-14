package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.BranchParksView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.BranchParksPresenter;

@CreatePresenter(BranchParksPresenter.class)
public class BranchParksActivity extends AbstractMvpActivity<BranchParksView, BranchParksPresenter> implements BranchParksView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branchparks);
    }

    public void goBackBranchParks(View view) {
        this.finish();
    }
}
