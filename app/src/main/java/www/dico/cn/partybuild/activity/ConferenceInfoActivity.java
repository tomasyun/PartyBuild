package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.ConferenceInfoView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.ConferenceInfoPresenter;

@CreatePresenter(ConferenceInfoPresenter.class)
public class ConferenceInfoActivity extends AbstractMvpActivity<ConferenceInfoView, ConferenceInfoPresenter> implements ConferenceInfoView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conferenceinfo);
    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public void submitCommentSuccess(String result) {

    }

    @Override
    public void submitCommentFailure(String result) {
        showToast(result);
    }
}
