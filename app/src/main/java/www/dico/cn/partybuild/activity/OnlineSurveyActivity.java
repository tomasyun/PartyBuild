package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.SurveyQuestionAdapter;
import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.bean.SkipForm;
import www.dico.cn.partybuild.bean.SurveyAnswerBean;
import www.dico.cn.partybuild.bean.SurveyForm;
import www.dico.cn.partybuild.bean.SurveyQuestionBean;
import www.dico.cn.partybuild.modleview.OnlineSurveyView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.OnlineSurveyPresenter;
import www.yuntdev.com.bottomnavigationlibrary.referview.NoTouchViewPager;
import www.yuntdev.com.imitationiosdialoglibrary.AlertDialog;

@CreatePresenter(OnlineSurveyPresenter.class)
public class OnlineSurveyActivity extends AbstractMvpActivity<OnlineSurveyView, OnlineSurveyPresenter> implements OnlineSurveyView, SurveyQuestionAdapter.SurveyQuestionHandleInterface {
    @BindView(R.id.vp_online_survey)
    NoTouchViewPager vp_online_survey;
    private SurveyForm form;
    private SurveyQuestionAdapter adapter;
    private List<SurveyAnswerBean.AnswersBean> answers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onlinesurvey);
        ButterKnife.bind(this);
        answers = new ArrayList<>();
        form = getParam();
        if (form != null)
            getMvpPresenter().doGetSurveyQuestionRequest(dialog, form.surveyId);
    }

    public void goBackOnlineSurvey(View view) {
        new AlertDialog(this).builder()
                .setTitle("退出调查")
                .setMsg("调查中途退出，本次调查会作废，您确定退出?")
                .setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        OnlineSurveyActivity.this.finish();
                    }
                }).setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        }).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog(this).builder()
                    .setTitle("退出调查")
                    .setMsg("调查中途退出，本次调查会作废，您确定退出?")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            OnlineSurveyActivity.this.finish();
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
    public void resultSuccess(String result) {
        SurveyQuestionBean bean = new Gson().fromJson(result, SurveyQuestionBean.class);
        if (bean.code.equals("0000")) {
            List<SurveyQuestionBean.DataBean> beans = bean.getData();
            if (null != beans && beans.size() > 0) {
                adapter = new SurveyQuestionAdapter(this, beans, R.layout.item_question);
                vp_online_survey.setAdapter(adapter);
                adapter.setHandleInterface(this);
            } else {
                //空白
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
    public void saveAnswerSuccess(String result) {
        BaseProtocol protocol = new Gson().fromJson(result, BaseProtocol.class);
        if (protocol.code.equals("0000")) {
            SkipForm form = new SkipForm();
            form.skip = 4;
            goTo(SuccessTipsActivity.class, form);
            this.finish();
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void saveAnswerFailure(String result) {
        showToast(result);
    }


    @Override
    public void nextStep(String tqId, String serial, String answer) {
        vp_online_survey.setCurrentItem(vp_online_survey.getCurrentItem() + 1, true);
        SurveyAnswerBean.AnswersBean bean = new SurveyAnswerBean.AnswersBean();
        bean.setUserId(tqId);
        bean.setAnswer(answer);
        answers.add(bean);
    }

    @Override
    public void submit(String tqId, String serial, String answer) {
        SurveyAnswerBean answerBean = new SurveyAnswerBean();
        answerBean.setId(form.surveyId);
        SurveyAnswerBean.AnswersBean bean = new SurveyAnswerBean.AnswersBean();
        bean.setUserId(tqId);
        bean.setAnswer(answer);
        answers.add(bean);
        answerBean.setAnswers(answers);
        String json = new Gson().toJson(answerBean);
        getMvpPresenter().doSubmitSurveyQuestionAnswerRequest(dialog, json);
    }
}
