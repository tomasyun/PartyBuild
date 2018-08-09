package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.ExamRuleForm;
import www.dico.cn.partybuild.modleview.ExamRuleView;
import www.dico.cn.partybuild.mvp.FieldView;
import www.dico.cn.partybuild.mvp.ViewFind;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.bean.ExamRuleBean;
import www.dico.cn.partybuild.presenter.ExamRulePresenter;

//考试规则
@CreatePresenter(ExamRulePresenter.class)
public class ExamRuleActivity extends AbstractMvpActivity<ExamRuleView, ExamRulePresenter> implements ExamRuleView {
    private ExamRuleForm form;
    @BindView(R.id.tv_total_score)
    TextView tv_total_score;//总分数
    @BindView(R.id.tv_standard_score)
    TextView tv_standard_score;//及格分
    @BindView(R.id.tv_question_total_num)
    TextView tv_question_total_num;//考试题数
    @BindView(R.id.tv_exam_during)
    TextView tv_exam_during;//考试时长
    @BindView(R.id.tv_exam_start_date)
    TextView tv_exam_start_date;//考试开始时间
    @BindView(R.id.tv_exam_end_date)
    TextView tv_exam_end_date;//考试结束时间

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examrule);
        ButterKnife.bind(this);
        form = getParam();
        getMvpPresenter().examRuleRequest(form.examId);
    }

    //返回
    public void goBackExamRule(View view) {
        this.finish();
    }

    //开始答题
    public void startExam(View view) {
        goTo(OnlineExamActivity.class, null);
    }

    @Override
    public void resultSuccess(String result) {
        ExamRuleBean bean = new Gson().fromJson(result, ExamRuleBean.class);
        if (bean.code.equals("0000")) {
            if (null != bean.getData()) {
                tv_total_score.setText(bean.getData().getTotalScore());
                tv_standard_score.setText(bean.getData().getLimitScore());
                tv_question_total_num.setText(bean.getData().getQuestionNum());
                tv_exam_during.setText(bean.getData().getExamDuration());
                tv_exam_start_date.setText(bean.getData().getExamHours());
            }
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }
}
