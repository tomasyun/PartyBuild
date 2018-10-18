package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.bean.ExamResultForm;
import www.dico.cn.partybuild.bean.ExamRuleBean;
import www.dico.cn.partybuild.bean.ExamRulerForm;
import www.dico.cn.partybuild.modleview.ExamRuleView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.ExamRulePresenter;

//考试规则
@CreatePresenter(ExamRulePresenter.class)
public class ExamRuleActivity extends AbstractMvpActivity<ExamRuleView, ExamRulePresenter> implements ExamRuleView {
    @BindView(R.id.tv_exam_name)
    TextView tv_exam_name;
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
    @BindView(R.id.tv_start_exam)
    TextView tv_start_exam;
    private ExamRulerForm form;
    private ExamResultForm resultForm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examrule);
        ButterKnife.bind(this);
        tv_start_exam.setEnabled(false);
        tv_start_exam.setClickable(false);
        form = getParam();
        if (form.state.equals("1")) {
            tv_start_exam.setText("查看考试结果");
            tv_start_exam.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_light_red_bg));
        } else {
            tv_start_exam.setText("开始答题");
            tv_start_exam.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_corner20_red_bg));
        }
        getMvpPresenter().examRuleRequest(dialog, form.examId);
    }

    //返回
    public void goBackExamRule(View view) {
        this.finish();
    }

    //开始答题
    public void startExam(View view) {
        switch (form.state) {
            case "0"://开始答题
                goTo(OnlineExamActivity.class, resultForm);
                break;
            case "1"://查看考试结果
                goTo(ExamResultActivity.class, resultForm);
                break;
        }
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        ExamRuleBean bean = new Gson().fromJson(result, ExamRuleBean.class);
        if (bean.code.equals("0000")) {
            if (null != bean.getData()) {
                tv_start_exam.setEnabled(true);
                tv_start_exam.setClickable(true);
                tv_exam_name.setText(bean.getData().getTitle());
                NumberFormat nf = new DecimalFormat("#");
                String totalScore = bean.getData().getTotalScore();
                totalScore = (totalScore == null || totalScore.equals("")) ? "100" : bean.getData().getTotalScore();
                tv_total_score.setText(nf.format(Double.valueOf(totalScore)) + "分");
                String limitScore = bean.getData().getLimitScore();
                limitScore = (limitScore == null || limitScore.equals("")) ? "60" : bean.getData().getLimitScore();
                tv_standard_score.setText(nf.format(Double.valueOf(limitScore)) + "分");
                tv_question_total_num.setText(bean.getData().getQuestionNum() + "道题");
                tv_exam_during.setText(bean.getData().getExamHours() + "分钟");
                tv_exam_start_date.setText(bean.getData().getExamStartTime());
                tv_exam_end_date.setText(bean.getData().getExamEndTime());
                resultForm = new ExamResultForm();
                resultForm.examId = bean.getData().getId();
                resultForm.limitScore = bean.getData().getLimitScore();
            }
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        showToast("网络出现异常");
    }
}
