package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.StudyResultView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.StudyResultPresenter;

@CreatePresenter(StudyResultPresenter.class)
public class StudyResultActivity extends AbstractMvpActivity<StudyResultView, StudyResultPresenter> implements StudyResultView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studyresult);
        ViewFind.bind(this);
    }

    public void goBackStudyResult(View view) {
        this.finish();
    }
}
