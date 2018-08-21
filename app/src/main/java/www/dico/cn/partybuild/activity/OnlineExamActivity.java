package www.dico.cn.partybuild.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.QuestionsAdapter;
import www.dico.cn.partybuild.bean.ExamAnswerBean;
import www.dico.cn.partybuild.bean.ExamResultBean;
import www.dico.cn.partybuild.bean.ExamResultForm;
import www.dico.cn.partybuild.bean.ExamRuleForm;
import www.dico.cn.partybuild.bean.QuestionBean;
import www.dico.cn.partybuild.modleview.OnlineExamView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.OnlineExamPresenter;
import www.dico.cn.partybuild.utils.DateTimeUtils;
import www.dico.cn.partybuild.widget.CountDownButtonHelper;
import www.yuntdev.com.bottomnavigationlibrary.referview.NoTouchViewPager;
import www.yuntdev.com.imitationiosdialoglibrary.AlertDialog;

//在线考试
@CreatePresenter(OnlineExamPresenter.class)
public class OnlineExamActivity extends AbstractMvpActivity<OnlineExamView, OnlineExamPresenter> implements OnlineExamView, QuestionsAdapter.CallBackInterface {
    @BindView(R.id.tv_residue_time_online_exam)
    TextView tv_residue_time_online_exam;
    @BindView(R.id.vp_online_exam)
    NoTouchViewPager vp_online_exam;
    @BindView(R.id.tv_current_item_online_exam)
    TextView tv_current_item_online_exam;
    @BindView(R.id.tv_question_total_online_exam)
    TextView tv_question_total_online_exam;
    private CountDownButtonHelper helper;
    private int during = 0;
    private QuestionsAdapter adapter;
    private List<ExamAnswerBean.TestAnswersBean> answers;
    private String examStartTime;
    private String examCost;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (tv_residue_time_online_exam != null) {
                        helper = new CountDownButtonHelper(tv_residue_time_online_exam, "00分:00秒", during * 60, 1, 0);
                        helper.setOnFinishListener(new CountDownButtonHelper.OnFinishListener() {
                            @Override
                            public void finish() {
                                ExamAnswerBean answerBean = new ExamAnswerBean();
                                ExamAnswerBean.ExamRecordBean recordBean = new ExamAnswerBean.ExamRecordBean();
                                recordBean.setExamRuleId(form.examId);
                                recordBean.setExamTime(DateTimeUtils.getNow());
                                examCost = DateTimeUtils.getMinutes(examStartTime, DateTimeUtils.getNow());
                                recordBean.setExamCost(examCost);
                                answerBean.setExamRecord(recordBean);
                                answerBean.setTestAnswers(answers);
                                answerBean.setLimitScore(form.limitScore);
                                String requestParams = new Gson().toJson(answerBean);
                                getMvpPresenter().doSaveExamAnswer(requestParams);
                            }
                        });
                        helper.start();
                    }
                    break;
            }
        }
    };
    private ExamRuleForm form;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlineexam);
        ButterKnife.bind(this);
        answers = new ArrayList<>();
        form = getParam();
        if (form != null)
            getMvpPresenter().onlineExamRequest(form.examId);
    }

    public void goBackOnlineExam(View view) {
        new AlertDialog(this).builder()
                .setTitle("退出考试")
                .setMsg("考试中途退出，会记零分，您确定退出?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        LocalBroadcastManager manager = LocalBroadcastManager.getInstance(OnlineExamActivity.this);
                        manager.sendBroadcast(new Intent("cn.diconet.www").putExtra("skip", "3"));
                        OnlineExamActivity.this.finish();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    @Override
    public void resultSuccess(String result) {
        QuestionBean bean = new Gson().fromJson(result, QuestionBean.class);
        if (bean.code.equals("0000")) {
            during = bean.getData().getDuration();
            List<QuestionBean.DataBean.QuestionListBean> beans = bean.getData().getQuestionList();
            if (null != beans && beans.size() > 0) {
                tv_current_item_online_exam.setText("1");
                tv_question_total_online_exam.setText("/" + beans.size());
                adapter = new QuestionsAdapter(this, beans, R.layout.item_question);
                vp_online_exam.setAdapter(adapter);
                adapter.setCallBackInterface(this);
                mHandler.sendEmptyMessage(0);
                examStartTime = DateTimeUtils.getNow();
            }
        }
    }

    @Override
    public void resultFailure(String result) {
        showToast(result);
    }

    @Override
    public void submitSuccess(String result) {
        ExamResultBean bean = new Gson().fromJson(result, ExamResultBean.class);
        if (bean.code.equals("0000")) {
            if (bean.getData() != null) {
                ExamResultForm form = new ExamResultForm();
                form.examScore = String.valueOf(bean.getData().getExamScore());
                form.isPass = bean.getData().getIsPass();
                form.limitScore = this.form.limitScore;
                form.examCost = examCost;
                form.examId = this.form.examId;
                goTo(ExamResultActivity.class, form);
                helper.cancel();
                this.finish();
            }
        } else {
            showToast(bean.msg);
        }
    }

    @Override
    public void submitFailure(String result) {
        showToast(result);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog(this).builder()
                    .setTitle("退出考试")
                    .setMsg("考试中途退出，会记零分，您确定退出?")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            LocalBroadcastManager manager = LocalBroadcastManager.getInstance(OnlineExamActivity.this);
                            manager.sendBroadcast(new Intent("cn.diconet.www").putExtra("skip", "3"));
                            OnlineExamActivity.this.finish();
                        }
                    }).setNegativeButton("取消", new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            }).show();
        }
        return false;
    }

    @Override
    public void nextStep(String tqId, String serial, String answer) {
        vp_online_exam.setCurrentItem(vp_online_exam.getCurrentItem() + 1, true);
        tv_current_item_online_exam.setText(String.valueOf(Integer.valueOf(serial) + 1));
        ExamAnswerBean.TestAnswersBean bean = new ExamAnswerBean.TestAnswersBean();
        bean.setQuestionId(tqId);
        bean.setAnswer(answer);
        answers.add(bean);
    }

    @Override
    public void submit(String tqId, String serial, String answer) {
        ExamAnswerBean answerBean = new ExamAnswerBean();
        ExamAnswerBean.ExamRecordBean recordBean = new ExamAnswerBean.ExamRecordBean();
        recordBean.setExamRuleId(form.examId);
        recordBean.setExamTime(DateTimeUtils.getNow());
        examCost = DateTimeUtils.getMinutes(examStartTime, DateTimeUtils.getNow());
        recordBean.setExamCost(examCost);
        ExamAnswerBean.TestAnswersBean bean = new ExamAnswerBean.TestAnswersBean();
        bean.setQuestionId(tqId);
        bean.setAnswer(answer);
        answers.add(bean);
        answerBean.setExamRecord(recordBean);
        answerBean.setTestAnswers(answers);
        answerBean.setLimitScore(form.limitScore);
        String requestParams = new Gson().toJson(answerBean);
        getMvpPresenter().doSaveExamAnswer(requestParams);
    }
}
