package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.FeedbackView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.persistance.FeedBackBean;
import www.dico.cn.partybuild.presenter.FeedbackPresenter;
//意见反馈
@CreatePresenter(FeedbackPresenter.class)
public class FeedbackActivity extends AbstractMvpActivity<FeedbackView, FeedbackPresenter> implements FeedbackView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ViewFind.bind(this);
    }

    @Override
    public void resultSuccess(FeedBackBean result) {

    }

    @Override
    public void resultFailure(String result) {

    }
}
