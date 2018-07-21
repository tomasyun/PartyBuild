package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.OnlineExamView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.OnlineExamPresenter;

@CreatePresenter(OnlineExamPresenter.class)
public class OnlineExamActivity extends AbstractMvpActivity<OnlineExamView, OnlineExamPresenter> implements OnlineExamView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlineexam);
        ViewFind.bind(this);
    }
}