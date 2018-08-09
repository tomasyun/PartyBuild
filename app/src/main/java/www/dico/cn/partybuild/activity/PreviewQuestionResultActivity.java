package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.PreviewQuestionResultView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.PreviewQuestionResultPresenter;

@CreatePresenter(PreviewQuestionResultPresenter.class)
public class PreviewQuestionResultActivity extends AbstractMvpActivity<PreviewQuestionResultView, PreviewQuestionResultPresenter> implements PreviewQuestionResultView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previewquestionresult);
    }

    public void goBackPreviewQuestionResult(View view) {
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
