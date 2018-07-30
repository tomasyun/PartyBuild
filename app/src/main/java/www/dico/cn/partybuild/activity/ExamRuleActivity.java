package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.modleview.ExamRuleView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.bean.ExamRuleBean;
import www.dico.cn.partybuild.presenter.ExamRulePresenter;

//考试规则
@CreatePresenter(ExamRulePresenter.class)
public class ExamRuleActivity extends AbstractMvpActivity<ExamRuleView, ExamRulePresenter> implements ExamRuleView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examrule);
        ViewFind.bind(this);
    }

    //返回
    public void goback(View view) {
       this.finish();
    }

    //开始答题
    public void startExam(View view) {
        goTo(OnlineExamActivity.class, null);
    }

    @Override
    public void resultSuccess(ExamRuleBean result) {

    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
