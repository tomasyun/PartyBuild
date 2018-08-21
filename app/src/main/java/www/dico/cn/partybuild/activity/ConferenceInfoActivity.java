package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import www.dico.cn.partybuild.modleview.ConferenceInfoView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.ConferencePresenter;
@CreatePresenter(ConferencePresenter.class)
public class ConferenceInfoActivity extends AbstractMvpActivity<ConferenceInfoView,ConferencePresenter> implements ConferenceInfoView{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void resultSuccess(String result) {

    }

    @Override
    public void resultFailure(String result) {

    }

    @Override
    public void submitCommentSuccess(String result) {

    }

    @Override
    public void submitCommentFailure(String result) {

    }
}
