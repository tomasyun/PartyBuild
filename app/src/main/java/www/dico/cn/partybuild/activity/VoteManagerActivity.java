package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.VoteManagerView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.VoteManagerPresenter;

@CreatePresenter(VoteManagerPresenter.class)
public class VoteManagerActivity extends AbstractMvpActivity<VoteManagerView, VoteManagerPresenter> implements VoteManagerView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votemanager);
    }

    public void goback(View view) {
        this.finish();
    }
}
