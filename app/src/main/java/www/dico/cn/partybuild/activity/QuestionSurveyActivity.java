package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.QuestionSurveyView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.QuestionSurveyPresenter;

@CreatePresenter(QuestionSurveyPresenter.class)
public class QuestionSurveyActivity extends AbstractMvpActivity<QuestionSurveyView, QuestionSurveyPresenter> implements QuestionSurveyView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionsurvey);

    }

    public void goback(View view) {
        this.finish();
    }
}
