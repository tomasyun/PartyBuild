package www.dico.cn.partybuild.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import www.dico.cn.partybuild.R;
import www.dico.cn.partybuild.adapter.QuestionSurveyAdapter;
import www.dico.cn.partybuild.bean.QuestionSurveyBean;
import www.dico.cn.partybuild.bean.SurveyForm;
import www.dico.cn.partybuild.modleview.SurveyListView;
import www.dico.cn.partybuild.mvp.factory.CreatePresenter;
import www.dico.cn.partybuild.mvp.view.AbstractMvpActivity;
import www.dico.cn.partybuild.presenter.SurveyListPresenter;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.SmartRefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.api.RefreshLayout;
import www.yuntdev.com.refreshlayoutlibrary.refreshlayout.listener.OnRefreshListener;

@CreatePresenter(SurveyListPresenter.class)
public class SurveyListActivity extends AbstractMvpActivity<SurveyListView, SurveyListPresenter> implements SurveyListView, QuestionSurveyAdapter.SkipQuestionSurveyInfoInterface {
    @BindView(R.id.rv_question_survey)
    RecyclerView rv_question_survey;
    @BindView(R.id.survey_empty_data)
    View survey_empty_data;
    @BindView(R.id.survey_net_error)
    View survey_net_error;
    @BindView(R.id.srl_question_survey)
    SmartRefreshLayout srl_question_survey;
    private QuestionSurveyAdapter adapter;
    private List<QuestionSurveyBean.DataBean> beans;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surveylist);
        ButterKnife.bind(this);
        rv_question_survey.setLayoutManager(new LinearLayoutManager(this));
        srl_question_survey.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getMvpPresenter().doQuestionSurveyRequest(dialog);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMvpPresenter().doQuestionSurveyRequest(dialog);
    }

    public void goBackQuestionSurvey(View view) {
        this.finish();
    }

    @Override
    public void resultSuccess(String result) {
        srl_question_survey.finishRefresh();
        QuestionSurveyBean bean = new Gson().fromJson(result, QuestionSurveyBean.class);
        if (bean.code.equals("0000")) {
            if (null != bean.getData()) {
                beans = bean.getData();
                if (null != beans && beans.size() > 0) {
                    srl_question_survey.setVisibility(View.VISIBLE);
                    survey_empty_data.setVisibility(View.GONE);
                    survey_net_error.setVisibility(View.GONE);
                    adapter = new QuestionSurveyAdapter(this, R.layout.item_questionsurvey, beans);
                    adapter.setInfoInterface(SurveyListActivity.this);
                    rv_question_survey.setAdapter(adapter);
                } else {
                    srl_question_survey.setVisibility(View.GONE);
                    survey_empty_data.setVisibility(View.VISIBLE);
                    survey_net_error.setVisibility(View.GONE);
                }
            }
        } else {
            showToast("服务器异常");
        }
    }

    @Override
    public void resultFailure(String result) {
        srl_question_survey.finishRefresh();
        showToast(result);
    }

    @Override
    public void netWorkUnAvailable() {
        srl_question_survey.setVisibility(View.GONE);
        survey_empty_data.setVisibility(View.GONE);
        survey_net_error.setVisibility(View.VISIBLE);
        survey_net_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMvpPresenter().doQuestionSurveyRequest(dialog);
            }
        });
    }

    @Override
    public void skip(int skipId,int position) {
        switch (skipId) {
            case 0:
                SurveyForm form = new SurveyForm();
                form.surveyId = beans.get(position).getId();
                goTo(OnlineSurveyActivity.class, form);
                break;
            case 1:
                showToast("该问卷已过期");
                break;
        }
    }
}
